import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { AddEditSoustraitantModule } from './add-edit-soustraitant/add-edit-soustraitant.module';
import { SoustraitantsComponent } from './soustraitants.component';
import { ButtonModule } from 'primeng/button';












@NgModule({
  declarations: [
    SoustraitantsComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    TableModule ,
    AddEditSoustraitantModule,
    ButtonModule
  ],
  exports: [
    SoustraitantsComponent
  ]
})
export class SoustraitantsModule { }
