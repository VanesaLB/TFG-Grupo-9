import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductsComponent } from './products.component';
import { ProductsRoutingModule } from './products-routing.module';
import { HistoryPageComponent } from './pages/history-page/history-page.component';
import { ProductPageComponent } from './pages/product-page/product-page.component';
import { ContactPageComponent } from './pages/contact-page/contact-page.component';
import { CardComponent } from './components/card/card.component';
import { DigitalMenuComponent } from './pages/digital-menu/digital-menu.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    ProductsComponent,
    HistoryPageComponent,
    ProductPageComponent,
    ContactPageComponent,
    DigitalMenuComponent,
    CardComponent,
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    HttpClientModule
  ]
})
export class ProductsModule { }


