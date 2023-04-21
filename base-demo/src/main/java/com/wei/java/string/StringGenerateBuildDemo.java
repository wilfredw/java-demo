package com.wei.java.string;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import joptsimple.internal.Strings;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @author buhuan.wang
 * @since 2023/4/14
 */
public class StringGenerateBuildDemo {

    public static void main(String[] args) {
        String a = "aaa";
        String seperator = "+";
        String b = "bbb";
        String plusStr = a + seperator + b;
        System.out.println(plusStr);

        StringBuilder stringBuilder = new StringBuilder(a);
        stringBuilder.append(seperator);
        stringBuilder.append(b);
        System.out.println(stringBuilder.toString());

        StringBuffer stringBuffer = new StringBuffer(a);
        stringBuffer.append(seperator);
        stringBuffer.append(b);
        System.out.println(stringBuffer.toString());

        String concat = a.concat(seperator).concat(b);
        System.out.println(concat);

        String stringJoin = Strings.join(Arrays.asList(a, b), seperator);
        System.out.println(stringJoin);

        String stringFormat = String.format("lock_%s_%s", "1111", 2);
        System.out.println(stringFormat);

        String messageFormat = MessageFormat.format("您好{0}，晚上好！您目前余额：{1,number,#.##}元，积分：{2}", "张三", 10.155, 10);
        System.out.println(messageFormat);
        //您好张三，晚上好！您目前余额：10.16元，积分：10

        String[] params = "a,b,c,d".split(",");
        String messageFormat2 = MessageFormat.format("Hello, this is {0}, {1}, {2}.", params);
        System.out.println("params: " + JSON.toJSONString(params) + " messageFormat2: " + messageFormat2);
        Object[] paramsObj = new Object[] {"a", 1, null};
        messageFormat2 = MessageFormat.format("Hello, this is {0}, {1}, {2}.", paramsObj);
        System.out.println("params: " + JSON.toJSONString(paramsObj) + " messageFormat2: " + messageFormat2);
        paramsObj = new Object[] {"a", 1};
        messageFormat2 = MessageFormat.format("Hello, this is {0}, {1}, {2}.", paramsObj);
        System.out.println("params: " + JSON.toJSONString(paramsObj) + " messageFormat2: " + messageFormat2);
        paramsObj = null;
        messageFormat2 = MessageFormat.format("Hello, this is {0}, {1}, {2}.", paramsObj);
        System.out.println("params: " + JSON.toJSONString(paramsObj) + " messageFormat2: " + messageFormat2);
        paramsObj = null;
        messageFormat2 = MessageFormat.format("Hello.", paramsObj);
        System.out.println("params: " + JSON.toJSONString(paramsObj) + " messageFormat2: " + messageFormat2);

        String userConversationInfoKey = Joiner.on("_").join("lock",
                String.format("id_%s_lock", "001"));
        System.out.println(userConversationInfoKey);


    }
}
