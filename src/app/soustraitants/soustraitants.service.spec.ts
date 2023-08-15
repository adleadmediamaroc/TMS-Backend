import { TestBed } from '@angular/core/testing';

import { SoustraitantsService } from './soustraitants.service';

describe('SoustraitantsService', () => {
  let service: SoustraitantsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SoustraitantsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
