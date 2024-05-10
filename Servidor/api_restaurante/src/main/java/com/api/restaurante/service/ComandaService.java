package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Comanda;

public interface ComandaService {
	
	Comanda buscarUno(int idComanda);
	List<Comanda> buscarTodos();
	
}
