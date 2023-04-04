import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RouteExtensions } from '@app/core/route.service';
import { ${classInfo.className}ListComponent } from './partial/${classInfo.slashName}-list/${classInfo.slashName}-list.component';
<#if classInfo.frontCodeStyle == "2">
import { CreateEdit${classInfo.className}PageComponent } from './partial/create-edit-${classInfo.slashName}-page/create-edit-${classInfo.slashName}-page.component';
import { ${classInfo.className}DetailsPageComponent } from './partial/${classInfo.slashName}-details-page/${classInfo.slashName}-details-page.component';
    // import { pageTitle } from ''
<#else >
</#if>



const routes: Routes = RouteExtensions.withHost({ path: '', component: ${classInfo.className}ListComponent }, [
<#if classInfo.frontCodeStyle == "2">
    { path: 'details/:id', component: ${classInfo.className}DetailsPageComponent, data: { title: '模板详情' } },
    { path: 'createEdit', component: CreateEdit${classInfo.className}PageComponent, data: { title: '新建模板' } },
    { path: 'createEdit/:id', component: CreateEdit${classInfo.className}PageComponent, data: { title: '编辑模板' } }
<#else >
</#if>
]);

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ${classInfo.className}PageRoutingModule {}