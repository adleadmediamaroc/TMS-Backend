import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Soustraitants } from './soustraitants';
import { HttpClient } from '@angular/common/http';






@Injectable({
  providedIn: 'root'
})


export class SoustraitantsService {
 

  constructor(private http: HttpClient) { }




   getSuppliers() :Observable<Soustraitants[]>{
    return this.http.get<Soustraitants[]>(`http://localhost:8080/api/suppliers/`);
  }

  
   addEditSupplier(postData: any,selectedSupplier:any) {
    console.log(selectedSupplier)
    if(!selectedSupplier){
    return this.http.post(`http://localhost:8080/api/suppliers/add-supplier`, postData);
  }else{
    return this.http.put(`http://localhost:8080/api/suppliers/update-supplier/${selectedSupplier.userId}`,postData);
  }
  
   }
   
   deleteSupplier(supplierId: number){
    return this.http.delete(`http://localhost:8080/api/suppliers/delete-supplier/${supplierId}`);
   }
}

