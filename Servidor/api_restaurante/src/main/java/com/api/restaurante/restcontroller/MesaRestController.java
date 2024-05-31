package com.api.restaurante.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.entities.Mesa;

import com.api.restaurante.service.MesaService;
import com.api.restaurante.service.PedidoService;
import com.api.restaurante.service.ProductoService;


/**
 * Controlador REST para la gestión de mesas.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las mesas.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mesa")
public class MesaRestController {

	
	

		@Autowired
		private PedidoService pedidoService;
		@Autowired
		private ProductoService productoService;
		@Autowired
		private MesaService mesaService;
		@Autowired
		private ModelMapper modelMapper;
		 
		/**
	     * Obtiene todas las mesas.
	     *
	     * @return Una lista de todas las mesas disponibles.
	     */
		
		@GetMapping("/buscarTodos")
		 public List<Mesa> buscarTodasLasMesas() {
			 return mesaService.buscarTodos();
		 }
		/**
	     * Obtiene una mesa específica por su ID.
	     *
	     * @param idMesa El ID de la mesa que se desea obtener.
	     * @return La mesa correspondiente al ID especificado.
	     */
		@GetMapping("/buscarUno/{idMesa}")
		 public Mesa buscarUnaMesa(@PathVariable int idMesa) {
			 return mesaService.buscarUna(idMesa);
		 }
		 /**
	     * Elimina una mesa existente.
	     *
	     * @param idMesa El ID de la mesa que se desea eliminar.
	     * @return Un mensaje indicando el resultado de la operación de eliminación.
	     */
		@DeleteMapping("/eliminar/{idMesa}")
		public String eliminarMesa(@PathVariable int idMesa) {
		
			switch (mesaService.eliminarMesa(idMesa)) {
			
			case 1: return "Mesa eliminado correctamente";
			case 0: return "No se pudo eliminar el Mesa porque FK en otra tabla";
			case -1: return "No se pudo eliminar el Mesa porque NO existe";
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + mesaService.eliminarMesa(idMesa));
			}
			
		}
		  /**
	     * Da de alta una nueva mesa.
	     *
	     * @param mesa La mesa que se desea dar de alta.
	     * @return La mesa añadida al sistema.
	     */
		@PostMapping("/altaMesa")
		public Mesa altaDelPedido(@RequestBody Mesa mesa) {
			
			return mesaService.altaUno(mesa);
			}
}
