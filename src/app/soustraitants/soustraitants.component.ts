import { Component, OnInit } from '@angular/core';
import { SoustraitantsService } from './soustraitants.service';
import { Soustraitants } from './soustraitants';

@Component({
  selector: 'app-soustraitants',
  templateUrl: './soustraitants.component.html',
  styleUrls: ['./soustraitants.component.scss']
})
export class SoustraitantsComponent implements OnInit {

  suppliers: Soustraitants[] = [];
  displayAddModal=false;

  constructor(private soustraitantsService: SoustraitantsService) { }

  ngOnInit(): void {
    
this.getSuppliers();
  }


  getSuppliers(): void {
    
this.soustraitantsService.getSuppliers()
      .subscribe(
        response =>{
          this.suppliers=response;
          
        }
      );
  }

  showAddModal(){
    this.displayAddModal=true;
  }

    
  hideAddModal(isClosed: boolean){
    this.displayAddModal=!isClosed;
  }


  /*ngOnInit(): void{
    this.getSoustraitantsList();
  }


  getSoustraitantsList(){
    this.soustraitantsService.getSuppliers().subscribe(
      response =>{
        console.log(response);
      }

    )
  }*/



}
