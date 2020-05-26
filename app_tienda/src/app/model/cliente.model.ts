export class Cliente {
    private codigo: number;
    private nombres: string;
    private apellidos: string;
    private email: string;
    private clave: string;

    constructor(codigo?: number) {
        this.codigo = codigo;
    }

    setCodigo(codigo: number) {
        this.codigo = codigo;
    }
    getCodigo() {
        return this.codigo;
    }

    setNombres(nombres: string) {
        this.nombres = nombres;
    }
    getNombres() {
        return this.nombres;
    }

    setApellidos(apellidos: string) {
        this.apellidos = apellidos;
    }
    getApellidos() {
        return this.apellidos;
    }

    setEmail(email: string) {
        this.email = email;
    }
    getEmail() {
        return this.email;
    }

    setClave(clave: string) {
        this.clave = clave;
    }
    getClave() {
        return this.clave;
    }

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "nombres": this.getNombres(),
            "apellidos": this.getApellidos(),
            "email": this.getEmail(),
            "clave": this.getClave()

        };
    }

}