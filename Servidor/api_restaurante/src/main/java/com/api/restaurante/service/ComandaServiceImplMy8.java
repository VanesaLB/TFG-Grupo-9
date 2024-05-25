package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Comanda;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.repository.ComandaRepository;
import com.api.restaurante.repository.PedidoRepository;


@Service
public class ComandaServiceImplMy8 implements ComandaService{
	
	@Autowired
	private ComandaRepository comandaRepository;
	@Autowired
    private PedidoRepository pedidoRepository;


	@Override
	public Comanda buscarUno(int idComanda) {
		// TODO Auto-generated method stub
		return comandaRepository.findById(idComanda).orElse(null);
	}

	@Override
	public List<Comanda> buscarTodos() {
		// TODO Auto-generated method stub
		return comandaRepository.findAll();
	}

	@Override
	public List<Comanda> altaMuchos(List<Comanda> comandasAlta) {
		return comandaRepository.saveAll(comandasAlta);
	}
	
	@Override
	public Comanda altaUno(Comanda comanda) {
		if (buscarUno(comanda.getIdComanda()) == null)
			return comandaRepository.save(comanda);
			else
				return null;
	}

}
