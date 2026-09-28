import { TestBed } from '@angular/core/testing';
import { GuardianComponent } from './guardian.component';
import { provideHttpClient } from '@angular/common/http';

describe('GuardianComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuardianComponent],
      providers: [
        provideHttpClient()
      ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(GuardianComponent);
    const component = fixture.componentInstance;

    expect(component).toBeTruthy();
  });

});
