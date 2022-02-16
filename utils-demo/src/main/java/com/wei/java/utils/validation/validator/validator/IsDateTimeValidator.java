package com.wei.java.utils.validation.validator.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.wei.java.utils.validation.validator.util.ValidatorUtil.isDateTime;

/**
 * @author kenx
 * @version 1.0
 * @date 2021/1/21 20:51
 * 日期验证器
 */
public class IsDateTimeValidator implements ConstraintValidator<IsDateTime, String> {

    private boolean required = false;
    private String dateFormat = "yyyy-MM-dd";

    /**
     * 用于初始化注解上的值到这个validator
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsDateTime constraintAnnotation) {
        required = constraintAnnotation.required();
        dateFormat = constraintAnnotation.dateFormat();
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
        if (StringUtils.isBlank(value)) {
            if (required) {
                return false;
            } else {
                return true;
            }
        }
        return isDateTime(value, dateFormat);
    }


}
