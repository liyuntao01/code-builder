import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { Logger } from '@app/core/logger.service';
import { LoggerFactory } from '@app/core/logger-factory.service';
import { Dialogs } from '@app/core/dialogs.service';
import { Router } from '@angular/router';
import { QueryMode, NgxQueryComponent } from '@zhongruigroup/ngx-query';
import { BsModalService } from 'ngx-bootstrap/modal';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { NgxDataTableDirective } from '@app/shared/directives/ngx-datatable.directive';
import { finalize } from 'rxjs/operators';
import { ExceptionHandler } from '@app/shared/utils/exception-handler';
import { ${classInfo.className}Service } from '../../shared/${classInfo.slashName}.service';
<#if classInfo.frontCodeStyle == "2">
<#else >
import { CreateEdit${classInfo.className}PopupComponent } from '../create-edit-${classInfo.slashName}-popup/create-edit-${classInfo.slashName}-popup.component';
import { ${classInfo.className}DetailsPopupComponent } from '../${classInfo.slashName}-details-popup/${classInfo.slashName}-details-popup.component';
</#if>

@Component({
selector: 'app-${classInfo.slashName}-list',
templateUrl: './${classInfo.slashName}-list.component.html',
styleUrls: ['./${classInfo.slashName}-list.component.scss']
})
export class ${classInfo.className}ListComponent implements OnInit, AfterViewInit {
log: Logger;
saving: boolean = false;
dataList: Array<any> = [];
    mode: QueryMode = QueryMode.plainCollapse;
    loading: boolean = false;


    pageType: number = 2; // 新增/编辑/详情页面类型 1为弹窗 2为页面


    queryTemplates: any = [
        {
            name: 'Default',
            template: {
                op: 'and',
                rules: [{ field: 'name', op: 'cn' }]
            }
        }
    ];
@ViewChild('appNgxDataTable') ngxDataTable: NgxDataTableDirective;
@ViewChild('ngxQuery') ngxQuery: NgxQueryComponent;
public datatable: DatatableComponent;

    constructor(
        private router: Router,
        private modalService: BsModalService,
        private changeDetectorRef: ChangeDetectorRef,
        private loggerFactory: LoggerFactory,
        private dialogs: Dialogs,
        private exceptionHandler: ExceptionHandler,
        private ${classInfo.variableName}Service: ${classInfo.className}Service
    ) {
        this.log = this.loggerFactory.getLogger('模板页面');
    }

    ngOnInit(): void {}

    ngAfterViewInit(): void {
        this.changeDetectorRef.detectChanges();
    }

    // 获取分页数据
    loadData(event: any) {
        const params: any = event.page;
        params.filter.rules.push({ field: 'id', op: 'eq' });
        this.datatable = event.datatable;
        this.loading = true;
        this.${classInfo.variableName}Service
            .getPaging(params)
            .pipe(finalize(() => (this.loading = false)))
            .subscribe(
                (res) => {
                    this.dataList = res.records;
                    this.datatable.count = res.total;
                },
                (error) => {
                    this.log.error(`模板页面列表获取失败！`, error);
                }
            );
    }

    // 详情
    ${classInfo.variableName}Details(row: any) {
    <#if classInfo.frontCodeStyle == "2">
        this.router.navigate([`${classInfo.microRoute}/${classInfo.slashName}/details/${r"${row.id}"}`]);
    <#else >
        const initialState = { dataClone: row };
        this.modalService.show(${classInfo.className}DetailsPopupComponent, {
        initialState,
        ignoreBackdropClick: true,
        class: 'modal-mlg modal-mlg-top'
        });
    </#if>
    }

    // 添加/编辑
    createEdit${classInfo.className}(row: any, type: number) {
    <#if classInfo.frontCodeStyle == "2">
        // 新建
        if (type === 1) {
        this.router.navigate([`${classInfo.microRoute}/${classInfo.slashName}/createEdit`]);
        } else if (type === 2) {
        // 编辑
        this.router.navigate([`${classInfo.microRoute}/${classInfo.slashName}/createEdit/${r"${row.id}"}`]);
        }
    <#else >
        const initialState = { dataClone: row, type };
        const modalRef = this.modalService.show(CreateEdit${classInfo.className}PopupComponent, {
        initialState,
        ignoreBackdropClick: true,
        class: 'modal-mlg'
        });
        modalRef.content.action.subscribe((value: any) => {
        this.ngxQuery.resetQueryTemplate();
        });
    </#if>
    }

    // 删除
    delete${classInfo.className}(row: any) {
        this.dialogs.confirm('请注意，此操作确认后无法撤销', '确认删除吗？').subscribe(
            () => {
                this.${classInfo.variableName}Service
                    .deleteInfo(row.id)
                    .pipe(finalize(() => (this.loading = false)))
                    .subscribe(
                        (res) => {
                            this.log.info(`模板页面删除成功!`);
                            this.ngxQuery.resetQueryTemplate();
                        },
                        (error) => {
                            this.exceptionHandler.error(this.log, `模板页面删除失败!`, error);
                        }
                    );
            },
            () => this.log.debug('取消')
        );
    }
}