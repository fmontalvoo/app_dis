export class Tarjeta {
    private codigo: number;
    private numeroTarjeta: string;
    private fechaExpiracion: Date;
    private codigoSeguridad: string;

    constructor(codigo?: number) {
        this.codigo = codigo;
    }

    setCodigo(codigo: number) {
        this.codigo = codigo;
    }
    getCodigo() {
        return this.codigo;
    }

    setNumeroTarjeta(numeroTarjeta: string) {
        this.numeroTarjeta = numeroTarjeta;
    }
    getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    setFechaExpiracion(fechaExpiracion: Date) {
        this.fechaExpiracion = fechaExpiracion;
    }
    getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    setCodigoSeguridad(codigoSeguridad: string) {
        this.codigoSeguridad = codigoSeguridad;
    }
    getCodigoSeguridad() {
        return this.codigoSeguridad;
    }

    toJson() {
        return {
            "codigo": this.getCodigo(),
            "numeroTarjeta": this.getNumeroTarjeta(),
            "fechaExpiracion": this.getFechaExpiracion(),
            "codigoSeguridad": this.getCodigoSeguridad()
        };
    }
}