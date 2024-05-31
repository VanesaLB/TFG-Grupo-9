import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../interfaces/product.interface';

@Component({
  selector: 'app-productos-page',
  templateUrl: './productos-page.component.html',
  styleUrl: './productos-page.component.css'
})
export class ProductosPageComponent implements OnInit{

  public product!: Product;

  public products: Product[] = [];

  //Se inyecta el ProductService que hemos importado en el constructor del componente
  constructor( private productService: ProductService ){ }

  ngOnInit(): void {
    this.productService.getProducts()
      .subscribe(
        products => {
          this.products = products;
        },
        error => {
          console.error('Error al cargar los productos:', error);
        }
      );
  }

  mostrarTodos(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }

  eliminarProducto(producto: Product): void {
    if( producto && producto.idProducto ){
      this.productService.deleteProduct( producto.idProducto )
      .subscribe(() => {
        this.products = this.products.filter(p => p.idProducto !== producto.idProducto);
      })
    } else {
      console.error('El producto es nulo o no tiene una propiedad idProducto definida.');
    }
  }
}
