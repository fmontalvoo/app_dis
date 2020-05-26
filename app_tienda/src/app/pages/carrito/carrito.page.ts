import { Component, OnInit } from '@angular/core';
import { CarritoService } from 'src/app/services/carrito.service';
import { Carrito } from 'src/app/model/carrito.model';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.page.html',
  styleUrls: ['./carrito.page.scss'],
})
export class CarritoPage implements OnInit {
  private carritos: Array<Carrito>;

  constructor(private carritoService: CarritoService, private storage: Storage) {
    this.carritos = new Array<Carrito>();
    storage.get('codigo').then((val) => {
      this.carritos = this.carritoService.listar(val);
    });
  }

  ngOnInit() {
  }

  eliminar(carrito: Carrito) {
    this.deleteElement(this.carritos, carrito);
    this.carritoService.eliminar(carrito.getCodigo());
  }

  deleteElement(arr, element) {
    var position = arr.indexOf(element);
    if (position === -1)
      return null;
    return arr.splice(position, 1);
  }

  
}
