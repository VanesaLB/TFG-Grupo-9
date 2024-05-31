import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Gluten, Product, Tipo, Vegano } from '../../interfaces/product.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent{

  public productoForm = new FormGroup({
    id: new FormControl<string>(''),
    ingredientes: new FormControl<string>(''),
    descripcion: new FormControl<string>(''),
    tipo: new FormControl<Tipo>( Tipo.Entrante ),
    vegano: new FormControl<Vegano>( Vegano.No ),
    gluten: new FormControl<Gluten>( Gluten.No ),
    precio: new FormControl<number>(0)
  });

  public tipos = [
    { id: 'ENTRANTE', desc: 'ENTRANTE'},
    { id: 'PRINCIPAL', desc: 'PRINCIPAL'},
    { id: 'POSTRES', desc: 'POSTRES'},
    { id: 'BEBIDAS', desc: 'BEBIDAS'}
  ];

  public veganos = [
    { id: 'si', desc: 'si'},
    { id: 'no', desc: 'no'},
  ];

  public glutens = [
    { id: 'si', desc: 'si'},
    { id: 'no', desc: 'no'},
  ];

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  //Para que cargue en el formulario el producto actual para editarlo
  get currentProduct(): Product {
    const product = this.productoForm.value as Product;
    return product;
  }

  crearProducto(): void{
      this.productService.addProduct( this.currentProduct )
      .subscribe( product => {
        this.router.navigate(['/productos', product.idProducto ])
        alert(`${ product.descripcion } creado`);
      })
  }

}
