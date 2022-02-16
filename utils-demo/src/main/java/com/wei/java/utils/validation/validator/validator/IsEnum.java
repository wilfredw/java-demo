package com.wei.java.utils.validation.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsEnumValidator.class}) // 标明由哪个类执行校验逻辑
public @interface IsEnum {

    // 校验出错时默认返回的消息
    String message() default "枚举类型错误";

    //分组校验
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //下面是我自己定义属性
    boolean required() default true;

    Class<? extends Enum> enumType();


}