import { TestBed } from '@angular/core/testing';

import { ${classInfo.className}Service } from './${classInfo.slashName}.service';

describe('${classInfo.className}Service', () => {
let service: ${classInfo.className}Service;

beforeEach(() => {
TestBed.configureTestingModule({});
service = TestBed.inject(${classInfo.className}Service);
});

it('should be created', () => {
expect(service).toBeTruthy();
});
});