import { TestBed } from '@angular/core/testing';

import { UserauthenticationService } from './userauthentication.service';

describe('UserauthenticationService', () => {
  let service: UserauthenticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserauthenticationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
