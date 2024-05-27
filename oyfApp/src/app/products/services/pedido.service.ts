import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Pedido } from '../interfaces/pedido.interface';
import { PedidoDto } from '../interfaces/pedidoDto.interface';

/*
Decorador @Injectable: Este decorador se utiliza para proporcionar metadatos que Angular
necesita para inyectar dependencias en este servicio.
Especifica que este servicio está disponible en todo el módulo de la aplicación (root).
*/
@Injectable({
  providedIn: 'root'
})


export class PedidoService {
  //Propiedad privada que almacena la URL base del backend donde se encuentran los recursos de los productos. Endpoint del Backend.
  private backendURL: string = "http://localhost:8088/pedido";
  //Propiedad pública que almacenará los productos obtenidos del backend
  public pedidos: Pedido[] = [];

  //Inicializa el servicio con el cliente HTTP (httpClient), que se utilizará para realizar solicitudes HTTP al backend y obtener los datos de los productos.
  constructor(
    //HttpClient para proporcionar métodos que reciben datos del backend
    private httpClient: HttpClient
    ) {}



  //Métodos para obtener productos del backend

  /**
   *Método que envían solicitud HTTP al backend para obtener todos los productos.
   Utiliza el cliente HTTP (httpClient) para realizar la solicitud y devuelve un Observable que emite la lista
   de productos obtenidos. El operador tap se utiliza para asignar los productos obtenidos a la propiedad products.
   * @returns Observable<Product[]>, un observable que emite secuencialmente arrays de objetos Product.
   Esto significa que el observable puede ser observado y, cuando se suscribe a él, puede manejar los valores
   emitidos a lo largo del tiempo. Se obtiene de la fuente asíncrona, la solicitud HTTP.
   */
  public getPedidos(): Observable<Pedido[]> {
    return this.httpClient.get<Pedido[]>(`${this.backendURL}/buscarTodos`)
      .pipe(
        //Utiliza el operador tap para asignar los productos obtenidos a la propiedad products
        tap(pedidos => this.pedidos = pedidos)
      );
  }



  public crearPedido(pedidoDto: PedidoDto): Observable<number> {
    return this.httpClient.post<number>(`${ this.backendURL }/altaPedidoId`, pedidoDto)

}

}
