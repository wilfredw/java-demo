package com.wei.java.json.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author buhuan.wang
 * @since 2021/6/16
 */
public class MessageDTO {
    private String name;
    private String type;
    private String value;
    private JSONObject context;
    private Map<String, String> valueMap;
    private SubMessageDTO subMessage;
    private List<SubMessageDTO> subMessageList;

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

    public JSONObject getContext() {
        return context;
    }

    public void setContext(JSONObject context) {
        this.context = context;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SubMessageDTO getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(SubMessageDTO subMessage) {
        this.subMessage = subMessage;
    }

    public List<SubMessageDTO> getSubMessageList() {
        return subMessageList;
    }

    public void setSubMessageList(List<SubMessageDTO> subMessageList) {
        this.subMessageList = subMessageList;
    }

    public static class SubMessageDTO {
        private String messageType;
        private String messageValue;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageValue() {
            return messageValue;
        }

        public void setMessageValue(String messageValue) {
            this.messageValue = messageValue;
        }
    }
}
