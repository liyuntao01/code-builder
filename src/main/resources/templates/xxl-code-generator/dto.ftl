package ${classInfo.prefix}.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
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
*  ${classInfo.tableDescription}DTO
*
*  Created by ${classInfo.author} on '${.now?string('yyyy-MM-dd')}'.
*/
@Data
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
public class ${classInfo.className}DTO implements Serializable{
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
        @ApiModelProperty("${fieldItem.fieldComment}")
        private ${fieldItem.fieldClass} ${fieldItem.fieldName};
        </#if>
    </#list>


}
