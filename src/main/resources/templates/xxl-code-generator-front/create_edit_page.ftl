
<#if classInfo.frontCodeStyle == "2">
    <div class="m-portlet">
        <!-- title -->
        <div class="list-header-box header-height">
            <div class="list-top-title">{{ title }}</div>
            <div class="top-right-btn">
                <app-white-btn
                        [borderStyle]="'btn-border-circle'"
                        [btnTitle]="'取 消'"
                        (click)="goBack()"
                        class="list-left-btn"
                ></app-white-btn>
                <app-blue-btn
                        [borderStyle]="'btn-border-circle'"
                        [btnTitle]="'保 存'"
                        (click)="submit()"
                        [saving]="saving"
                ></app-blue-btn>
            </div>
        </div>
        <div class="margin-top-19">
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
            <nz-form-item>
                <nz-form-label [nzSm]="3" [nzXs]="24" nzRequired>${fieldItem.fieldComment}</nz-form-label>
                <nz-form-control [nzSm]="8" [nzXs]="24">
                    <input nz-input placeholder="请输入" [(ngModel)]="${classInfo.variableName}Info.${fieldItem.fieldName}" (ngModelChange)="validateForm()" />
                    <span class="error_message">
                         {{ getMessage('${classInfo.variableName}Info.${fieldItem.fieldName}') }}
                    </span>
                </nz-form-control>
            </nz-form-item>
            </#if>
            </#list>
        </div>
    </div>



<#else >

    <div class="modal-spin-wrap">
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
                    <div class="content-item">
                        <nz-form-item>
                            <nz-form-label [nzSm]="4" [nzXs]="24" nzRequired>${fieldItem.fieldComment}</nz-form-label>
                            <nz-form-control [nzSm]="16" [nzXs]="24">
                                <input
                                        nz-input
                                        [(ngModel)]="${classInfo.variableName}Info.${fieldItem.fieldName}"
                                        (ngModelChange)="validateForm()"
                                        placeholder="请输入${fieldItem.fieldComment}"
                                />
                            </nz-form-control>
                        </nz-form-item>
                        <span class="error_message">{{ getMessage('${classInfo.variableName}Info.${fieldItem.fieldName}') }}</span>
                    </div>
                </#if>
            </#list>

        </div>
        <div class="modal-footer">
            <app-white-btn
                    [btnTitle]="'取 消'"
                    [borderStyle]="'btn-border-circle'"
                    (click)="activeModal.hide()"
            ></app-white-btn>
            <app-blue-btn
                    [btnTitle]="'保 存'"
                    [borderStyle]="'btn-border-circle'"
                    (click)="submit()"
            ></app-blue-btn>
        </div>
    </div>

</#if>
