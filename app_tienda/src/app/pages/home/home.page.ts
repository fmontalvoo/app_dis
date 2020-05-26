import { Component } from '@angular/core';

import { ProductoService } from 'src/app/services/producto.service';
import { Producto } from 'src/app/model/producto.model';
import { Storage } from '@ionic/storage';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  private productos: Array<Producto>;
  constructor(private productoService: ProductoService, private storage: Storage) {
    this.productos = new Array<Producto>();
    this.productos = this.productoService.listarProductos();
  }

}
