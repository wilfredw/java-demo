package com.wei.java.utils.validation.validator;

import com.wei.java.utils.validation.validator.enums.TypeEnum;
import com.wei.java.utils.validation.validator.group.Create;
import com.wei.java.utils.validation.validator.group.Update;
import com.wei.java.utils.validation.validator.model.DemoCommand;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/7/13
 */
public class ParamValidatorDemo {
    public static void main(String[] args) {
        ParamValidator paramValidator = new ParamValidator();
        DemoCommand demoCommand = new DemoCommand();
        byte value = 1;
        demoCommand.setName("12345678");
        try {
            /**
             * 设置group的注解不会调用
             * 没设置group的注解可以调用
             */
            paramValidator.validate(demoCommand);
        } catch (ConstraintViolationException e) {
            System.out.println("=== no group");
            System.out.println(e.getConstraintViolations());
        }

        DemoCommand createCommand = new DemoCommand();
        createCommand.setMsgType("setProfile");
        createCommand.setIds(new ArrayList<>(Arrays.asList("1", "1", "1")));
        createCommand.setName("12345");
        createCommand.setCurrentDate("2022-14-11");
        try {
            /**
             * 设置当前的group的注解会调用
             * 没设置group的注解可以调用
             */
            paramValidator.validate(createCommand, Create.class);
        } catch (ConstraintViolationException e) {
            System.out.println("=== create group");
            System.out.println(e.getConstraintViolations());
        }
        DemoCommand createCommand2 = new DemoCommand();
        createCommand2.setIsTrue(value);
        createCommand2.setMsgType("setProfile");
        createCommand2.setIds(new ArrayList<>(Arrays.asList("1", "1", "1")));
        createCommand2.setName("12345");
        createCommand2.setCurrentDate("2022-12-11");
        createCommand2.setType("xxxxx");
        try {
            paramValidator.validate(createCommand2, Create.class);
        } catch (ConstraintViolationException e) {
            System.out.println("=== create group 22");
            System.out.println(e.getConstraintViolations());
        }

        /**
         * 设置当前的group的注解会调用
         * 没设置group的注解可以调用
         */
        DemoCommand updateCommand = new DemoCommand();
        updateCommand.setIsTrue(value);
        updateCommand.setMsgType("setProfile");
        updateCommand.setIds(new ArrayList<>(Arrays.asList("1", "1", "1")));
        updateCommand.setName("12345");
        updateCommand.setCurrentDate("2022-12-13");
        try {
            paramValidator.validate(updateCommand, Update.class);
        } catch (ConstraintViolationException e) {
            System.out.println("=== update group");
            System.out.println(e.getConstraintViolations());
        }

        DemoCommand updateCommand22 = new DemoCommand();
        updateCommand22.setId("111");
        updateCommand22.setIsTrue(value);
        updateCommand22.setMsgType("setProfile");
        updateCommand22.setIds(new ArrayList<>(Arrays.asList("1", "1", "1")));
        updateCommand22.setName("12345");
        updateCommand22.setCurrentDate("2022-13-13");
        updateCommand22.setType(TypeEnum.NORMAL.name());
        try {
            paramValidator.validate(updateCommand22, Update.class);
        } catch (ConstraintViolationException e) {
            System.out.println("=== update group");
            System.out.println(e.getConstraintViolations());
        }
        System.out.println("success!");
    }
}
