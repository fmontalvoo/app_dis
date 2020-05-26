import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {
  static URL: string = 'http://localhost:8080/Tienda_Virtual/srv/factura';
  constructor(private http: HttpClient) { }

  facturar(codigo: number) {
    return new Promise(resolve => {
      this.http.post(`${FacturaService.URL}`, { "codigo": codigo }).subscribe(data => {
        resolve(data);
        
      }, err => {
        console.log(err);
      });
    });
  }
}
