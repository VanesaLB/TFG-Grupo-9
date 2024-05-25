package com.api.restaurante.service;

import java.util.List;


import com.api.restaurante.modelo.entities.Pedido;


public interface PedidoService {

	Pedido buscarUno(int idPedido);
	List<Pedido> buscarTodos();
	Pedido altaUno(Pedido pedido);
	int altaPedidoId(Pedido pedido);
	int eliminarPedido(int idPedido);
	
	List<Pedido> altaMuchos(List<Pedido> pedidosAlta);
	
	
}
