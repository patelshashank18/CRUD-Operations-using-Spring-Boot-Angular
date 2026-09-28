import { TestBed } from '@angular/core/testing';
import { ChildService } from './child.service';
import { provideHttpClient } from '@angular/common/http';

describe('ChildService', () => {

  let service: ChildService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ChildService,
        provideHttpClient()
      ]
    });

    service = TestBed.inject(ChildService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

});
