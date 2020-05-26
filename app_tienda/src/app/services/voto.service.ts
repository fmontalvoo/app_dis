import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Voto } from '../model/voto.model';

@Injectable({
  providedIn: 'root'
})
export class VotoService {
  static URL: string = 'http://localhost:8080/Tienda_Virtual/srv/voto';

  constructor(private http: HttpClient) { }

  crear(voto: Voto) {
    this.http.post(VotoService.URL, voto.toJson(), {})
      .subscribe(response =>
        console.log(response)
        ,
        error => console.log(error)
      );
  }
}
