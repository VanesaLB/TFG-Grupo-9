package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Comanda;
import com.api.restaurante.modelo.entities.Pedido;

public interface ComandaService {
	
	Comanda buscarUno(int idComanda);
	List<Comanda> buscarTodos();
	List<Comanda> altaMuchos(List<Comanda> comandasAlta);
	Comanda altaUno(Comanda comanda);
	
}
