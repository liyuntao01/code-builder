<div class="m-portlet form-content">
    <!-- title -->
    <div class="list-header-box">
        <div class="list-top-title">模板页面</div>
    </div>
    <!-- query -->
    <div class="search-box">
        <ngx-query
                #ngxQuery
                [mode]="mode"
                [datePickerReadonly]="true"
                class="full-screen no-header"
                [queryTemplates]="queryTemplates"
                [columnNumber]="4"
        >
            <ngx-query-field [name]="'name'" [label]="'模板名称'" [type]="'string'">
                <ng-template ngx-query-value-input-template let-rule="rule" let-dataIndex="dataIndex">
                    <input nz-input placeholder="请输入" [(ngModel)]="rule.datas[dataIndex]" class="input-common"/>
                </ng-template>
            </ngx-query-field>
        </ngx-query>
    </div>
    <!-- button -->
    <div class="list-top-btns">
        <app-blue-btn
                (click)="createEdit${classInfo.className}(null, 1)"
                [btnTitle]="'新建'"
                [borderStyle]="'btn-border-circle'"
                [icon]="'la la-plus'"
        ></app-blue-btn>
    </div>
    <!-- table -->
    <div class="m-portlet__body">
        <ngx-datatable
                #dt
                class="material"
                [headerHeight]="60"
                [scrollbarH]="true"
                [rows]="dataList"
                [loadingIndicator]="loading"
                #appNgxDataTable="NgxDataTableDirective"
                appNgxDataTable
                [ngxQuery]="ngxQuery"
                (data)="loadData($event)"
                [saveState]="false"
        >
            <ngx-datatable-footer [datatable]="datatable"></ngx-datatable-footer>
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
                    <ngx-datatable-column
                            name="${fieldItem.fieldComment}"
                            prop="${fieldItem.fieldName}"
                            [sortable]="false"
                            headerClass="text-left"
                            cellClass="text-left"
                    >
                        <ng-template let-row="row" ngx-datatable-cell-template>
                            <div class="text-ellipsis" nz-tooltip [nzTooltipPlacement]="'topLeft'"
                                 [nz-tooltip]="row.${classInfo.variableName} | isNull">
                                {{ row.${fieldItem.fieldName} | isNull }}
                            </div>
                        </ng-template>
                    </ngx-datatable-column>

                </#if>
            </#list>

            <ngx-datatable-column
                    headerClass="datatable-header-cell-actions text-center"
                    cellClass="text-center"
                    name="操作"
                    [sortable]="false"
            >
                <ng-template let-column="column" name="操作" ngx-datatable-header-template>
                    <div class="cell-text cell-operation operation-text-wraper">
                        <div class="cell-left-txt operation-text">操作</div>
                    </div>
                </ng-template>
                <ng-template let-row="row" ngx-datatable-cell-template>
                    <app-list-btn [btnTitle]="'详情'" (click)="${classInfo.variableName}Details(row)"></app-list-btn>
                    <span class="btn-center-line">|</span>
                    <app-list-btn [btnTitle]="'编辑'" (click)="createEdit${classInfo.className}(row, 2)"></app-list-btn>
                    <span class="btn-center-line">|</span>
                    <app-list-btn [btnTitle]="'删除'" (click)="delete${classInfo.className}(row)"></app-list-btn>
                </ng-template>
            </ngx-datatable-column>
        </ngx-datatable>
    </div>
</div>
