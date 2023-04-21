package com.wei.java.mapstruct.simple.converter;

import com.wei.java.mapstruct.simple.model.BaseDO;
import com.wei.java.mapstruct.simple.model.BaseDTO;
import com.wei.java.mapstruct.simple.model.BaseRichDO;
import com.wei.java.mapstruct.simple.model.BaseRichDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author buhuan.wang
 * @since 2023/4/21
 */
@Mapper
public interface AmbiguousConverter {
    AmbiguousConverter MAPPER = Mappers.getMapper(AmbiguousConverter.class);

    // 冲突
    @Named(value = "fromDOtoDTO")
    BaseDTO fromDOtoDTO(BaseDO s);

    // 冲突
    @Named(value = "fromDOtoRichDTO")
    BaseRichDTO fromDOtoRichDTO(BaseDO s);

    // ambiguous
//    @IterableMapping(qualifiedByName = "fromDOtoDTO")
    @IterableMapping(elementTargetType = BaseDTO.class)
    // 上面两种方式，选一个，解决冲突，确定使用的单个元素转换器
    List<BaseDTO> fromDOtoDTOs(List<BaseDO> userDOS);

    List<BaseRichDTO> fromDOtoRichDTOs(List<BaseDO> userDOS);

    BaseRichDTO fromRichDOtoRichDTO(BaseRichDO s);

    List<BaseRichDTO> fromRichDOtoRichDTOs(List<BaseRichDO> userDOS);


}
