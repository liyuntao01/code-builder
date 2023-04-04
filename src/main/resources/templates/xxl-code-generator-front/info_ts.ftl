import { IsNotEmpty, MaxLength, MinLength } from 'class-validator';
export class ${classInfo.className}Info {
    id?: number; // id

<#list classInfo.fieldList as fieldItem >
    <#if fieldItem.fieldName == "id"
    || fieldItem.fieldName == "deleted"
    || fieldItem.fieldName == "createdById"
    || fieldItem.fieldName == "tenantId"
    || fieldItem.fieldName == "createdAt"
    || fieldItem.fieldName == "updatedById"
    || fieldItem.fieldName == "updatedAt"
    || fieldItem.fieldName == "deletedAt"
    || fieldItem.fieldName == "deletedById">
    <#else >
    @IsNotEmpty({ message: '${fieldItem.fieldComment}不能为空', groups: ['${classInfo.className}Info'] })
    @MaxLength(20, { message: '请输入20个以内的字符', groups: ['${classInfo.className}Info'] })
    @MinLength(1, { message: '${fieldItem.fieldComment}不能为空', groups: ['${classInfo.className}Info'] })
    ${fieldItem.fieldName}: <#if fieldItem.fieldClass == "Long" || fieldItem.fieldClass == "Integer">number<#else >string</#if>;
    </#if>
</#list>
}
