import { Component, Input, OnInit } from '@angular/core';
import { Comanda } from '../../interfaces/comanda.interface';
import { ComandaService } from '../../services/comanda.service';
import { PedidoService } from '../../services/pedido.service';

@Component({
  selector: 'app-comandas-page',
  templateUrl: './comandas-page.component.html',
  styleUrl: './comandas-page.component.css'
})
export class ComandasPageComponent implements OnInit{

  @Input()
  public comanda!: Comanda;

  public comandas: Comanda[] = [];

  //Inyectamos el ComandaService que hemos importado en el constructor del componente
  constructor( private comandaService: ComandaService, private pedidoService: PedidoService ){ }

  ngOnInit(): void {

    this.comandaService.getComandas()
      .subscribe(
        comandas => {
          this.comandas = comandas;
          //this.loadQuantityStateFromLocalStorage();
          //this.mostrarMiPedido();
          //this.mostrarTotal();
        },
        error => {
          console.error('Error al cargar las comandas:', error);
        }
      );
  }

}
