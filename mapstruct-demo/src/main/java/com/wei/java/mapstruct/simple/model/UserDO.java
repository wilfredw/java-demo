package com.wei.java.mapstruct.simple.model;

import com.wei.java.mapstruct.simple.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDO {
    private String name;
    private String desc;
    private List<String> labels;
    private String address;
    private Integer age;
    private LocalDateTime createLDT;
    private String lastUpdateDateTime;
    private UserTypeEnum userTypeEnum;
}
