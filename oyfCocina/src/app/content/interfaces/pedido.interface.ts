import { Mesa } from "./mesa.interface";

export interface Pedido{
  cantidadProductos: number;
  fecha: Date;
  idPedido: number;
  mesa: Mesa;
  precioTotal: number;
 }
