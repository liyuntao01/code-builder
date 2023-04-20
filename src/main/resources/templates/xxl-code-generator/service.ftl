package ${classInfo.prefix}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${classInfo.prefix}.${classInfo.appName}.bean.${classInfo.className};

<#if classInfo.checkFields?exists && classInfo.checkFields?keys?size gt 0>
    <#assign check = true />
</#if>

/**
*  ${classInfo.classComment}
*
*    @author  ${classInfo.author} on '
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
*/
public interface ${classInfo.className}Service extends IService<${classInfo.className}Bean> {

    void save${classInfo.className}(${classInfo.className}Bean ${classInfo.className?uncap_first}Bean);

    void update${classInfo.className}ById(${classInfo.className}Bean ${classInfo.className?uncap_first}Bean);
}
