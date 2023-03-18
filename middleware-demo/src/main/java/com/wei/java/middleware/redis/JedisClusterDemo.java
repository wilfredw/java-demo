package com.wei.java.middleware.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/6/24
 */
public class JedisClusterDemo {

    public static final String CONVERSATION_LOCK_KEY = "conversation_lock";

    public static final String USER_CLIENT_SENT_CLIENT_MSG_IDS = "user_client_sent_client_msg_ids";

    public static final String USER_CONVERSATION_VERSION = "user_conversation_version_{%s}";

    public static final String USER_CONVERSATION_SET = "user_conversation_set_{%d}";

    public static final String USER_CONVERSATION_INFO = "user_convo_info_{%d}_%d";

    public static final String SERVICE_CONVERSATION_INFO = "service_convo_info";

    public static final String CONVERSATION_PRE_MSG_ID = "conversation_pre_msg_id";

    public static Long imId = Long.MAX_VALUE;
    public static Long conversationId = Long.MAX_VALUE - 5;

    public static void main(String[] args) {
        JedisCluster jedisCluster = getJedis();
//        insertUserConversation(jedisCluster);
//        updateUserConversationByMessage(jedisCluster);
        updateUserConversationByReadMessage(jedisCluster);

    }

    public static UserConversationRedisDO insertUserConversation(JedisCluster jedisCluster) {
        String role = "customer";
        String status = "ok";
        Integer unreadNum = 0;
        Long lastReadMsgId = 0L;
        Long lastMsgId = 0L;
        String userConversationVersionKey = String.format(USER_CONVERSATION_VERSION, imId);
        String userConversationSetKey = String.format(USER_CONVERSATION_SET, imId);
        String userConversationInfoKey = String.format(USER_CONVERSATION_INFO, imId, conversationId);
        System.out.println(userConversationSetKey);
        System.out.println(userConversationInfoKey);
//        jedisCluster.set(userConversationSetKey, "xxx");
        String script =
                "local version = 0; "
                        + "if (redis.call('exists', KEYS[3]) == 0) then "
                        + "version = 1; "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "else "
                        + "local largest_version = redis.call('get', KEYS[3]);  "
                        + "version = largest_version + 1;  "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "end; "
                        + "redis.call('hset', KEYS[2], 'role', ARGV[2]); "
                        + "redis.call('hset', KEYS[2], 'status', ARGV[3]); "
                        + "redis.call('hset', KEYS[2], 'unreadNum', ARGV[4]); "
                        + "redis.call('hset', KEYS[2], 'lastReadMsgId', ARGV[5]); "
                        + "redis.call('hset', KEYS[2], 'lastMsgId', ARGV[6]); "
                        + "local convo_table = {};  "
                        + "convo_table[1] = '\"role\"';  "
                        + "convo_table[2] = redis.call('hget', KEYS[2], 'role');  "
                        + "convo_table[3] = '\"status\"';  "
                        + "convo_table[4] = redis.call('hget', KEYS[2], 'status');  "
                        + "convo_table[5] = '\"unreadNum\"';  "
                        + "convo_table[6] = redis.call('hget', KEYS[2], 'unreadNum');  "
                        + "convo_table[7] = '\"lastReadMsgId\"';  "
                        + "convo_table[8] = redis.call('hget', KEYS[2], 'lastReadMsgId');  "
                        + "convo_table[9] = '\"lastMsgId\"';  "
                        + "convo_table[10] = redis.call('hget', KEYS[2], 'lastMsgId');  "
                        + "convo_table[11] = '\"version\"';  "
                        + "convo_table[12] = version;  "
                        + "return convo_table;";
        Object result = jedisCluster.eval(script, Arrays.asList(userConversationSetKey,
                userConversationInfoKey,
                userConversationVersionKey),
                Arrays.asList(conversationId.toString(),
                        role,
                        status,
                        unreadNum.toString(),
                        lastReadMsgId.toString(),
                        lastMsgId.toString()));
        System.out.println(result);
        return null;
    }

    public static UserConversationRedisDO updateUserConversationByMessage(
            JedisCluster jedisCluster) {

        Boolean excludeFromUnread = Boolean.FALSE;
        Long msgId = 9223372036854770008L;
        String userConversationVersionKey = String.format(USER_CONVERSATION_VERSION, imId);
        String userConversationSetKey = String.format(USER_CONVERSATION_SET, imId);
        String userConversationInfoKey = String.format(USER_CONVERSATION_INFO, imId, conversationId);
        System.out.println(userConversationSetKey);
        System.out.println(userConversationInfoKey);

        String script =
                "local version = 0; "
                        + "local lastMsgId = redis.call('hget', KEYS[2], 'lastMsgId'); "
                        + "if (ARGV[3] > lastMsgId) then  "
                        + "if (redis.call('exists', KEYS[3]) == 0) then "
                        + "version = 1; "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "else "
                        + "local largest_version = redis.call('get', KEYS[3]);  "
                        + "version = largest_version + 1;  "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "end; "
                        + "redis.call('hset', KEYS[2], 'lastMsgId', ARGV[3]); "
                        + "if (ARGV[2] == '\"unread\"') then  "
                        + "local unreadNum = redis.call('hget', KEYS[2], 'unreadNum'); "
                        + "unreadNum = 1 + unreadNum; "
                        + "redis.call('hset', KEYS[2], 'unreadNum', unreadNum); "
                        + "end; "
                        + "end; "
                        + "local convo_table = {};  "
                        + "convo_table[1] = '\"role\"';  "
                        + "convo_table[2] = redis.call('hget', KEYS[2], 'role');  "
                        + "convo_table[3] = '\"status\"';  "
                        + "convo_table[4] = redis.call('hget', KEYS[2], 'status');  "
                        + "convo_table[5] = '\"unreadNum\"';  "
                        + "convo_table[6] = redis.call('hget', KEYS[2], 'unreadNum');  "
                        + "convo_table[7] = '\"lastReadMsgId\"';  "
                        + "convo_table[8] = redis.call('hget', KEYS[2], 'lastReadMsgId');  "
                        + "convo_table[9] = '\"lastMsgId\"';  "
                        + "convo_table[10] = redis.call('hget', KEYS[2], 'lastMsgId');  "
                        + "convo_table[11] = '\"version\"';  "
                        + "convo_table[12] = version;  "
                        + "return convo_table;";
        Object result = jedisCluster.eval(script, Arrays.asList(userConversationSetKey,
                userConversationInfoKey,
                userConversationVersionKey),
                Arrays.asList(conversationId.toString(),
                        excludeFromUnread ? "\"exclude\"" : "\"unread\"",
                        msgId.toString()));
        System.out.println(result);
        return null;
    }


    public static UserConversationRedisDO updateUserConversationByReadMessage(JedisCluster jedisCluster) {

        Long unreadNum = 2L;
        Long readMsgId = 9223372036854770006L;

        String userConversationVersionKey = String.format(USER_CONVERSATION_VERSION, imId);
        String userConversationSetKey = String.format(USER_CONVERSATION_SET, imId);
        String userConversationInfoKey = String.format(USER_CONVERSATION_INFO, imId, conversationId);
        System.out.println(userConversationSetKey);
        System.out.println(userConversationInfoKey);

        String script =
                "local version = 0; "
                        + "local lastReadMsgId = redis.call('hget', KEYS[2], 'lastReadMsgId'); "
                        + "if (ARGV[3] > lastReadMsgId) then  "
                        + "if (redis.call('exists', KEYS[3]) == 0) then "
                        + "version = 1; "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "else "
                        + "local largest_version = redis.call('get', KEYS[3]);  "
                        + "version = largest_version + 1;  "
                        + "redis.call('zadd', KEYS[1], version, ARGV[1]); "
                        + "redis.call('set', KEYS[3], version); "
                        + "end; "
                        + "redis.call('hset', KEYS[2], 'lastReadMsgId', ARGV[3]); "
                        + "redis.call('hset', KEYS[2], 'unreadNum', ARGV[2]); "
                        + "end; "
                        + "local convo_table = {};  "
                        + "convo_table[1] = '\"role\"';  "
                        + "convo_table[2] = redis.call('hget', KEYS[2], 'role');  "
                        + "convo_table[3] = '\"status\"';  "
                        + "convo_table[4] = redis.call('hget', KEYS[2], 'status');  "
                        + "convo_table[5] = '\"unreadNum\"';  "
                        + "convo_table[6] = redis.call('hget', KEYS[2], 'unreadNum');  "
                        + "convo_table[7] = '\"lastReadMsgId\"';  "
                        + "convo_table[8] = redis.call('hget', KEYS[2], 'lastReadMsgId');  "
                        + "convo_table[9] = '\"lastMsgId\"';  "
                        + "convo_table[10] = redis.call('hget', KEYS[2], 'lastMsgId');  "
                        + "convo_table[11] = '\"version\"';  "
                        + "convo_table[12] = version;  "
                        + "return convo_table;";
        Object result = jedisCluster.eval(script, Arrays.asList(userConversationSetKey,
                userConversationInfoKey,
                userConversationVersionKey),
                Arrays.asList(conversationId.toString(),
                        unreadNum.toString(),
                        readMsgId.toString()));
        System.out.println(result);

        return null;
    }

    //可用连接实例的最大数目，默认为8；
    //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private static Integer MAX_TOTAL = 1024;
    //控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
    private static Integer MAX_IDLE = 200;
    //等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException
    private static Integer MAX_WAIT_MILLIS = 10000;
    //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
    //如果为true，则得到的jedis实例均是可用的
    private static Boolean TEST_ON_BORROW = true;
    //在空闲时检查有效性, 默认false
    private static Boolean TEST_WHILE_IDLE = true;
    //是否进行有效性检查
    private static Boolean TEST_ON_RETURN = true;

    //访问密码
    private static String AUTH = "";


    public static JedisCluster getJedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        /*注意：
            在高版本的jedis jar包，比如本版本2.9.0，JedisPoolConfig没有setMaxActive和setMaxWait属性了
            这是因为高版本中官方废弃了此方法，用以下两个属性替换。
            maxActive  ==>  maxTotal
            maxWait==>  maxWaitMillis
         */
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setTestWhileIdle(TEST_WHILE_IDLE);
        config.setTestOnReturn(TEST_ON_RETURN);

        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 7000));
        JedisCluster jedisCluster = new JedisCluster(nodes, 1000, 1000, 3, config);
        return jedisCluster;
    }
}
