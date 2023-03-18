package com.wei.java.json.jackson.subclass.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = InputPage.class, name = "input")
        , @JsonSubTypes.Type(value = NumberPage.class, name = "number")})
public abstract class Page {

    private String type;
    private String name;
    private String uiType;
    private String label;
}