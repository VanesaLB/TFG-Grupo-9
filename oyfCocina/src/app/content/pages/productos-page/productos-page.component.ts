import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../interfaces/product.interface';

@Component({
  selector: 'app-productos-page',
  templateUrl: './productos-page.component.html',
  styleUrl: './productos-page.component.css'
})
export class ProductosPageComponent implements OnInit{

  @Input()
  public product!: Product;

  public products: Product[] = [];

  //Inyectamos el ProductService que hemos importado en el constructor del componente
  constructor( private productService: ProductService ){ }

  ngOnInit(): void {

    this.productService.getProducts()
      .subscribe(
        products => {
          this.products = products;
          //this.loadQuantityStateFromLocalStorage();
          //this.mostrarMiPedido();
          //this.mostrarTotal();
        },
        error => {
          console.error('Error al cargar los productos:', error);
        }
      );
  }

    /**
   * Este método se llama cuando se hace clic en el botón "Todos".
   * Obtiene todos los productos del servicio y actualiza el estado de los checkboxes después de obtener la respuesta.
   */
    mostrarTodos(): void {
      this.productService.getProducts().subscribe(products => {
        this.products = products;
        //this.loadQuantityStateFromLocalStorage();
      });
    }


}
