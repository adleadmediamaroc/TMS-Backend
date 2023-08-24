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
    return this.http.get<Soustraitants[]>('http://localhost:8080/api/suppliers/');
  }

  
 
  

}

