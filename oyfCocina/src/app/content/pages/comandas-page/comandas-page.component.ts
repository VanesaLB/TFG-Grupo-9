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


  /*public productoForm = new FormGroup({
    id: new FormControl<string>(''),
    ingredientes: new FormControl<string>(''),
    descripcion: new FormControl<string>(''),
    tipo: new FormControl<Tipo>( Tipo.Entrante ),
    vegano: new FormControl<Vegano>( Vegano.No ),
    gluten: new FormControl<Gluten>( Gluten.No ),
    precio: new FormControl<number>(0)
  });
*/

  public servidos = [
    { id: 'si', desc: 'si'},
    { id: 'no', desc: 'no'},
  ];

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

  //modificarServido(comandaDto: ComandaDto): void {
    
    /*if( producto && producto.idProducto ){
      this.productService.deleteProduct( producto.idProducto )
      .subscribe(() => {
        this.products = this.products.filter(p => p.idProducto !== producto.idProducto);

      })
    } else {
      console.error('El producto es nulo o no tiene una propiedad idProducto definida.');

    }*/

  }

//}
