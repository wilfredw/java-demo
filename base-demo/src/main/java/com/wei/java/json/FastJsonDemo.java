package com.wei.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wei.java.json.model.MessageDTO;
import com.wei.java.util.SystemOutUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author buhuan.wang
 * @since 2021/6/16
 */
public class FastJsonDemo {
    public static void main(String[] args) {
        JSONObject context = new JSONObject();
        context.put("a", "a");
        context.put("b", null);
        context.put("c", "");
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setName("message");
        messageDTO.setType("single");
        messageDTO.setContent(context);
        Map<String, String> valueMap = new LinkedHashMap<>();
        valueMap.put("x", "x");
        valueMap.put("y", null);
        valueMap.put("z", "");
        messageDTO.setValueMap(valueMap);
        String messageStr = JSON.toJSONString(messageDTO);
        SystemOutUtil.println("1: " + messageStr);
        MessageDTO messageDTO1 = JSON.parseObject(messageStr, MessageDTO.class);
        SystemOutUtil.println("2: " + JSON.toJSONString(messageDTO1));

        String newLineStr = "a\nbb";
        System.out.println(newLineStr);
        System.out.println(JSON.toJSONString(newLineStr));

        String arrayContent =
                "[{\"localize\":[\"linkName\"],\"icon\":\"icon-bangzhuzhongxin1\",\"linkName\":\"HelpCenter\",\"linkUri\":\"https://support.tuya.com/zh/ibuilding\"},{\"localize\":[\"linkName\"],\"icon\":\"icon-IoTgongzuotai\",\"linkName\":\"Workbench\",\"linkUri\":\"/\"}]";
        JSON arrayContentObject = (JSON) JSON.parse(arrayContent);
        String jsonContent =
                "{\"step1Exposure\":\"ty_7kq62tudge8yzjmpabbyuyd6n1dzgrnb\",\"step3Create\":\"ty_tk8idrtep69su324iyd39i9f8rdynk1m\",\"step1Cate\":\"ty_qo0sjog81c6evn3560vfvdpnz4wwztcy\",\"step2Document\":\"ty_vj2g2pm77eeml91ugchevy7uvr5ptsz6\",\"step3Cate\":\"ty_nc51u82taxa7fq794fcfeng6gt95w5a3\",\"step3Document\":\"ty_wre3d0td94e8lctkmv1zdm147zeeawn3\"}";
        JSON jsonContentObject = (JSON) JSON.parse(jsonContent);
        System.out.println(arrayContentObject);
        System.out.println(jsonContentObject);

    }
}
