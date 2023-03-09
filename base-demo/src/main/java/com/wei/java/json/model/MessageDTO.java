package com.wei.java.json.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author buhuan.wang
 * @since 2021/6/16
 */
public class MessageDTO {
    private String name;
    private String type;
    private JSONObject content;
    private Map<String, String> valueMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }
}
