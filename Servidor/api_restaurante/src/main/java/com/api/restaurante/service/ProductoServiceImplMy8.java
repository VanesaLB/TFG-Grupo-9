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
	public Producto buscarProducto(int idProducto) {
		// TODO Auto-generated method stub
		return productoRepository.findById(idProducto).orElse(null);
	}
	
	@Override
	public List<Producto> buscarProductosPorTipo(String tipo) {
	    return productoRepository.findByTipo(tipo);
	}
	
	@Override
	public List<Producto> buscarProductosVeganos() {
	    return productoRepository.findByVegano("si");
	}
	@Override
	public List<Producto> buscarProductosSinGluten() {
	    return productoRepository.findByGluten("no");
	}

	

}
