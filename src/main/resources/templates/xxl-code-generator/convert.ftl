package ${classInfo.prefix}.convert;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${classInfo.prefix}.${classInfo.appName}.entity.${classInfo.className}Bean;
import ${classInfo.prefix}.${classInfo.appName}.dto.${classInfo.className}DTO;
import ${classInfo.prefix}.${classInfo.appName}.vo.${classInfo.className}VO;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*  ${classInfo.classComment}实体映射类
*
* @author  ${classInfo.author} on '
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
*/

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ${classInfo.className}ConvertMappers {

    ${classInfo.className}ConvertMappers MAPPER = Mappers.getMapper(${classInfo.className}ConvertMappers.class);

    ${classInfo.className}Bean convertBean(${classInfo.className}DTO ${classInfo.className?uncap_first}DTO);

    List<${classInfo.className}VO> convertVoList(List<${classInfo.className}Bean> ${classInfo.className?uncap_first}List);

    ${classInfo.className}VO convertVO(${classInfo.className}Bean ${classInfo.className?uncap_first});

   <#-- MpAll<${classInfo.className}> convertAll(MpAll<${classInfo.className}DTO> pageDTO);

    MpPager<${classInfo.className}> convertPage(MpPager<${classInfo.className}DTO> pageDTO);

    Page<${classInfo.className}VO> convertPageVO(Page<${classInfo.className}> page);-->

}