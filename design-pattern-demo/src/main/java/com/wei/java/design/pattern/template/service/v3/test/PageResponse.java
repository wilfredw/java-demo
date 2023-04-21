package com.wei.java.design.pattern.template.service.v3.test;


import com.wei.java.design.pattern.template.service.v3.AbsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xusw
 * @date 2019-05-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> extends AbsResponse {

    private long total;

    private List<T> data;
}
