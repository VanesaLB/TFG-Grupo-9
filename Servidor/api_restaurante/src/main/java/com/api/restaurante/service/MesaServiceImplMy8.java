package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Mesa;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.repository.MesaRepository;

@Service
public class MesaServiceImplMy8 implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	@Override
	public List<Mesa> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Mesa buscarUna(int idMesa) {
		// TODO Auto-generated method stub
		return mesaRepository.findById(idMesa).orElse(null);
	}

}
