package com.wei.java.utils.validation.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsEnumValidator.class}) // 标明由哪个类执行校验逻辑
public @interface IsEnum {

    /**
     * 校验出错时默认返回的消息
     *
     * @return
     */
    String message() default "枚举类型错误";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验的目标枚举类型
     */
    Class<? extends Enum> enumType();

    /**
     * 校验的目标匹配的枚举类型的字段，
     * name是枚举name()返回值，
     * code是枚举getCode()方法返回值，
     */
    String[] enumFields() default {"name"};

}