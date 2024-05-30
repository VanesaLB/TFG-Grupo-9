import { Component, Input, OnInit } from '@angular/core';
import { Comanda, Servido } from '../../interfaces/comanda.interface';
import { ComandaService } from '../../services/comanda.service';
import { PedidoService } from '../../services/pedido.service';
import { ComandaDto } from '../../interfaces/comandaDto.interface';

@Component({
  selector: 'app-comandas-page',
  templateUrl: './comandas-page.component.html',
  styleUrl: './comandas-page.component.css'
})
export class ComandasPageComponent implements OnInit{

  public servidos = [
    { id: 'si', desc: 'si'},
    { id: 'no', desc: 'no'},
  ];

  @Input()
  public comanda!: Comanda;
  public comandaDto!: ComandaDto;

  public comandas: Comanda[] = [];

  //Inyectamos el ComandaService que hemos importado en el constructor del componente
  constructor( private comandaService: ComandaService, private pedidoService: PedidoService ){ }

  ngOnInit(): void {
    this.loadComandasNoServidas();
  }

  private loadComandasNoServidas(): void {
    this.comandaService.getComandas('no')
      .subscribe(
        comandas => {
          // Filtrar comandas inválidas
          this.comandas = comandas.filter(comanda =>
            comanda && comanda.idComanda !== undefined && comanda.producto && comanda.pedido
          );
        },
        error => {
          console.error('Error al cargar las comandas:', error);
        }
      );
  }

  modificarServido(comanda: Comanda, nuevoServido: Servido): void {
    comanda.servido = nuevoServido;
    this.comandaService.updateComanda(comanda).subscribe(
      response => {
        console.log('Comanda actualizada:', response);
        this.loadComandasNoServidas(); // Refrescar la lista de comandas
      },
      error => {
        console.error('Error al actualizar la comanda:', error);
      }
    );
  }
}
