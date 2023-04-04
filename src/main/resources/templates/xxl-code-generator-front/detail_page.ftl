<#if classInfo.frontCodeStyle == "2">
    <div class="m-portlet form-content">
        <!-- title -->
        <div class="list-header-box header-height">
            <div class="list-top-title">{{ title }}</div>
            <div class="top-right-btn">
                <app-white-btn [btnTitle]="'返 回'" [borderStyle]="'btn-border-circle'"
                               [routerLink]="backLink"></app-white-btn>
            </div>
        </div>
        <div class="info-content">
            <#list classInfo.fieldList as fieldItem >
                <#if fieldItem.fieldName == "deleted"
                || fieldItem.fieldName == "createdById"
                || fieldItem.fieldName == "tenantId"
                || fieldItem.fieldName == "createdAt"
                || fieldItem.fieldName == "updatedById"
                || fieldItem.fieldName == "updatedAt"
                || fieldItem.fieldName == "deletedAt"
                || fieldItem.fieldName == "deletedById">
                <#else >
                    <div class="info-content-group row">
                        <label class="group-label col-md-1">${fieldItem.fieldComment}:</label>
                        <span class="group-detail col-md-10">{{ ${classInfo.variableName}Info.${fieldItem.fieldName} | isNull }}</span>
                    </div>
                </#if>
            </#list>
        </div>
    </div>


<#else >

    <div class="modal-header">
        <h5 class="modal-title">{{ title }}</h5>
        <button type="button" class="close" (click)="activeModal.hide()" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="modal-body-cus">
        <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.fieldName == "deleted"
        || fieldItem.fieldName == "createdById"
        || fieldItem.fieldName == "tenantId"
        || fieldItem.fieldName == "createdAt"
        || fieldItem.fieldName == "updatedById"
        || fieldItem.fieldName == "updatedAt"
        || fieldItem.fieldName == "deletedAt"
        || fieldItem.fieldName == "deletedById">
        <#else >
            <div class="form-body row">
                <label class="text-label col-md-2">
                    <span>${fieldItem.fieldComment}：</span>
                </label>
                <div class="flex-input col-md-10">
                    <span class="group-detail">{{ ${classInfo.variableName}Info.${fieldItem.fieldName} | isNull }}</span>
                </div>
            </div>

        </#if>
        </#list>
    </div>

</#if>
