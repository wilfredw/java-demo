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
    }
}
