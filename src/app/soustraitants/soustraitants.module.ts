import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { AddEditSoustraitantModule } from './add-edit-soustraitant/add-edit-soustraitant.module';
import { SoustraitantsComponent } from './soustraitants.component';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    SoustraitantsComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    TableModule ,
    AddEditSoustraitantModule,
    ButtonModule,
    HttpClientModule,
    ToastModule,
    ConfirmDialogModule,
    FormsModule
  ],
  exports: [
    SoustraitantsComponent
  ],
  providers: [MessageService,ConfirmationService]
})
export class SoustraitantsModule { }
