import { Component,EventEmitter,Input,Output, OnInit, OnChanges} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SoustraitantsService } from '../soustraitants.service';
import { Soustraitants } from '../soustraitants';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-add-edit-soustraitant',
  templateUrl: './add-edit-soustraitant.component.html',
  styleUrls: ['./add-edit-soustraitant.component.scss']
})
export class AddEditSoustraitantComponent implements OnInit,OnChanges{

  @Input() displayAddEditModal: boolean =false;
  @Input() selectedSupplier: any=null;
  @Output() clickClose: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() clickAddEdit: EventEmitter<Soustraitants> =new EventEmitter<Soustraitants>();
  modalType="Add";

  SoustraitantForm=this.fb.group({
    userid:[0,Validators.required],
    company: ["",Validators.required],
    phoneNumber: ["",Validators.required],
    countryid:[0,Validators.required],
    city:["",Validators.required],
    zip:["",Validators.required],
    state:["",Validators.required],
    address:["",Validators.required],
    website:["",Validators.required],
    default_currency:[0,Validators.required],
    codeAuxi:["",Validators.required]

  });

  constructor(private fb:FormBuilder,private soustraitantsService: SoustraitantsService,
    private messageService: MessageService){}

  ngOnInit(): void {
    
  }
 
  
  ngOnChanges(): void {
  if(this.selectedSupplier){
    this.modalType="Modifier";
     this.SoustraitantForm.patchValue(this.selectedSupplier);
  }else{
    this.SoustraitantForm.reset();
    this.modalType="Ajouter";
  }
  
}
  closeModal(){
    this.SoustraitantForm.reset();
    this.clickClose.emit(true);

  }

  addEditSupplier(){
    const newSubcontractor = this.SoustraitantForm.value;
    const sanitizedSubcontractor = {
      ...newSubcontractor,
      userid: newSubcontractor.userid || 0,
      company: newSubcontractor.company || '',
      phoneNumber: newSubcontractor.phoneNumber || '',
      countryid: newSubcontractor.countryid || 0,
      city: newSubcontractor.city || '',
      zip: newSubcontractor.zip || '',
      state: newSubcontractor.state || '',
      address: newSubcontractor.address || '',
      website: newSubcontractor.website || '',
      default_currency: newSubcontractor.default_currency || 0,
      codeAuxi: newSubcontractor.codeAuxi || '',
    };
    this.clickAddEdit.emit(sanitizedSubcontractor);
    this.closeModal();
    const msg =this.modalType==='Add'? 'Sous-traitant ajouté' :'Sous-traitant mis à jour'
    this.messageService.add({severity:'success', summary:'Success', detail: msg});
    

  //console.log(this.SoustraitantForm.value)
    this.soustraitantsService.addEditSupplier(newSubcontractor,this.selectedSupplier).subscribe(
      response => {
        console.log('Sous-traitant ajouté avec succès !', response);
        //this.clickAddEdit.emit(response);
        this.SoustraitantForm.reset();
        this.clickClose.emit(true);

      },
      error => {
        //this.messageService.add({severity:'error', summary:'Error', detail:'error'});
        console.error('Erreur occured');
      }
    );

  }




}
