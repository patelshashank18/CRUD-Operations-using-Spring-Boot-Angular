import { TestBed } from '@angular/core/testing';
import { ChildComponent } from './child.component';
import { provideHttpClient } from '@angular/common/http';

describe('ChildComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChildComponent],
      providers: [
        provideHttpClient()
      ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(ChildComponent);
    const component = fixture.componentInstance;

    expect(component).toBeTruthy();
  });

});
