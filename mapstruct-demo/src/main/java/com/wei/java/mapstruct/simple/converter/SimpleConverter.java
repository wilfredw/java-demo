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
public interface SimpleConverter {
    SimpleConverter MAPPER = Mappers.getMapper(SimpleConverter.class);

    @Mappings({
            @Mapping(target = "lastUpdateLDT", expression = "java(com.wei.java.mapstruct.simple.converter.DateTimeConverter.strToDate(s.getLastUpdateDateTime()))"),
            @Mapping(target = "createDateTime", source = "createLDT"),
            @Mapping(target = "type", source = "userTypeEnum"),
    })
    UserDTO fromDOtoDTO(UserDO s);

    UserDO fromDTOToDO(UserDTO s);
}
