import { Component, OnInit } from '@angular/core';
import { SoustraitantsService } from './soustraitants.service';
import { Soustraitants } from './soustraitants';
import { HttpClient } from '@angular/common/http';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-soustraitants',
  templateUrl: './soustraitants.component.html',
  styleUrls: ['./soustraitants.component.scss']
})

export class SoustraitantsComponent implements OnInit {
  countryNames:{[key:number]:string}={};
  currencyNames:{[key:number]:string}={};
  suppliers: Soustraitants[] = [];
  displayAddEditModal=false;
  selectedSupplier: any=null;
  searchCompany: string = '';


  constructor(
     private soustraitantsService: SoustraitantsService,
     private http: HttpClient,
     private confirmationService: ConfirmationService,
     private messageService: MessageService
     ) { }

  /*ngOnInit(): void{
    this.getSoustraitantsList();
    this.getCountryNames();
    this.getCurrencyNames();
  }*/

  ngOnInit(): void {
    const storedSuppliers = localStorage.getItem('suppliers');
    if (storedSuppliers) {
      this.suppliers = JSON.parse(storedSuppliers);
    } else {
      this.getSoustraitantsList();
    }
    
    this.getCountryNames();
    this.getCurrencyNames();

    
  }
  


  getCurrencyNames(){
    this.http.get<any>(' http://localhost:8080/api/currencies/').subscribe(
      response => {
        response.forEach((currency: any) => {
          this.currencyNames[currency.default_currency] = currency.name;
          this.countryNames[currency.default_currency] = currency.currencyid;
        });
      },
      error => {
        console.error('Erreur lors de la récupération des noms de pays :', error);
      }
    );
  }


   getCountryNames(){
    this.http.get<any>(' http://localhost:8080/api/countries/').subscribe(
      response => {
        response.forEach((country: any) => {
          this.countryNames[country.countryid] = country.short_name;
          this.countryNames[country.countryid] = country.countryid;
        });
      },
      error => {
        console.error('Erreur lors de la récupération des noms de pays :', error);
      }
    );
  }

  getSoustraitantsList(){
    this.soustraitantsService.getSuppliers().subscribe(
      response =>{
        this.suppliers=response;
        localStorage.setItem('suppliers', JSON.stringify(this.suppliers));
        //console.log(response);
      }

    )
    }

  showAddModal(){
    this.displayAddEditModal=true;
    this.selectedSupplier=null;
  }

    
  hideAddModal(isClosed: boolean){
    this.displayAddEditModal=!isClosed;
  }
  saveorUpdateSupplierList(newData:any){
    console.log("Before adding new supplier:", this.suppliers);
    //console.log(newData)
    /*if(newData.useridid===this.selectedSupplier.userid){
    const supplierIndex=this.suppliers.findIndex(data => data.userid === newData.userid);
    this.suppliers[supplierIndex]=newData;
    }else{
    this.suppliers.unshift(newData);
    }*/
  
    this.suppliers.unshift( newData);
  // Vérifiez si la liste dépasse le nombre de fournisseurs par page
  const suppliersPerPage = 4; // Nombre de fournisseurs par page
  if (this.suppliers.length > suppliersPerPage) {
    this.suppliers.pop(); // Supprime le dernier fournisseur pour maintenir la pagination
  }
  console.log("After adding new supplier:", this.suppliers);
  // Mettez à jour la liste dans le stockage local
  localStorage.setItem('suppliers', JSON.stringify(this.suppliers));
    //this.getSoustraitantsList();
  }

  showEditModal(supplier:Soustraitants){
  this.displayAddEditModal=true;
  this.selectedSupplier=supplier;

  }

  deleteSupplier(supplier: Soustraitants){
    this.confirmationService.confirm({
      message:'Êtes-vous sûr de vouloir supprimer ce sous-traitant?',
      accept:()=>{

          // Recherche de l'index de l'élément à supprimer dans le tableau
      const index = this.suppliers.indexOf(supplier);
    
      // Vérification de l'index pour éviter les erreurs
      if (index !== -1) {
        // Suppression de l'élément du tableau
        this.suppliers.splice(index, 1);
        
        // Affichage du message de succès
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Supprimé avec succès' });
      }

        /*//Actual logic to perform a confirmation 
         this.soustraitantsService.deleteSupplier(supplier.userid).subscribe(
          response =>{
                this.getSoustraitantsList();
                this.messageService.add({severity:'success', summary:'Success', detail: 'supprimer avec succès'});
          },
          error =>{
            this.messageService.add({severity:'error', summary:'Error', detail: 'erreur'});
          }
         )*/
        
        }
    })

  }
    
  searchByCompany() {
    if (this.searchCompany.trim() === '') {
      this.suppliers = []; // Effacez les résultats si le champ est vide
      return;
    }

    this.suppliers = this.suppliers.filter(
      supplier => supplier.company.toLowerCase().includes(this.searchCompany.toLowerCase())
    );
  }

}
