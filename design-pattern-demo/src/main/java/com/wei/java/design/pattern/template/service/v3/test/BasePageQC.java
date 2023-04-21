package com.wei.java.design.pattern.template.service.v3.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author buhuan.wang
 * @since 2023/4/18
 */
@Data
public class BasePageQC implements Serializable {
    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNo;
}
