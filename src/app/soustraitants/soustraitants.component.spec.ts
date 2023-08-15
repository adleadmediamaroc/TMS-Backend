import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoustraitantsComponent } from './soustraitants.component';

describe('SoustraitantsComponent', () => {
  let component: SoustraitantsComponent;
  let fixture: ComponentFixture<SoustraitantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SoustraitantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SoustraitantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
