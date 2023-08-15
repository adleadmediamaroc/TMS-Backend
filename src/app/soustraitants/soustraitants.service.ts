import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Soustraitants} from './soustraitants'
import { of } from 'rxjs';





@Injectable({
  providedIn: 'root'
})
export class SoustraitantsService {
  private mockSuppliers: Soustraitants[] = [
    {
      "company": "nom1 ",
      "phoneNumber": "0615141821",
      "countryId": 1,
      "city": "kech",
      "zip": "40001",
      "state": "État1",
      "address": "Adresse1",
      "website": "Site web1",
      "defaultCurrencyId": 1,
      "codeAuxi": "01"
    },
    {
      
      "company": "Nom2",
      "phoneNumber": "0615567479",
      "countryId": 2,
      "city": "casa",
      "zip": "40002",
      "state": "État2",
      "address": "Adresse2",
      "website": "Site web2",
      "defaultCurrencyId": 2,
      "codeAuxi": "02"
    },
    {
      "company": "Nom3 ",
      "phoneNumber": "0615781821",
      "countryId": 3,
      "city": "rabat",
      "zip": "40003",
      "state": "État3",
      "address": "Adresse3",
      "website": "Site web3",
      "defaultCurrencyId": 3,
      "codeAuxi": "03"
    },
    {
      
      "company": "Nom4",
      "phoneNumber": "0615500479",
      "countryId": 4,
      "city": "tanger",
      "zip": "40004",
      "state": "État4",
      "address": "Adresse4",
      "website": "Site web4",
      "defaultCurrencyId": 4,
      "codeAuxi": "04"
    }
  ];
  constructor() { }

  getSuppliers(): Observable<Soustraitants[]> {
    return of(this.mockSuppliers);
  } 

  /*constructor(private http: HttpClient) { }

  getSuppliers() :Observable<Soustraitants[]>{
    return this.http.get<Soustraitants[]>('http://localhost:8080/api/suppliers/');
  }

  addSupplier(supplierData: Soustraitants) {
    return this.http.post('http://localhost:8080/api/suppliers/add-supplier', supplierData);
  }

  updateSupplier(id:number, supplierData:Soustraitants ) {
    return this.http.put(`http://localhost:8080/api/suppliers/update-supplier/${id}`, supplierData);
  }

  deleteSupplier(id:number) {
    return this.http.delete(`http://localhost:8080/api/suppliers/delete-supplier/${id}`);
  }
 
  // Récupération des détails d'un fournisseur par ID
  getSupplierById(id:number) {
    
   
    return this.http.get(`http://localhost:8080/api/suppliers/${id}`);
      }
    
    
    // Mise à jour du statut actif/inactif d'un fournisseur
      updateSupplierActiveStatus(id:number, active:boolean) {
        
       
    return this.http.put(`http://localhost:8080/api/suppliers/${id}/active`, { active });
      }
    
    
    // Compter le nombre total de fournisseurs
      
     
    countTotalSuppliers() {
        
       
    return this.http.get('http://localhost:8080/api/suppliers/count-total');
      }
    
    // Compter le nombre de fournisseurs actifs
      
     
    countActiveSuppliers() {
        
       
    return this.http.get('http://localhost:8080/api/suppliers/count-active');
      }
    
    // Compter le nombre de fournisseurs inactifs
      countInactiveSuppliers() {
        
       
    return this.http.get('http://localhost:8080/api/suppliers/count-inactive');
      }

    */
  

}
