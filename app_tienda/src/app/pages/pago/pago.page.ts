import { Component, OnInit } from '@angular/core';
import { TarjetaService } from 'src/app/services/tarjeta.service';
import { Tarjeta } from 'src/app/model/tarjeta.model';
import { Storage } from '@ionic/storage';
import { FacturaService } from 'src/app/services/factura.service';

@Component({
  selector: 'app-pago',
  templateUrl: './pago.page.html',
  styleUrls: ['./pago.page.scss'],
})
export class PagoPage implements OnInit {
  private tarjetas: Array<Tarjeta>;
  private tarjeta: Tarjeta;
  private codigo: number;

  constructor(private tarjetaService: TarjetaService, private facturaService: FacturaService, private storage: Storage) {
    this.tarjetas = new Array<Tarjeta>();
    this.tarjeta = new Tarjeta();
    storage.get('codigo').then((val) => {
      this.codigo = val;
      this.tarjetas = this.tarjetaService.listarTarjetas(this.codigo);
    });
  }

  ngOnInit() {
  }

  seleccionarTarjeta(tarjeta: Tarjeta) {
    this.tarjeta = tarjeta;
    console.log(this.tarjeta.toJson());
  }

  comprar() {
    console.log(`COMPRAR>>>>${this.tarjeta.getCodigo()}`);
    this.facturaService.facturar(this.tarjeta.getCodigo());
  }

}
