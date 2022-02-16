package com.wei.java.utils.validation.validator.validator;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author kenx
 * @version 1.0
 * @date 2021/1/21 20:51
 * 日期验证器
 */
public class IsEnumValidator implements ConstraintValidator<IsEnum, String> {

    private boolean required = false;
    private Class<? extends Enum> enumClass;
    private Set<String> enumNameSet;

    /**
     * 用于初始化注解上的值到这个validator
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsEnum constraintAnnotation) {
        required = constraintAnnotation.required();
        enumClass = constraintAnnotation.enumType();

        enumNameSet = new TreeSet<>();
        //转换枚举类
        Class<Enum> clazz = (Class<Enum>) enumClass;
        /**
         * 其实枚举是语法糖
         * 是封装好的多个Enum类的实列
         * 获取所有枚举实例
         */
        Enum[] enumConstants = clazz.getEnumConstants();

        for (Enum enums : enumConstants) {
            //得到枚举实例名
            String instance = enums.name();
            enumNameSet.add(instance);
        }
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

        return isEnum(enumClass, value);
    }

    public boolean isEnum(Class<?> beanClass, String status) {
        if (StrUtil.isBlank(status)) {
            return false;
        }

        return enumNameSet.contains(status);

    }

}
