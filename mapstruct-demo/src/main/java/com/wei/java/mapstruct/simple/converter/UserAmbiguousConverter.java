package com.wei.java.mapstruct.simple.converter;

import com.wei.java.mapstruct.simple.model.BaseRichDO;
import com.wei.java.mapstruct.simple.model.UserDO;
import com.wei.java.mapstruct.simple.model.UserDTO;
import com.wei.java.mapstruct.simple.model.UserRichDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author buhuan.wang
 * @since 2023/4/21
 */
@Mapper
public interface UserAmbiguousConverter {
    UserAmbiguousConverter MAPPER = Mappers.getMapper(UserAmbiguousConverter.class);

    @Mappings({
            @Mapping(target = "lastUpdateLDT", expression = "java(com.wei.java.mapstruct.simple.converter.DateTimeConverter.strToDate(s.getLastUpdateDateTime()))"),
            @Mapping(target = "createDateTime", source = "createLDT"),
            @Mapping(target = "type", source = "userTypeEnum"),
    })
    UserDTO fromDOtoDTO(UserDO s);

    List<UserDTO> fromDOtoDTOs(List<UserDO> userDOS);

    UserRichDTO fromRichDOtoRichDTO(BaseRichDO s);

    List<UserRichDTO> fromRichDOtoRichDTOs(List<BaseRichDO> userDOS);


}
