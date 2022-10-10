package com.wei.java.utils.validation.validator.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 检查字符串是不是Long的校验器
 *
 * @author buhuan.wang
 * @since 2022/2/14
 */
public class IsLongValidator implements ConstraintValidator<IsLong, String> {

    /**
     * 用于初始化注解上的值到这个validator
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsLong constraintAnnotation) {
    }

    /**
     * 具体的校验逻辑
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        Long ret = null;
        try {
            ret = Long.valueOf(value);
        } catch (Throwable e) {
            return false;
        }
        return ret != null;
    }
}
