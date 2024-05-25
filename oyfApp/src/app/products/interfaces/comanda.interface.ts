import { Mesa } from "./mesa.interface";
import { Pedido } from "./pedido.interface";
import { Product } from "./product.interface";

export interface Comanda{
  idComanda: number;
  idProducto: Product;
  idPedido: Pedido;
  idMesa: Mesa;
  servido: string;
 }
