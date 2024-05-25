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
          console.log('Comandas recibidas:', comandas);
          //this.comandas = comandas;
          // Filtrar comandas inválidas si es necesario
          this.comandas = comandas.filter(comanda => comanda.producto && comanda.pedido);
          console.log('Comandas filtradas:', this.comandas);
        },
        error => {
          console.error('Error al cargar las comandas:', error);
        }
      );
  }

}
