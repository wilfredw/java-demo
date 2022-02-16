package com.wei.java.utils.validation.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author buhuan.wang
 * @since 2022/2/11
 */
@Documented
@Constraint(validatedBy = CheckEnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumCheck {
    String message() default "请传入合法的byte";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
