package com.wei.java.design.pattern.template.service.longsheng.actionengine.enums;

/**
 * @author muyh
 * @since 2020/9/14 21:02
 */
public interface IProcEnum {

    ProcTypeEnum procType();

    String procName();

    default String uniqueKey() {
        return procType().getName() + "_" + procName();
    }
}
