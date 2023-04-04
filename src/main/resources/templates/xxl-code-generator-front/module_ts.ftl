import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgZorroAntdModule } from '@app/ng-zorro-antd.module';
import { SharedModule } from '@app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ${classInfo.className}ListComponent } from './partial/${classInfo.slashName}-list/${classInfo.slashName}-list.component';
import { ${classInfo.className}PageRoutingModule } from './${classInfo.slashName}-routing.module';

<#if classInfo.frontCodeStyle == "2">
import { CreateEdit${classInfo.className}PageComponent } from './partial/create-edit-${classInfo.slashName}-page/create-edit-${classInfo.slashName}-page.component';
import { ${classInfo.className}DetailsPageComponent } from './partial/${classInfo.slashName}-details-page/${classInfo.slashName}-details-page.component';
<#else >
import { CreateEdit${classInfo.className}PopupComponent } from './partial/create-edit-${classInfo.slashName}-popup/create-edit-${classInfo.slashName}-popup.component';
import { ${classInfo.className}DetailsPopupComponent } from './partial/${classInfo.slashName}-details-popup/${classInfo.slashName}-details-popup.component';
</#if>


@NgModule({
declarations: [
${classInfo.className}ListComponent,

<#if classInfo.frontCodeStyle == "2">
    CreateEdit${classInfo.className}PageComponent,
    ${classInfo.className}DetailsPageComponent
<#else >
    CreateEdit${classInfo.className}PopupComponent,
    ${classInfo.className}DetailsPopupComponent,

</#if>

],
imports: [CommonModule, ${classInfo.className}PageRoutingModule, NgZorroAntdModule, SharedModule, FormsModule, ReactiveFormsModule]
})
export class ${classInfo.className}Module {}
