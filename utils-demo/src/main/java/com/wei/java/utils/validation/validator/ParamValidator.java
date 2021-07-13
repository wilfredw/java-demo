package com.wei.java.utils.validation.validator;


import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author wuj@tuya.com
 * @Date 2020/2/9
 * @Description
 */

public class ParamValidator {
    private final Validator validator;

    public ParamValidator() {
        LocalizedMessageInterpolator localizedMessageInterpolator = new LocalizedMessageInterpolator();
        this.validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(localizedMessageInterpolator)
//                .messageInterpolator(new ParameterMessageInterpolator())
                .failFast(true)
                .buildValidatorFactory().getValidator();


    }

    public <C extends Object> void validate(C command) throws ConstraintViolationException {
        Set<ConstraintViolation<C>> constraintViolations = validator.validate(command);
        System.out.println(constraintViolations);
        if (null != constraintViolations && constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
