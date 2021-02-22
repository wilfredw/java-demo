package com.wei.java.optional;

import com.wei.java.util.SystemOutUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用NULL的示例
 *
 * @author buhuan.wang
 * @since 2020/12/18
 */
public class NullDemo {
    public static void main(String[] args) {
        String nickName = getNickName("Duke");
        if (nickName == null) {
            nickName = "No nichname";
        }
        SystemOutUtil.println(nickName);
    }

    static String getNickName(String name) {
        Map<String, String> nickNames = new HashMap<>(); // 假裝的鍵值資料庫
        nickNames.put("Justin", "caterpillar");
        nickNames.put("Monica", "momor");
        nickNames.put("Irene", "hamimi");
        return nickNames.get(name); // 鍵不存在時會傳回null
    }
}
