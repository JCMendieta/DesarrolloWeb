import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecorativeItemListComponent } from './decorative-item-list.component';

describe('DecorativeItemListComponent', () => {
  let component: DecorativeItemListComponent;
  let fixture: ComponentFixture<DecorativeItemListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DecorativeItemListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DecorativeItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
