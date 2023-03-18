package com.wei.java.json.jackson.subclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wei.java.json.jackson.subclass.model.AskMessage;
import com.wei.java.json.jackson.subclass.model.HelloMessage;
import com.wei.java.json.jackson.subclass.model.IMessage;

import java.io.IOException;

public class JsonTypeInfoTest2 {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        // todo reflections框架
        // Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(JsonTypeName.class);
        // classSet.parallelStream().forEach(clazz -> mapper.registerSubtypes(clazz));

        String inputJson = " {\n" +
                "        \"type\": \"hello\",\n" +
                "        \"hello\": \"my name is xxx\",\n" +
                "        \n" +
                "      }";
        try {
            HelloMessage helloMessage = ((HelloMessage) mapper.readValue(inputJson, IMessage.class));
            System.out.println(helloMessage.getHello());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String numberJson = " {\n" +
                "        \"type\": \"ask\",\n" +
                "        \"ask\": \"who are you\",\n" +
                "        \n" +
                "      }";
        try {
            AskMessage askMessage = ((AskMessage) mapper.readValue(numberJson, IMessage.class));
            System.out.println(askMessage.getAsk());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}