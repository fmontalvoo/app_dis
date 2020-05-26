import { Cliente } from './cliente.model';
import { Producto } from './producto.model';
import { BooleanLiteral } from 'typescript';

export class Carrito {

    private codigo: number;
    private cliente: Cliente;
    private producto: Producto;
    private cantidad: number;
    private pagado: boolean;

    constructor(codigo?: number) {
        this.codigo = codigo;
    }

    setCodigo(codigo: number) {
        this.codigo = codigo;
    }
    getCodigo() {
        return this.codigo;
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

    setCantidad(cantidad: number) {
        this.cantidad = cantidad;
    }
    getCantidad() {
        return this.cantidad;
    }

    setPagado(pagado: boolean) {
        this.pagado = pagado;
    }
    getPagado() {
        return this.pagado;
    }

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "cliente": this.getCliente().toJson(),
            "producto": this.getProducto().toJson(),
            "cantidad": this.getCantidad(),
            "pagado": this.getPagado(),
        };
    }

}