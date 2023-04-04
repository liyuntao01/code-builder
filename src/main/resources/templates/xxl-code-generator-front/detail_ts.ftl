import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Logger } from '@app/core/logger.service';
import { LoggerFactory } from '@core/logger-factory.service';
import { plainToClass } from 'class-transformer';
import { ExceptionHandler } from '@app/shared/utils/exception-handler';
import { ${classInfo.className}Service } from '../../shared/${classInfo.slashName}.service';
import { ${classInfo.className}Info } from '../../shared/model/${classInfo.slashName}-info';
<#if classInfo.frontCodeStyle == "2">
import { ActivatedRoute } from '@angular/router';
<#else >
import { BsModalRef } from 'ngx-bootstrap/modal';
</#if>

<#if classInfo.frontCodeStyle == "2">
@Component({
    selector: 'app-${classInfo.slashName}-details-page',
    templateUrl: './${classInfo.slashName}-details-page.component.html',
    styleUrls: ['./${classInfo.slashName}-details-page.component.scss']
})
export class ${classInfo.className}DetailsPageComponent implements OnInit {
    log: Logger;
    id: string;
    title: string = '模板详情';
    ${classInfo.variableName}Info: ${classInfo.className}Info = new ${classInfo.className}Info();
    backLink = '../../';

    constructor(
        private route: ActivatedRoute,
        private loggerFactory: LoggerFactory,
        private ${classInfo.variableName}Service: ${classInfo.className}Service,
        private exceptionHandler: ExceptionHandler
    ) {}

    ngOnInit(): void {
        this.id = this.route.snapshot.params.id;
        this.log = this.loggerFactory.getLogger(`${r"${this.title}"}`);
        this.getDetails();
    }
<#else >
@Component({
    selector: 'app-${classInfo.slashName}-details-popup',
    templateUrl: './${classInfo.slashName}-details-popup.component.html',
    styleUrls: ['./${classInfo.slashName}-details-popup.component.scss']
})
export class ${classInfo.className}DetailsPopupComponent implements OnInit {
    @Input() dataClone: any; // 传值
    @Output() action = new EventEmitter();

    log: Logger;
    title: string = '模板详情';
    ${classInfo.variableName}Info: ${classInfo.className}Info = new ${classInfo.className}Info();

    constructor(
    public activeModal: BsModalRef,
    private loggerFactory: LoggerFactory,
    private ${classInfo.variableName}Service: ${classInfo.className}Service,
    private exceptionHandler: ExceptionHandler
    ) {}

    ngOnInit(): void {
    this.log = this.loggerFactory.getLogger(`${r"${this.title}"}`);
    if (this.dataClone) {
    this.getDetails();
    }
    }
</#if>
// 获取详情
getDetails() {
<#if classInfo.frontCodeStyle == "2">
this.${classInfo.variableName}Service.getDetails(this.id).subscribe(
<#else >
this.${classInfo.variableName}Service.getDetails(this.dataClone.id).subscribe(
</#if>

(res) => {
this.${classInfo.variableName}Info = plainToClass(${classInfo.className}Info, res);
},
(error) => {
this.exceptionHandler.error(this.log, `详情获取失败!`, error);
}
);
}
}

