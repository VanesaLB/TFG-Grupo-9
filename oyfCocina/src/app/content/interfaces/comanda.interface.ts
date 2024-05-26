import { Mesa } from "./mesa.interface";
import { Pedido } from "./pedido.interface";
import { Product } from "./product.interface";

export interface Comanda{
  idComanda: number;
  producto: Product;
  pedido: Pedido;
  mesa: Mesa;
  servido: Servido;
 }

 export enum Servido {
  Si = "si",
  No = "no"
}
