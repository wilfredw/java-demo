package com.wei.java.design.pattern.template.service.longsheng.actionengine.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理器类注解
 *
 * @author zhoulongsheng
 * @version $Id: ProcClass.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProcClass {

}