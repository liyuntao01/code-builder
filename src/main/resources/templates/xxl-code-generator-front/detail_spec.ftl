import { async, ComponentFixture, TestBed } from '@angular/core/testing';
<#if classInfo.frontCodeStyle == "2">
import { ${classInfo.className}DetailsPageComponent } from './${classInfo.slashName}-details-page.component';
<#else >
import { ${classInfo.className}DetailsPopupComponent } from './${classInfo.slashName}-details-popup.component';
</#if>

<#if classInfo.frontCodeStyle == "2">
describe('${classInfo.className}DetailsPageComponent', () => {
    let component: ${classInfo.className}DetailsPageComponent;
    let fixture: ComponentFixture<${classInfo.className}DetailsPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
        declarations: [ ${classInfo.className}DetailsPageComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(${classInfo.className}DetailsPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    <#else >
describe('${classInfo.className}DetailsPopupComponent', () => {
    let component: ${classInfo.className}DetailsPopupComponent;
    let fixture: ComponentFixture<${classInfo.className}DetailsPopupComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
        declarations: [ ${classInfo.className}DetailsPopupComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(${classInfo.className}DetailsPopupComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    </#if>


    it('should create', () => {
    expect(component).toBeTruthy();
    });
});
