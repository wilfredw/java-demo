package com.wei.java.mapstruct.simple;

import com.wei.java.mapstruct.simple.converter.AmbiguousConverter;
import com.wei.java.mapstruct.simple.converter.DateTimeConverter;
import com.wei.java.mapstruct.simple.converter.SimpleAbstractClassConverter;
import com.wei.java.mapstruct.simple.converter.SimpleConverter;
import com.wei.java.mapstruct.simple.enums.UserTypeEnum;
import com.wei.java.mapstruct.simple.model.BaseDO;
import com.wei.java.mapstruct.simple.model.UserDO;
import com.wei.java.mapstruct.simple.model.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/2/22
 */
public class SimpleMain {
    public static void main(String[] args) {
        UserDO userDO = UserDO.builder()
                .name("a")
                .address("zhejiang, china")
                .age(24)
                .labels(Arrays.asList("a", "b"))
                .createLDT(DateTimeConverter.strToDate("2021-02-22 19:55:05"))
                .lastUpdateDateTime("2021-02-22 19:55:05")
                .userTypeEnum(UserTypeEnum.DB)
                .build();
        UserDTO userDTO = SimpleConverter.MAPPER.fromDOtoDTO(userDO);
        System.out.println("name: " + userDTO.getName()
                + " type: " + userDTO.getType());

        System.out.println("----------");
        UserDTO userDTO2 = SimpleAbstractClassConverter.MAPPER.fromDOtoDTO(userDO);
        System.out.println("name: " + userDTO2.getName()
                + " type: " + userDTO2.getType());

//        List<UserDO> userDOS = new ArrayList<>();
//        userDOS.add(userDO);
//        System.out.println(AmbiguousConverter.MAPPER.fromDOtoDTOs(userDOS));
        List<BaseDO> baseDOS = new ArrayList<>();
        BaseDO baseDO = new BaseDO();
        baseDO.setId("1");
        baseDOS.add(baseDO);
        System.out.println(AmbiguousConverter.MAPPER.fromDOtoDTOs(baseDOS));
        return;
    }
}
