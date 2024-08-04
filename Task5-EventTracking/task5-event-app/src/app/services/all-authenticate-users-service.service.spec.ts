import { TestBed } from '@angular/core/testing';

import { AllAuthenticateUsersServiceService } from './all-authenticate-users-service.service';

describe('AllAuthenticateUsersServiceService', () => {
  let service: AllAuthenticateUsersServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllAuthenticateUsersServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
