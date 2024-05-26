import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { MesasPageComponent } from './pages/mesas-page/mesas-page.component';
import { ComandasPageComponent } from './pages/comandas-page/comandas-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ProductosPageComponent } from './pages/productos-page/productos-page.component';
import { ContentRoutingModule } from './content-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CreateProductComponent } from './pages/create-product/create-product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ContentComponent,
    MesasPageComponent,
    ComandasPageComponent,
    HomePageComponent,
    ProductosPageComponent,
    CreateProductComponent
  ],
  imports: [
    CommonModule,
    ContentRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    CreateProductComponent
  ]
})
export class ContentModule { }
