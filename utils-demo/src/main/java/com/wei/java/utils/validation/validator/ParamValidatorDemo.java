package com.wei.java.utils.validation.validator;

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
        demoCommand.setMsgType("setProfile");
        demoCommand.setIds(new ArrayList<>(Arrays.asList("1", "1", "1")));
        demoCommand.setName("12345");

        paramValidator.validate(demoCommand);
        System.out.println("success!");
    }
}
