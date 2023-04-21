package com.wei.java.design.pattern.template.service.longsheng.actionengine.enums;

/**
 * 多语言处理器枚举.
 *
 * @author zhoulongsheng
 * @version $Id: ProcTypeEnum.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
public enum ProcTypeEnum {

    OTA("ota"),
    I18n("i18n"),
    Linkage("linkage"),
    DPTest("dpTest"),
    VOICE("voice"),
    LINKMODE("linkmode"),
    ;

    final private String name;

    private ProcTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

