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
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.service.MesaService;
import com.api.restaurante.service.PedidoService;
import com.api.restaurante.service.ProductoService;

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
		
		@GetMapping("/buscarTodos")
		 public List<Mesa> buscarTodasLasMesas() {
			 return mesaService.buscarTodos();
		 }
		
		@GetMapping("/buscarUno/{idMesa}")
		 public Mesa buscarUnEmpleado(@PathVariable int idMesa) {
			 return mesaService.buscarUna(idMesa);
		 }
		
		@DeleteMapping("/eliminar/{idMesa}")
		public String eliminarElProyecto(@PathVariable int idMesa) {
		
			switch (mesaService.eliminarMesa(idMesa)) {
			
			case 1: return "Proyecto eliminado correctamente";
			case 0: return "No se pudo eliminar el proyecto porque FK en otra tabla";
			case -1: return "No se pudo eliminar el proyecto porque NO existe";
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + mesaService.eliminarMesa(idMesa));
			}
			
		}
		
		@PostMapping("/alta")
		public Mesa altaDelPedido(@RequestBody Mesa mesa) {
			
			return mesaService.altaUno(mesa);
			}
}
