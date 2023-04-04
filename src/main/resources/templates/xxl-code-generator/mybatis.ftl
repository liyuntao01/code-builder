<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${classInfo.prefix}.mapper.${classInfo.className}Mapper">

    <resultMap id="${classInfo.className}" type="${classInfo.prefix}.${classInfo.appName}.bean.${classInfo.className}" >
    <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem ><#if fieldItem.columnName == "tenant_id"><#else>
        <result column="${fieldItem.columnName}" property="${fieldItem.fieldName}" />
    </#if>
    </#list>
    </#if>
    </resultMap>

    <sql id="Base_Column_List">
    <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem ><#if fieldItem.columnName == "tenant_id"><#else>
        `${fieldItem.columnName}`<#if fieldItem_has_next>,</#if>
        </#if>
    </#list>
    </#if>
    </sql>

    <sql id="Alias_Column_List">
        <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
            <#list classInfo.fieldList as fieldItem ><#if fieldItem.columnName == "tenant_id"><#else>
        ${classInfo.tableName}.`${fieldItem.columnName}`<#if fieldItem_has_next>,</#if>
        </#if>
            </#list>
        </#if>
    </sql>

</mapper>
