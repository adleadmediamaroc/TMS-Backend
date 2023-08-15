import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddEditSoustraitantComponent } from './add-edit-soustraitant.component';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';

@NgModule({
  declarations: [
    AddEditSoustraitantComponent
  ],
  imports: [
    CommonModule,
    DialogModule,
    BrowserAnimationsModule,
    ButtonModule,
    ReactiveFormsModule,
    InputTextModule,
    InputNumberModule
  ],
  exports: [
    AddEditSoustraitantComponent
  ]
})
export class AddEditSoustraitantModule { }
