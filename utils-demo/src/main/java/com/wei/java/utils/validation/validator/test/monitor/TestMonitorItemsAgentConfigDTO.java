package com.wei.java.utils.validation.validator.test.monitor;

import com.alibaba.fastjson.JSONObject;
import com.wei.java.utils.validation.validator.ParamValidator;

import javax.validation.ConstraintViolationException;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/3/1
 */
public class TestMonitorItemsAgentConfigDTO {
    public static void main(String[] args) {
        String testStr =
                "{\"monitorItems\":{\"agentOnlineNum\":[{\"active\":true,\"agentGroups\":[\"110011\"],\"effectiveTime\":{\"calendarType\":\"tuyaWork\"},\"levelConfigs\":{\"warning\":{\"threshold\":3},\"alarm\":{\"threshold\":3}}}]}}";
        String testStr1 =
                "{\"monitorItems\":{\"agentOnlineNum\":[]}}";


        ParamValidator paramValidator = new ParamValidator();
        try {
            System.out.println("============1");
            MonitorItemsAgentConfigDTO agentConfigDTO1 = JSONObject.parseObject(testStr1, MonitorItemsAgentConfigDTO.class);
            paramValidator.validate(agentConfigDTO1);
            System.out.println("============2");
            MonitorItemsAgentConfigDTO agentConfigDTO = JSONObject.parseObject(testStr, MonitorItemsAgentConfigDTO.class);
            paramValidator.validate(agentConfigDTO);
            System.out.println("============3");
        } catch (ConstraintViolationException e) {
            System.out.println("=== no group");
            System.out.println(e.getConstraintViolations());
        }

    }
}
