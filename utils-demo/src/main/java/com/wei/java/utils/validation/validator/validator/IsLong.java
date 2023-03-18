package com.wei.java.utils.validation.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 检查字符串是不是Long的校验注解
 *
 * @author buhuan.wang
 * @since 2022/2/14
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsLongValidator.class})
public @interface IsLong {

    /**
     * 校验出错时默认返回的消息
     */
    String message() default "日期格式错误";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
