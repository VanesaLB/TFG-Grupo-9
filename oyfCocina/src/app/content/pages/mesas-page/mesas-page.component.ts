import { Component, Input, OnInit } from '@angular/core';
import { Mesa } from '../../interfaces/mesa.interface';
import { MesaService } from '../../services/mesa.service';

@Component({
  selector: 'app-mesas-page',
  templateUrl: './mesas-page.component.html',
  styleUrl: './mesas-page.component.css'
})
export class MesasPageComponent implements OnInit{

  @Input()
  public mesa!: Mesa;

  public mesas: Mesa[] = [];

  //Inyectamos el ComandaService que hemos importado en el constructor del componente
  constructor( private mesaService: MesaService ){ }

  ngOnInit(): void {

    this.mesaService.getMesas()
      .subscribe(
        mesas => {
          console.log('Mesas recibidas:', mesas);
          //this.comandas = comandas;
          // Filtrar comandas inválidas si es necesario
          this.mesas = mesas.filter(mesa => mesa.idMesa);
          console.log('Mesas filtradas:', this.mesas);
        },
        error => {
          console.error('Error al cargar las mesas:', error);
        }
      );
  }
}
