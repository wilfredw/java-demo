package com.wei.java.design.pattern.template.service.v3.test;

import com.alibaba.fastjson.JSON;
import com.wei.java.design.pattern.template.service.v3.AbsResponse;
import com.wei.java.design.pattern.template.service.v3.IpcHandleTemplate;
import com.wei.java.design.pattern.template.service.v3.IpcHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2023/4/19
 */
@Slf4j
public class TestController {
    private IpcHandleTemplate ipcHandleTemplate = new IpcHandleTemplate();

    public AbsResponse<GroupManagementRespVO> listGroupManagement(GroupManagementQC query) {
        log.info("listCommodity query :{}", JSON.toJSONString(query));
        GroupManagementQuery groupManagementQuery = new GroupManagementQuery();
        String serviceName = "listGroupManagement";
        return ipcHandleTemplate
                .handle(serviceName, groupManagementQuery, new IpcHandler<GroupManagementQuery, GroupManagementRespVO>() {
                    @Override
                    public AbsResponse<GroupManagementRespVO> handle(GroupManagementQuery param) {

                        List<GroupManagementRespVO> groupManagementRespVOS =
                                new ArrayList<>();
                        return new PageResponse<GroupManagementRespVO>(0, groupManagementRespVOS);
                    }
                });
    }
}
