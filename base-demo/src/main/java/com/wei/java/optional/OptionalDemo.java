package com.wei.java.optional;

import com.wei.java.util.SystemOutUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 使用Optional的示例
 *
 * @author buhuan.wang
 * @since 2020/12/18
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> optionalNickName = getNickName("Duke");
        String nickname = null;
        try {
            nickname = optionalNickName.get();
            SystemOutUtil.println("direct try get nickname: " + nickname);
        } catch (Exception e) {
            SystemOutUtil.println("direct try get nickname exception: " + e.toString());
            e.printStackTrace();
        }

        if (optionalNickName.isPresent()) {
            nickname = optionalNickName.get();
        } else {
            nickname = "Default nickname1";
        }
        SystemOutUtil.println(nickname);

        SystemOutUtil.println(optionalNickName.orElse("Default nickname2"));

        SystemOutUtil.println(optionalNickName.orElseGet(() -> {
            return "Default nickname3";
        }));

        SystemOutUtil.println(optionalNickName.orElseThrow(() -> {
            return new RuntimeException("Default nickname4 exception");
        }));

    }

    static Optional<String> getNickName(String name) {
        Map<String, String> nickNames = new HashMap<>(); // 假裝的鍵值資料庫
        nickNames.put("Justin", "caterpillar");
        nickNames.put("Monica", "momor");
        nickNames.put("Irene", "hamimi");
        String nickname = nickNames.get(name); // 鍵不存在時會傳回null
        Optional<String> optionalNickName = Optional.ofNullable(nickname);
        return optionalNickName;
        // return nickname == null ? Optional.empty() : Optional.of(nickname); // 这句和ofNullable的实现一样
    }
}
