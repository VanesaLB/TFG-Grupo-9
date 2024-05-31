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

/**
 * Controlador REST para la gestión de productos.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los productos.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/producto")
public class ProductoRestController {

	 @Autowired
	    private ProductoService productoService;

	    /**
	     * Obtiene todos los productos.
	     *
	     * @return Una lista de todos los productos disponibles.
	     */
	    @GetMapping("/buscarTodos")
	    public List<Producto> buscarTodosLosProductos() {
	        return productoService.buscarTodosLosProductos();
	    }

	    /**
	     * Obtiene un producto específico por su ID.
	     *
	     * @param idProducto El ID del producto que se desea obtener.
	     * @return El producto correspondiente al ID especificado.
	     */
	    @GetMapping("/buscarUno/{idProducto}")
	    public Producto buscarUnEmpleado(@PathVariable int idProducto) {
	        return productoService.buscarProducto(idProducto);
	    }

	    /**
	     * Da de alta un nuevo producto.
	     *
	     * @param producto El producto que se desea dar de alta.
	     * @return El producto añadido al sistema.
	     */
	    @PostMapping("/alta")
	    public Producto altaDelProducto(@RequestBody Producto producto) {
	        return productoService.altaUno(producto);
	    }

	    /**
	     * Elimina un producto existente.
	     *
	     * @param idProducto El ID del producto que se desea eliminar.
	     * @return Un mensaje indicando el resultado de la operación de eliminación.
	     */
	    @DeleteMapping("/eliminar/{idProducto}")
	    public String eliminarElProyecto(@PathVariable int idProducto) {
	        switch (productoService.eliminarProducto(idProducto)) {
	            case 1:
	                return "Proyecto eliminado correctamente";
	            case 0:
	                return "No se pudo eliminar el proyecto porque FK en otra tabla";
	            case -1:
	                return "No se pudo eliminar el proyecto porque NO existe";
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + productoService.eliminarProducto(idProducto));
	        }
	    }

	    /**
	     * Busca productos por tipo.
	     *
	     * @param tipo El tipo de producto que se desea buscar.
	     * @return Una lista de productos que coinciden con el tipo especificado.
	     */
	    @GetMapping("/buscarPorTipo/{tipo}")
	    public List<Producto> buscarPorTipo(@PathVariable String tipo) {
	        return productoService.buscarProductosPorTipo(tipo);
	    }

	    /**
	     * Busca productos veganos.
	     *
	     * @return Una lista de productos veganos.
	     */
	    @GetMapping("/buscarVeganos")
	    public List<Producto> buscarProductosVeganos() {
	        return productoService.buscarProductosVeganos();
	    }

	    /**
	     * Busca productos sin gluten.
	     *
	     * @return Una lista de productos sin gluten.
	     */
	    @GetMapping("/buscarSinGluten")
	    public List<Producto> buscarProductosSinGluten() {
	        return productoService.buscarProductosSinGluten();
	    }
	}
