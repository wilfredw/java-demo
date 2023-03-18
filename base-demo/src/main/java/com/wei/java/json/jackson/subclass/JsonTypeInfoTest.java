package com.wei.java.json.jackson.subclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wei.java.json.jackson.subclass.model.InputPage;
import com.wei.java.json.jackson.subclass.model.NumberPage;
import com.wei.java.json.jackson.subclass.model.Page;

import java.io.IOException;

public class JsonTypeInfoTest {

    public static void main(String[] args) {
        String inputJson = " {\n" +
                "        \"type\": \"input\",\n" +
                "        \"label\": \"标题\",\n" +
                "        \"uiType\": \"input\",\n" +
                "        \"input\" : \"lvsheng\"\n" +
                "        \n" +
                "      }";
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputPage inputPageModel = ((InputPage) mapper.readValue(inputJson, Page.class));
            System.out.println(inputPageModel.getInput());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String numberJson = " {\n" +
                "        \"type\": \"number\",\n" +
                "        \"label\": \"价格\",\n" +
                "        \"uiType\": \"input\",\n" +
                "        \"number\" : 110\n" +
                "        \n" +
                "      }";
        try {
            NumberPage numberPageModel = ((NumberPage) mapper.readValue(numberJson, Page.class));
            System.out.println(numberPageModel.getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}