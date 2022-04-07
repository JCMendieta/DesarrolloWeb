import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecorativeItemViewComponent } from './decorative-item-view.component';

describe('DecorativeItemViewComponent', () => {
  let component: DecorativeItemViewComponent;
  let fixture: ComponentFixture<DecorativeItemViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DecorativeItemViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DecorativeItemViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
