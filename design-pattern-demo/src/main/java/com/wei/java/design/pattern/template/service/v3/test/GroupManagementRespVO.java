package com.wei.java.design.pattern.template.service.v3.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author buhuan
 * @description: 群组管理
 * @date 2023-03-31
 * @version: 1.0
 */
@Data
public class GroupManagementRespVO implements Serializable {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 商品大类
     */
    private String categoryCode;

    /**
     * 商品数量（当商品群组时才有数据)
     */
    private Long commodityTotal;

    /**
     * 语言种类数量
     */
    private Integer langTotal;

    /**
     * 关联客户数量
     */
    private Long dealerTotal;


    /**
     * 最后修改时间（毫秒）
     */
    private Long gmtModified;

    /**
     * 最后修改人id
     */
    private Long modifier;

}