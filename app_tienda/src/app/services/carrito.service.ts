import { Injectable } from '@angular/core';
import { Carrito } from '../model/carrito.model';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../model/cliente.model';
import { Producto } from '../model/producto.model';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  static URL: string = 'http://localhost:8080/Tienda_Virtual/srv/carrito';
  private carritos: Array<Carrito>;

  constructor(private http: HttpClient) {
    this.carritos = new Array<Carrito>();
  }

  agregar(carrito: Carrito) {
    this.http.post(CarritoService.URL, carrito.toJson(), {})
      .subscribe(response =>
        console.log(response)
        ,
        error => console.log(error)
      );
  }

  listar(codigo: number) {
    this.carritos = new Array<Carrito>();
    this.http.get(`${CarritoService.URL}?codigo=${codigo}`).subscribe((data) => {
      for (const index in data) {
        let carrito = new Carrito();
        let cliente = new Cliente();
        let producto = new Producto();
        let datos = data[index];
        carrito.setCodigo(datos['codigo']);
        let cli = datos['cliente'];
        cliente.setCodigo(cli['codigo']);
        carrito.setCliente(cliente);
        let pro = datos['producto'];
        producto.setCodigo(pro['codigo']);
        producto.setNombre(pro['nombre']);
        producto.setPrecio(pro['precio']);
        carrito.setProducto(producto);
        carrito.setCantidad(datos['cantidad']);
        carrito.setPagado(datos['pagado']);
        this.carritos.push(carrito);
      }
    });
    return this.carritos;
  }

  eliminar(codigo: number) {
    this.http.delete(`${CarritoService.URL}/${codigo}`, {}).subscribe(response =>
      console.log(response),
      error => console.log(error));
  }
}
