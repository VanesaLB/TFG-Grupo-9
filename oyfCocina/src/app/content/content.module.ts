import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { MesasPageComponent } from './pages/mesas-page/mesas-page.component';
import { ComandasPageComponent } from './pages/comandas-page/comandas-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ProductosPageComponent } from './pages/productos-page/productos-page.component';
import { ContentRoutingModule } from './content-routing.module';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    ContentComponent,
    MesasPageComponent,
    ComandasPageComponent,
    HomePageComponent,
    ProductosPageComponent
  ],
  imports: [
    CommonModule,
    ContentRoutingModule,
    HttpClientModule
  ],
  exports: [

  ]
})
export class ContentModule { }
