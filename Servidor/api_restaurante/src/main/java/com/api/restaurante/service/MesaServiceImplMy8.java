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
		return mesaRepository.findAll();
	}
	
	@Override
	public Mesa buscarUna(int idMesa) {
		// TODO Auto-generated method stub
		return mesaRepository.findById(idMesa).orElse(null);
	}
	
	@Override
	public int eliminarMesa(int idMesa) {
		if (buscarUna(idMesa)!= null)
			try {
				mesaRepository.deleteById(idMesa);
				return 1;
			} catch (Exception e) {
				return 0;
			}else
				return -1;
	}
	
	@Override
	public Mesa altaUno(Mesa mesa) {
		if (buscarUna(mesa.getIdMesa()) == null)
			return mesaRepository.save(mesa);
			else
				return null;
	}

}
