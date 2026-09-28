import { TestBed } from '@angular/core/testing';
import { DashboardComponent } from './dashboard.component';
import { provideHttpClient } from '@angular/common/http';

describe('DashboardComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardComponent],

      providers: [
        // Provides HttpClient for DashboardService
        provideHttpClient()
      ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(DashboardComponent);

    const component = fixture.componentInstance;

    expect(component).toBeTruthy();
  });

});
