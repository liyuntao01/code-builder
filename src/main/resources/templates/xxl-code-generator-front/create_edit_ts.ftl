<#if classInfo.frontCodeStyle == "2">
import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
<#else >
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
</#if>
import { ValidateEncapsulation } from '@app/shared/utils/class-validator';
import { Logger } from '@core/logger.service';
import { finalize } from 'rxjs/operators';
import { ExceptionHandler } from '@app/shared/utils/exception-handler';
import { LoggerFactory } from '@core/logger-factory.service';
import { plainToClass } from 'class-transformer';
<#if classInfo.frontCodeStyle == "2">
import { ActivatedRoute } from '@angular/router';
<#else >
import { BsModalRef } from 'ngx-bootstrap/modal';
</#if>
import { ${classInfo.className}Service } from '../../shared/${classInfo.slashName}.service';
import { ${classInfo.className}Info } from '../../shared/model/${classInfo.slashName}-info';


<#if classInfo.frontCodeStyle == "2">
@Component({
selector: 'app-create-edit-${classInfo.slashName}-page',
templateUrl: './create-edit-${classInfo.slashName}-page.component.html',
styleUrls: ['./create-edit-${classInfo.slashName}-page.component.scss']
})

export class CreateEdit${classInfo.className}PageComponent extends ValidateEncapsulation implements OnInit, AfterViewInit {
log: Logger;
saving: boolean = false;
title: string;
plainErrors: any = {};
id: string;
${classInfo.variableName}Info: ${classInfo.className}Info = new ${classInfo.className}Info();
constructor(
private route: ActivatedRoute,
private loggerFactory: LoggerFactory,
private changeDetectorRef: ChangeDetectorRef,
private exceptionHandler: ExceptionHandler,
private ${classInfo.variableName}Service: ${classInfo.className}Service
) {
super();
}

ngOnInit(): void {
this.id = this.route.snapshot.params.id;
this.title = this.route.snapshot.params.id !== undefined ? '编辑模板' : '新增模板';
this.log = this.loggerFactory.getLogger(`${r"${this.title}"}`);
if (this.id) {
this.getDetails();
}
}

ngAfterViewInit(): void {
this.changeDetectorRef.detectChanges();
}

// 获取详情
getDetails() {
this.${classInfo.variableName}Service.getDetails(this.id).subscribe(
(res) => {
this.${classInfo.variableName}Info = plainToClass(${classInfo.className}Info, res);
},
(error) => {
this.log.error(`详情获取失败!`, error);
}
);
}
<#else >
@Component({
selector: 'app-create-edit-${classInfo.slashName}-popup',
templateUrl: './create-edit-${classInfo.slashName}-popup.component.html',
styleUrls: ['./create-edit-${classInfo.slashName}-popup.component.scss']
})

export class CreateEdit${classInfo.className}PopupComponent extends ValidateEncapsulation implements OnInit {
@Input() dataClone: any; // 编辑传值
@Input() type: any; // 1是新增 2是编辑
@Output() action = new EventEmitter();

log: Logger;
saving: boolean = false;
title: string;
plainErrors: any = {};
${classInfo.variableName}Info: ${classInfo.className}Info = new ${classInfo.className}Info();
constructor(
public activeModal: BsModalRef,
private loggerFactory: LoggerFactory,
private exceptionHandler: ExceptionHandler,
private ${classInfo.variableName}Service: ${classInfo.className}Service
) {
super();
}

ngOnInit(): void {
this.title = this.dataClone ? '编辑模板页面' : '新增模板页面';
this.log = this.loggerFactory.getLogger(`${r"${this.title}"}`);
if (this.dataClone) {
this.${classInfo.variableName}Info = plainToClass(${classInfo.className}Info, this.dataClone);
}
}
</#if>

// 提交
submit() {
if (this.saving) {
return;
}
this.saving = true;
this.validateFormAll(this.${ classInfo.variableName }Info, false, '${classInfo.variableName}Info').then((validateError: any) => {
this.plainErrors = validateError;
if (Object.keys(validateError).length > 0) {
this.saving = false;
this.log.warn('信息填写不完整，请检查。');
return;
}
this.diseaseinfoService
.createOrEdit(this.diseaseinfoInfo)
.subscribe(
(res) => {
this.log.info(`保存成功!`);
<#if classInfo.frontCodeStyle == "2">
history.go(-1);
<#else >
this.action.emit(this.diseaseinfoInfo)
this.activeModal.hide();
</#if>
},
(error) => {
this.saving = false;
this.exceptionHandler.error(this.log, `保存失败!`, error);
}
);
});
}

// 单个验证
validateForm() {
this.validateFormAll(this.${classInfo.variableName}Info, true, '${classInfo.variableName}Info').then((errors: any) => {
this.plainErrors = errors;
});
}

// 获取验证信息
getMessage(key: string) {
return this.plainErrors[key];
}

<#if classInfo.frontCodeStyle == "2">
    goBack() {
    history.go(-1);
    }
</#if>
}