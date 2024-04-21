package com.api.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Mesa;
import com.api.restaurante.repository.MesaRepository;

@Service
public class MesaServiceImplMy8 implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	@Override
	public Mesa buscarUna(int idMesa) {
		// TODO Auto-generated method stub
		return mesaRepository.findById(idMesa).orElse(null);
	}

}
