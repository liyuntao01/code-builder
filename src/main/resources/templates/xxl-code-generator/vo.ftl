package ${classInfo.prefix}.vo;

import java.io.Serializable;

import lombok.Data;
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.fieldClass == "Date">
            <#assign importDdate = true />
        </#if>
        <#if fieldItem.fieldClass == "BigDecimal">
            <#assign importBigDecimal = true />
        </#if>
    </#list>
</#if>
<#if importDdate?? && importDdate>
import java.util.Date;
</#if>

<#if importBigDecimal?? && importBigDecimal>
import java.math.BigDecimal;
</#if>

/**
*  ${classInfo.tableDescription}VO
*
*    @author  ${classInfo.author} on '
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
*/
@Data
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    public class ${classInfo.className}VO implements Serializable{
</#if>

<#list classInfo.fieldList as fieldItem >
    <#if fieldItem.fieldName == "deleted"
    || fieldItem.fieldName == "createdById"
    || fieldItem.fieldName == "createdAt"
    || fieldItem.fieldName == "updatedById"
    || fieldItem.fieldName == "updatedAt"
    || fieldItem.fieldName == "deletedAt"
    || fieldItem.fieldName == "deletedById">

    <#else >
        /**
        * ${fieldItem.fieldComment}
        */
        private ${fieldItem.fieldClass} ${fieldItem.fieldName};
    </#if>
</#list>

}
