package com.wei.java.json.jackson.subclass.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName(value = "ask")
public class AskMessage extends IMessage {
    private String ask;
}
