export class Categoria {
    private codigo: number;
    private nombre: string;

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

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "nombre": this.getNombre()
        };
    }
}