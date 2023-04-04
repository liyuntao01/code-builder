package ${classInfo.prefix}.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${classInfo.prefix}.${classInfo.appName}.bean.${classInfo.className};
import ${classInfo.prefix}.${classInfo.appName}.mapper.${classInfo.className}Mapper;
import ${classInfo.prefix}.${classInfo.appName}.service.${classInfo.className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


<#if classInfo.checkFields?exists && classInfo.checkFields?keys?size gt 0>
	<#assign check = true />
</#if>
/**
*  ${classInfo.classComment}
*
*  Created by ${classInfo.author} on '${.now?string('yyyy-MM-dd')}'.
*/
@Service
public class ${classInfo.className}ServiceImpl extends ServiceImpl<${classInfo.className}Mapper, ${classInfo.className}> implements ${classInfo.className}Service {

	@Autowired
	private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save${classInfo.className}(${classInfo.className} ${classInfo.className?uncap_first}) {

		save(${classInfo.className?uncap_first});

	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update${classInfo.className}ById(${classInfo.className} ${classInfo.className?uncap_first}) {

		updateById(${classInfo.className?uncap_first});

	}
}
