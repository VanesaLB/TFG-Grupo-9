
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { DigitalMenuComponent } from './pages/digital-menu/digital-menu.component';
import { ContactPageComponent } from './pages/contact-page/contact-page.component';
import { HistoryPageComponent } from './pages/history-page/history-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'menu',
    component: DigitalMenuComponent
  },
  {
    path: 'contact',
    component: ContactPageComponent
  },
  {
    path: 'history',
    component: HistoryPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }

