package com.wei.java.mapstruct.simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类的详细说明
 * 如果继承某个接口{@link StateContext}，提供一个链接，方便使用
 * <p>
 * 如果是过期的接口，那么请尽量提供用于替换的类的链接地址{@link StateContext}
 *
 * @param <S> 如果用到泛型，可以这样注释
 * @param <E> 如果用到泛型，可以这样注释
 * @author buhuan.wang
 * @since 2023/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRichDTO extends BaseDTO {
    private String money;
}