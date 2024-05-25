package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Mesa;

public interface MesaService {

	Mesa buscarUna (int idMesa);
	List<Mesa> buscarTodos();
}
