import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ComandasPageComponent } from './pages/comandas-page/comandas-page.component';
import { ProductosPageComponent } from './pages/productos-page/productos-page.component';
import { MesasPageComponent } from './pages/mesas-page/mesas-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'comandas',
    component: ComandasPageComponent
  },
  {
    path: 'productos',
    component: ProductosPageComponent
  },
  {
    path: 'mesas',
    component: MesasPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentRoutingModule { }
