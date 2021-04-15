package com.wei.java.curator.provider;

import com.google.common.base.Joiner;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.UUID;

import static com.wei.java.curator.constants.CommonConstants.*;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/4/15
 */
public class ProviderRunner implements Runnable {
    @Override
    public void run() {
        CuratorFramework client = null;
        try {
            String address = "localhost:2181";
            client = CuratorFrameworkFactory.newClient(address,
                    new ExponentialBackoffRetry(1000, 3));
            client.start();

            String nodePath = Joiner.on("/").join(ZK_ROOT_PATH, BIZ_TYPE, SCENARIO_DIR, NODE_ID);

            while (true) {
                try {

                    Stat stat = client.checkExists().forPath(nodePath);
                    if (stat == null) {
                        client.create().creatingParentsIfNeeded().forPath(nodePath);
                    }
                    client.setData().forPath(nodePath, UUID.randomUUID().toString().getBytes());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Thread.sleep(10 * 1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
