import { Categoria } from './categoria.model';

export class Producto {
    private codigo: number;
    private nombre: string;
    private descripcion: string;
    private precio: number;
    private imagen: any;
    private categoria: Categoria;

    constructor(codigo?: number) {
        this.codigo = codigo;
    }

    setCodigo(codigo: number) {
        this.codigo = codigo;
    }
    getCodigo() {
        return this.codigo;
    }

    setNombre(nombre: string) {
        this.nombre = nombre;
    }
    getNombre() {
        return this.nombre;
    }

    setDescripcion(descripcion: string) {
        this.descripcion = descripcion;
    }
    getDescripcion() {
        return this.descripcion;
    }

    setPrecio(precio: number) {
        this.precio = precio;
    }
    getPrecio() {
        return this.precio;
    }

    setImagen(imagen: any) {
        this.imagen = imagen;
    }
    getImagen() {
        return this.imagen;
    }

    setCategoria(categoria: Categoria) {
        this.categoria = categoria;
    }
    getCategoria() {
        return this.categoria;
    }

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "nombre": this.getNombre(),
            "descripcion": this.getDescripcion(),
            "precio": this.getPrecio(),
            "imagen": this.getImagen(),
            "categoria": this.getCategoria()
        };
    }
}