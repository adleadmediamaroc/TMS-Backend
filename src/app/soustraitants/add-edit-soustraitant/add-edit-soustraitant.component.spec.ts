import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditSoustraitantComponent } from './add-edit-soustraitant.component';

describe('AddEditSoustraitantComponent', () => {
  let component: AddEditSoustraitantComponent;
  let fixture: ComponentFixture<AddEditSoustraitantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditSoustraitantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEditSoustraitantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
