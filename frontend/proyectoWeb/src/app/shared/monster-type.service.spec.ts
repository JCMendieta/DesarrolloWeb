import { TestBed } from '@angular/core/testing';

import { MonsterTypeService } from './monster-type.service';

describe('MonsterTypeService', () => {
  let service: MonsterTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MonsterTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
