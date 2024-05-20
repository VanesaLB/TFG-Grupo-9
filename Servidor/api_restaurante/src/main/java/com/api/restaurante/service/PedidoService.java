package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.modelo.entities.Producto;

public interface PedidoService {

	Pedido buscarUno(int idPedido);
	List<Pedido> buscarTodos();
	List<Pedido> altaMuchos(List<Pedido> pedidosAlta);
	Pedido save(Pedido pedido);
	
}
