import { Mesa } from "./mesa.interface";

export interface Pedido{
  idPedido: number;
  precioTotal: number;
  idMesa: Mesa;
  cantidadProductos: number;
  fecha: Date;
 }
