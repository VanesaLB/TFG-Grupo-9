export interface Product {
  idProducto: number;
  ingredientes: string;
  descripcion: string;
  tipo: Tipo;
  vegano: Vegano;
  gluten: Gluten,
  precio: number;
}

export enum Tipo {
  Entrante = "ENTRANTE",
  Principal = "PRINCIPAL",
  Postres = "POSTRES",
  Bebidas = "BEBIDAS",
}

export enum Vegano {
  Si = "si",
  No = "no"
}

export enum Gluten {
  Si = "si",
  No = "no"
}

