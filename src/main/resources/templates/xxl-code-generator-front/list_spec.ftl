import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ${classInfo.className}ListComponent } from './${classInfo.slashName}-list.component';

describe('${classInfo.className}PageListComponent', () => {
    let component: ${classInfo.className}ListComponent;
    let fixture: ComponentFixture<${classInfo.className}ListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ${classInfo.className}ListComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(${classInfo.className}ListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
    expect(component).toBeTruthy();
    });
});