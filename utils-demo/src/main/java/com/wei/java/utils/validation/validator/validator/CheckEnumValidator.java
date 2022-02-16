package com.wei.java.utils.validation.validator.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author buhuan.wang
 * @since 2022/2/11
 */
public class CheckEnumValidator implements ConstraintValidator<ByteCheck, Byte> {
    @Override
    public void initialize(ByteCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(String.valueOf(value))) {
            return false;
        }
        String regex = "^[0|1]$";
        return Pattern.matches(regex, String.valueOf(value));
    }
}
