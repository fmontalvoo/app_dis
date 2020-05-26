import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Storage } from '@ionic/storage';
import { Router } from '@angular/router';

const URL = "http://localhost:8080/Tienda_Virtual/srv/login";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private storage: Storage , private router:Router) { }


  session(email: String, pass: String) {
    var user = { email: email, clave: pass };
    return new Promise(resolve => {
      this.http.post(URL, user).subscribe(data => {
        resolve(data);
        this.saveToken(data['jwt']);
        if(this.saveToken){
          this.router.navigateByUrl('/');
        }
      }, err => {
        console.log(err);
      });
    });
  }

  saveToken(token: String) {
    this.storage.set('token', token);
    let json = this.decodeJwt(token);
    this.storage.set('codigo', json['codigo']);
  }

  async getToken() {
    let response: string;
    await this.storage.get('token').then((val) => response = val);
    return response;
  }

  decodeJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };
}