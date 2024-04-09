package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.repository.ProductoRepository;

@Service
public class ProductoServiceImplMy8 implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	@Override
	public List<Producto> buscarTodosLosProductos() {
		return productoRepository.findAll();
	}
	
	@Override
	public Producto buscarProducto(int idPlato) {
		// TODO Auto-generated method stub
		return productoRepository.findById(idPlato).orElse(null);
	}

}
