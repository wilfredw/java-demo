package com.wei.java.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类的详细说明
 * 如果继承某个接口{@link StateContext}，提供一个链接，方便使用
 * <p>
 * 如果是过期的接口，那么请尽量提供用于替换的类的链接地址{@link StateContext}
 *
 * @param <S> 如果用到泛型，可以这样注释
 * @param <E> 如果用到泛型，可以这样注释
 * @author buhuan.wang
 * @since 2021/7/1
 */
public class Jackson {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        String strOK = "\"testjson\"";
        String strOK2 = MAPPER.readValue(strOK, String.class);

        String s = "testjson";
        String s1 = MAPPER.readValue(s, String.class);


        System.out.println(s1);
    }

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public static void testObjectMapper() {
 
    }


}
