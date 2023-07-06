import { TestBed } from '@angular/core/testing';

import { ConsumeritemService } from './consumeritem.service';

describe('ConsumeritemService', () => {
  let service: ConsumeritemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsumeritemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
