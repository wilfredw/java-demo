package com.wei.java.mapstruct.simple.converter;

import com.wei.java.mapstruct.simple.model.UserDO;
import com.wei.java.mapstruct.simple.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/2/22
 */
@Mapper
public abstract class SimpleAbstractClassConverter {
    public static SimpleAbstractClassConverter MAPPER = Mappers.getMapper(SimpleAbstractClassConverter.class);

    @Mappings({
            @Mapping(target = "lastUpdateLDT", expression = "java(com.wei.java.mapstruct.simple.converter.DateTimeConverter.strToDate(s.getLastUpdateDateTime()))"),
            @Mapping(target = "createDateTime", source = "createLDT"),
            @Mapping(target = "type", source = "userTypeEnum"),
    })
    public abstract UserDTO fromDOtoDTO(UserDO s);

    public abstract UserDO fromDTOToDO(UserDTO s);
}
