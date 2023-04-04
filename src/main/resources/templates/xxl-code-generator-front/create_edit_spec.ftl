import { async, ComponentFixture, TestBed } from '@angular/core/testing';
<#if classInfo.frontCodeStyle == "2">
import { CreateEdit${classInfo.className}PageComponent } from './create-edit-${classInfo.slashName}-page.component';
<#else >
    import { CreateEdit${classInfo.className}PopupComponent } from './create-edit-${classInfo.slashName}-popup.component';
</#if>

<#if classInfo.frontCodeStyle == "2">
describe('CreateEdit${classInfo.className}PageComponent', () => {
let component: CreateEdit${classInfo.className}PageComponent;
let fixture: ComponentFixture<CreateEdit${classInfo.className}PageComponent>;

    beforeEach(async(() => {
    TestBed.configureTestingModule({
    declarations: [ CreateEdit${classInfo.className}PageComponent ]
    })
    .compileComponents();
    }));

    beforeEach(() => {
    fixture = TestBed.createComponent(CreateEdit${classInfo.className}PageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    });
<#else >
    describe('CreateEdit${classInfo.className}PopupComponent', () => {
    let component: CreateEdit${classInfo.className}PopupComponent;
    let fixture: ComponentFixture<CreateEdit${classInfo.className}PopupComponent>;

        beforeEach(async(() => {
        TestBed.configureTestingModule({
        declarations: [ CreateEdit${classInfo.className}PopupComponent ]
        })
        .compileComponents();
        }));

        beforeEach(() => {
        fixture = TestBed.createComponent(CreateEdit${classInfo.className}PopupComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
        });
</#if>


    it('should create', () => {
    expect(component).toBeTruthy();
    });
});
