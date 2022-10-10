package com.wei.java.json.jackson.subclass.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName(value = "hello")
public class HelloMessage extends IMessage {
    private String hello;
}
