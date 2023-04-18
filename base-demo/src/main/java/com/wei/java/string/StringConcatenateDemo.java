package com.wei.java.string;

import com.google.common.base.Joiner;
import joptsimple.internal.Strings;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @author buhuan.wang
 * @since 2023/4/14
 */
public class StringConcatenateDemo {

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

        String userConversationInfoKey = Joiner.on("_").join("lock",
                String.format("id_%s_lock", "001"));
        System.out.println(userConversationInfoKey);

    }
}
