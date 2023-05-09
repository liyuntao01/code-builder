package ${classInfo.prefix}.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${classInfo.prefix}.${classInfo.appName}.entity.${classInfo.className}Bean;
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
*    @author  ${classInfo.author} on '
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
*/
@Service
public class ${classInfo.className}ServiceImpl extends ServiceImpl<${classInfo.className}Mapper, ${classInfo.className}Bean> implements ${classInfo.className}Service {

	@Autowired
	private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save${classInfo.className}(${classInfo.className}Bean ${classInfo.className?uncap_first}Bean) {

		save(${classInfo.className?uncap_first}Bean);

	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update${classInfo.className}ById(${classInfo.className}Bean ${classInfo.className?uncap_first}Bean) {

		updateById(${classInfo.className?uncap_first}Bean);

	}
}
