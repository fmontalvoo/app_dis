import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/services/producto.service';
import { Producto } from 'src/app/model/producto.model';
import { ActivatedRoute } from '@angular/router';
import { Storage } from '@ionic/storage';
import { Cliente } from 'src/app/model/cliente.model';
import { Voto } from 'src/app/model/voto.model';
import { VotoService } from 'src/app/services/voto.service';
import { Carrito } from 'src/app/model/carrito.model';
import { CarritoService } from 'src/app/services/carrito.service';


@Component({
  selector: 'app-producto',
  templateUrl: './producto.page.html',
  styleUrls: ['./producto.page.scss'],
})
export class ProductoPage implements OnInit {

  private producto: Producto;
  private cliente: Cliente;
  private voto: Voto;
  private carrito: Carrito;
  private cantidad: number;

  constructor(private productoService: ProductoService,
    private votoService: VotoService,
    private carritoService: CarritoService,
    private route: ActivatedRoute,
    private storage: Storage) {
    this.producto = new Producto();
    this.cliente = new Cliente();
    this.voto = new Voto();
    this.carrito = new Carrito();
    this.cantidad = 0;
    this.route.params.subscribe(param => {
      this.producto = this.productoService.leerProducto(param['codigo']);
    });
    storage.get('codigo').then((val) => {
      this.cliente.setCodigo(val);
    });
  }

  ngOnInit() {
  }

  votar() {
    this.voto.setEstado(true);
    this.voto.setCliente(this.cliente);
    this.voto.setProducto(this.producto);
    this.votoService.crear(this.voto);
  }

  agregarCarrito() {
    this.carrito.setCliente(this.cliente);
    this.carrito.setProducto(this.producto);
    this.carrito.setCantidad(this.cantidad);
    this.carrito.setPagado(false);
    console.log(this.carrito.toJson());
    this.carritoService.agregar(this.carrito);
  }

  increment() {
    this.cantidad++;
  }

  decrement() {
    if (this.cantidad > 0) {
      this.cantidad--;
    }
  }
}
