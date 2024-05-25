package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Mesa;
import com.api.restaurante.modelo.entities.Pedido;

public interface MesaService {

	Mesa buscarUna (int idMesa);
	int eliminarMesa(int idMesa);
	Mesa altaUno(Mesa mesa);
	List<Mesa> buscarTodos();
}
