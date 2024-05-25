import { Mesa } from "./mesa.interface";

export interface Pedido{
  idPedido: number;
  Mesa: Mesa;
  cantidadProductos: number;
  precioTotal: number;
  fecha: Date;
 }
