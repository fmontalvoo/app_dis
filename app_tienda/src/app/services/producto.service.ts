import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from '../model/producto.model';
import { Categoria } from '../model/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  static URL: string = 'http://localhost:8080/Tienda_Virtual/srv/producto';
  private productos: Array<Producto>;
  private producto: Producto;
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) {
    this.producto = new Producto();
  }
  
  leerProducto(codigo: number) {
    this.http.get(`${ProductoService.URL}/${codigo}`).subscribe((data) => {
      let categoria = new Categoria();
      this.producto.setCodigo(data['codigo']);
      this.producto.setNombre(data['nombre']);
      this.producto.setDescripcion(data['descripcion']);
      this.producto.setPrecio(data['precio']);
      this.producto.setImagen(data['imagen']);
      let cat = data['categoria'];
      categoria.setCodigo(cat['codigo']);
      categoria.setNombre(cat['nombre']);
      this.producto.setCategoria(cat);
      this.productos = new Array<Producto>();
    });
    return this.producto;
  }

  listarProductos() {
    this.productos = new Array<Producto>();
    this.http.get(ProductoService.URL, { headers: this.headers }).subscribe((data) => {
      for (const index in data) {
        let producto = new Producto();
        let categoria = new Categoria();
        let datos = data[index];
        producto.setCodigo(datos['codigo']);
        producto.setDescripcion(datos['descripcion']);
        producto.setImagen(datos['imagen']);
        producto.setNombre(datos['nombre']);
        producto.setPrecio(datos['precio']);
        let cat = datos['categoria'];
        categoria.setCodigo(cat['codigo']);
        categoria.setNombre(cat['nombre']);
        producto.setCategoria(categoria);
        this.productos.push(producto);
      }
    });
    return this.productos;
  }
}
