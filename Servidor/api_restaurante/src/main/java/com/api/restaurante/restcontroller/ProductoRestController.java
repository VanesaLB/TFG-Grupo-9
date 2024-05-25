package com.api.restaurante.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 public List<Producto> buscarTodosLosProductos() {
		 return productoService.buscarTodosLosProductos();
	 }
	
	@GetMapping("/buscarUno/{idProducto}")
	 public Producto buscarUnEmpleado(@PathVariable int idProducto) {
		 return productoService.buscarProducto(idProducto);
	 }
	
	@PostMapping("/alta")
	public Producto altaDelProducto(@RequestBody Producto producto) {
		
		return productoService.altaUno(producto);
		}
	
	@DeleteMapping("/eliminar/{idProyecto}")
	public String eliminarElProyecto(@PathVariable int idProducto) {
	
		switch (productoService.eliminarProducto(idProducto)) {
		
		case 1: return "Proyecto eliminado correctamente";
		case 0: return "No se pudo eliminar el proyecto porque FK en otra tabla";
		case -1: return "No se pudo eliminar el proyecto porque NO existe";
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + productoService.eliminarProducto(idProducto));
		}
		
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
