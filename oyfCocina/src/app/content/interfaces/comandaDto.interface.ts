export interface ComandaDto{
  idComanda: number;
  servido: Servido;
 }

 export enum Servido {
  Si = "si",
  No = "no"
}
