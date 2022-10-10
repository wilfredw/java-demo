package com.wei.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wei.java.json.model.MessageDTO;
import com.wei.java.util.SystemOutUtil;

/**
 * @author buhuan.wang
 * @since 2021/6/16
 */
public class FastJsonDemo {
    public static void main(String[] args) {
        JSONObject context = new JSONObject();
        context.put("a", "a");
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setName("message");
        messageDTO.setType("single");
        messageDTO.setContent(context);
        String messageStr = JSON.toJSONString(messageDTO);
        SystemOutUtil.println(messageStr);
        MessageDTO messageDTO1 = JSON.parseObject(messageStr, MessageDTO.class);
        SystemOutUtil.println(JSON.toJSONString(messageDTO1));

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
