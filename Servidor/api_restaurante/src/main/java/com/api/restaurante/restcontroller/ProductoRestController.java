package com.api.restaurante.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.service.ProductoService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/producto")
public class ProductoRestController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/buscarTodos")
	 public List<Producto> buscarTodosLosEmpleados() {
		 return productoService.buscarTodosLosProductos();
	 }
	
	@GetMapping("/buscarUno/{idPlato}")
	 public Producto buscarUnEmpleado(@PathVariable int idPlato) {
		 return productoService.buscarProducto(idPlato);
	 }
}
