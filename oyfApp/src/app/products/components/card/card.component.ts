import { Component, Input, OnInit, input } from '@angular/core';
import { Product } from '../../interfaces/product.interface';

@Component({
  selector: 'product-card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent implements OnInit{

  @Input()
  public product!: Product;
  // Definición de la propiedad isChecked con un array de booleanos
  public isChecked: boolean[] = [];

  constructor() {
    const savedState = localStorage.getItem('checkboxState');
    this.isChecked = savedState ? JSON.parse(savedState) : false;
  }

  ngOnInit(): void {
    if( !this.product ) throw Error( 'Producto es requerido' );
  }

  //Método para guardar el check en el local storage
  public saveCheckLocalStorage(event: Event, index: number): void {
    if (event.target instanceof HTMLInputElement) {
      const checked = event.target.checked;
      localStorage.setItem(`checkboxState-${index}`, JSON.stringify(checked));
    }
  }




// Método para guardar el check en el local storage
// saveCheckLocalStorage(event: any, index: number): void {
//   // Actualizar el estado del checkbox en el array isChecked
//   this.isChecked[index] = event.target.checked;

//   // Guardar el estado en el localStorage
//   localStorage.setItem(`checkboxState-${index}`, JSON.stringify(this.isChecked[index]));
// }
}
