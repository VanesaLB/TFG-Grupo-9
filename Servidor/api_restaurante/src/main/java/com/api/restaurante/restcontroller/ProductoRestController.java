package com.api.restaurante.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.dto.PedidoDto;
import com.api.restaurante.modelo.entities.Pedido;
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
	
	@GetMapping("/buscarUno/{idProducto}")
	 public Producto buscarUnEmpleado(@PathVariable int idProducto) {
		 return productoService.buscarProducto(idProducto);
	 }
	
	
	@GetMapping("/buscarPorTipo/{tipo}")
	public List<Producto> buscarPorTipo(@PathVariable String tipo) {
	    return productoService.buscarProductosPorTipo(tipo);
	}
	
	 @GetMapping("/buscarVeganos")
	    public List<Producto> buscarProductosVeganos() {
	        return productoService.buscarProductosVeganos();
	    }
	 @GetMapping("/buscarSinGluten")
	    public List<Producto> buscarProductosSinGluten() {
	        return productoService.buscarProductosSinGluten();
	    }
	 
	 
	
}
