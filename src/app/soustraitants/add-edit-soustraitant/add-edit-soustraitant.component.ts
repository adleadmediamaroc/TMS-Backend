import { Component,EventEmitter,Input,Output, OnInit} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-add-edit-soustraitant',
  templateUrl: './add-edit-soustraitant.component.html',
  styleUrls: ['./add-edit-soustraitant.component.scss']
})
export class AddEditSoustraitantComponent implements OnInit{

  @Input() displayAddModal: boolean =false;
  @Output() clickClose: EventEmitter<boolean> = new EventEmitter<boolean>();


  SoustraitantForm=this.fb.group({
    company: ["",Validators.required],
    phoneNumber: ["",Validators.required],
    number: ["",Validators.required],
    countryId:[0,Validators.required],
    city:["",Validators.required],
    zip:["",Validators.required],
    state:["",Validators.required],
    address:["",Validators.required],
    website:["",Validators.required],
    defaultCurrencyId:[0,Validators.required],
    codeAuxi:["",Validators.required]

  });
  constructor(private fb:FormBuilder){}

  ngOnInit(): void {
    
  }

  closeModal(){
    this.clickClose.emit(true);

  }
  addSoustraitant(){
    console.log(this.SoustraitantForm.value)

  }

 


}
