package com.api.restaurante.service;

import java.util.List;

import com.api.restaurante.modelo.entities.Producto;



public interface ProductoService {

	List<Producto> buscarTodosLosProductos();
	Producto buscarProducto(int idProducto);
	List<Producto> buscarProductosPorTipo(String tipo);
	List<Producto> buscarProductosVeganos();
	List<Producto> buscarProductosSinGluten();
	
}
