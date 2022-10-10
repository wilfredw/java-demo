package com.wei.java.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author buhuan.wang
 * @since 2021/7/1
 */
public class Jackson {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        UniKey uniKey = new UniKey();
        uniKey.setId(123L);
        uniKey.setKey("hello");
        System.out.println(MAPPER.writeValueAsString(uniKey));

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

    public static class UniKey {
        private String key;
        private Long id;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }


}
