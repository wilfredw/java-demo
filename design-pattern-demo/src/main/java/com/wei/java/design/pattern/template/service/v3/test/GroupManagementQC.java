package com.wei.java.design.pattern.template.service.v3.test;

import lombok.Data;

/**
 * @author buhuan
 * @description: 群组管理
 * @date 2023-03-31
 * @version: 1.0
 */
@Data
public class GroupManagementQC extends BasePageQC {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 群组类型，0:商品，1:样式
     */
    private Integer groupType;

    /**
     * 商品大类
     */
    private String categoryCode;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 客户账号名
     */
    private String userName;

}
