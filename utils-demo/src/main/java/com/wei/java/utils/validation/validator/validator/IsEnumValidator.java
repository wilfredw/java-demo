package com.wei.java.utils.validation.validator.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author kenx
 * @version 1.0
 * @date 2021/1/21 20:51
 * 日期验证器
 */
public class IsEnumValidator implements ConstraintValidator<IsEnum, String> {

    private static final String ENUM_FIELD_NAME = "name";
    private static final String ENUM_FIELD_CODE = "code";

    private Class<? extends Enum> enumClass;
    boolean matchFieldName = false;
    boolean matchFieldCode = false;
    private Set<String> enumNameSet;
    private Set<String> enumCodeSet;


    /**
     * 用于初始化注解上的值到这个validator
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsEnum constraintAnnotation) {
        enumClass = constraintAnnotation.enumType();
        List<String> enumFields = Arrays.asList(constraintAnnotation.enumFields());
        if (enumFields.contains(ENUM_FIELD_NAME)) {
            matchFieldName = true;
        }
        if (enumFields.contains(ENUM_FIELD_CODE)) {
            matchFieldCode = true;
        }

        enumNameSet = new TreeSet<>();
        enumCodeSet = new TreeSet<>();
        //转换枚举类
        Class<Enum> clazz = (Class<Enum>) enumClass;
        /**
         * 其实枚举是语法糖
         * 是封装好的多个Enum类的实列
         * 获取所有枚举实例
         */
        Enum[] enumConstants = clazz.getEnumConstants();

        if (matchFieldName) {
            for (Enum enums : enumConstants) {
                //得到枚举实例名
                String instance = enums.name();
                enumNameSet.add(instance);
            }
        }

        if (matchFieldCode) {
            try {
                Method getCodeMethod = clazz.getMethod("getCode");
                if (getCodeMethod != null) {
                    for (Enum enums : enumConstants) {
                        String code = String.valueOf(
                                getCodeMethod.invoke(enums));
                        enumCodeSet.add(code);
                    }
                }
            } catch (Exception e) {
                System.out.println("failed to load enum " + enumClass.getName());
            }
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
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        return isEnum(value);
    }

    public boolean isEnum(String value) {
        return (matchFieldCode && enumCodeSet.contains(value))
                || (matchFieldName && enumNameSet.contains(value));

    }

}
