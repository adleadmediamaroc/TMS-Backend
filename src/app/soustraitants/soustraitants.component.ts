import { Component, OnInit } from '@angular/core';
import { SoustraitantsService } from './soustraitants.service';
import { Soustraitants } from './soustraitants';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-soustraitants',
  templateUrl: './soustraitants.component.html',
  styleUrls: ['./soustraitants.component.scss']
})

export class SoustraitantsComponent implements OnInit {
  countryNames:{[key:number]:string}={};
  currencyNames:{[key:number]:string}={};
  suppliers: Soustraitants[] = [];
  displayAddModal=false;

  constructor(
     private soustraitantsService: SoustraitantsService,
     private http: HttpClient
     ) { }

  ngOnInit(): void{
    this.getSoustraitantsList();
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
      }

    )
    }

  showAddModal(){
    this.displayAddModal=true;
  }

    
  hideAddModal(isClosed: boolean){
    this.displayAddModal=!isClosed;
  }


}
