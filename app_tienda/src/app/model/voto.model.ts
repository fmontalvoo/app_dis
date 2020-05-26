import { Producto } from './producto.model';
import { Cliente } from './cliente.model';

export class Voto {
    private codigo: number;
    private estado: boolean;
    private cliente: Cliente;
    private producto: Producto;

    constructor() { }

    setCodigo(codigo: number) {
        this.codigo = codigo;
    }
    getCodigo() {
        return this.codigo;
    }

    setEstado(estado: boolean) {
        this.estado = estado;
    }
    getEstado() {
        return this.estado;
    }

    setCliente(cliente: Cliente) {
        this.cliente = cliente;
    }
    getCliente() {
        return this.cliente;
    }

    setProducto(producto: Producto) {
        this.producto = producto;
    }
    getProducto() {
        return this.producto;
    }

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "estado": this.getEstado(),
            "cliente": this.getCliente().toJson(),
            "producto": this.getProducto().toJson()
        };
    }
}