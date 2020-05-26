import { Component, OnInit } from '@angular/core';
import { MenuController } from '@ionic/angular';
import {AuthService} from '../../services/auth.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor( private authService:AuthService, public menuCtrl: MenuController) { this.menuCtrl.enable(false, 'menu');}

  ngOnInit() {
  }

  login(email, pass){
    console.log(email, pass);
    this.authService.session(email, pass);
    this.getToken();
  }

  getToken(){
    this.authService.getToken().then(val => console.log(`TOKEN>>>${val}`));
  }

}
