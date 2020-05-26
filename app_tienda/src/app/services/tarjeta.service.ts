import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tarjeta } from '../model/tarjeta.model';

@Injectable({
  providedIn: 'root'
})
export class TarjetaService {
  static URL: string = 'http://localhost:8080/Tienda_Virtual/srv/tarjeta';
  private tarjetas:Array<Tarjeta>;

  constructor(private http: HttpClient) { 
    this.tarjetas = new Array<Tarjeta>();
  }

  listarTarjetas(codigo:number) {
    this.tarjetas = new Array<Tarjeta>();
    this.http.get(`${TarjetaService.URL}?codigo=${codigo}`).subscribe((data) => {
      for (const index in data) {
        let tarjeta = new Tarjeta();
        let datos = data[index];
        tarjeta.setCodigo(datos['codigo']);

        tarjeta.setNumeroTarjeta(datos['numeroTarjeta']);

        tarjeta.setFechaExpiracion(datos['fechaExpiracion']);
        tarjeta.setCodigoSeguridad(datos['codigoSeguridad']);
        this.tarjetas.push(tarjeta);
      }
    });
    return this.tarjetas;
  }
}
