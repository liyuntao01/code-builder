package ${classInfo.prefix}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${classInfo.prefix}.${classInfo.appName}.bean.${classInfo.className};

<#if classInfo.checkFields?exists && classInfo.checkFields?keys?size gt 0>
    <#assign check = true />
</#if>

/**
*  ${classInfo.classComment}
*
*  Created by ${classInfo.author} on '${.now?string('yyyy-MM-dd')}'.
*/
public interface ${classInfo.className}Service extends IService<${classInfo.className}> {

    void save${classInfo.className}(${classInfo.className} ${classInfo.className?uncap_first});

    void update${classInfo.className}ById(${classInfo.className} ${classInfo.className?uncap_first});
}
