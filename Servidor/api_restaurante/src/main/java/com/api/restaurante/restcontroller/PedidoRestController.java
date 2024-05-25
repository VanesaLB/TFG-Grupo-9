package com.api.restaurante.restcontroller;

import java.util.ArrayList;
import java.util.Date;
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

import com.api.restaurante.modelo.dto.PedidoDto;
import com.api.restaurante.modelo.entities.Pedido;

import com.api.restaurante.service.MesaService;
import com.api.restaurante.service.PedidoService;
import com.api.restaurante.service.ProductoService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")
public class PedidoRestController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private MesaService mesaService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/buscarTodos")
	 public List<Pedido> buscarTodosLosPedidos() {
		 return pedidoService.buscarTodos();
	 }
	
	@GetMapping("/buscarUno/{idPedido}")
	 public Pedido buscarUnEmpleado(@PathVariable int idPedido) {
		 return pedidoService.buscarUno(idPedido);
	 }
	
	@DeleteMapping("/eliminar/{idPedido}")
	public String eliminarElProyecto(@PathVariable int idPedido) {
	
		switch (pedidoService.eliminarPedido(idPedido)) {
		
		case 1: return "Pedido eliminado correctamente";
		case 0: return "No se pudo eliminar el Pedido porque FK en otra tabla";
		case -1: return "No se pudo eliminar el Pedido porque NO existe";
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + pedidoService.eliminarPedido(idPedido));
		}
		
	}
	
	@PostMapping("/alta")
	public Pedido altaDelPedido(@RequestBody Pedido pedido) {
		
		return pedidoService.altaUno(pedido);
		}
	
	@PostMapping("/altaPedidoId")
	public int altaDelPedidoId(@RequestBody PedidoDto pedidoDto) {
		
		
		Pedido pedidoAux = new Pedido();
		pedidoAux.setMesa(mesaService.buscarUna(pedidoDto.getIdMesa()));
		pedidoAux.setCantidadProductos(pedidoDto.getCantidadProductos());
		pedidoAux.setPrecioTotal(pedidoDto.getPrecioTotal());
		pedidoAux.setFecha(pedidoDto.getFecha());
		
		return pedidoService.altaPedidoId(pedidoAux);
		}
	@PostMapping("/altaMuchos")
	 public List<Pedido> darDeAltaMuchos(@RequestBody List<PedidoDto> pedidosDto) {
	 		
	 		
	 		List<Pedido> pedidos = new ArrayList<>();
	 		
	 		for (PedidoDto dto : pedidosDto) {	
	 	        Pedido pedidoAux  = new Pedido();
	 	       
	 	      // modelMapper.map(dto, pedidoAux );
	 	       pedidoAux.setMesa(mesaService.buscarUna(dto.getIdMesa()));
	 	       pedidoAux.setCantidadProductos(dto.getCantidadProductos());
	 	       pedidoAux.setPrecioTotal(dto.getPrecioTotal());
	 	       pedidoAux.setFecha(dto.getFecha());
	 	     
	 	      
	 	        // Luego puedes realizar otras operaciones necesarias, como asignar el empleado y el proyecto
	 	        // empleadoEnProyecto.setEmpleado(empleadoService.buscarEmpleado(dto.getIdEmpleado()));
	 	        // empleadoEnProyecto.setProyecto(proyectoService.buscarProyecto(dto.getIdProyecto()));
	 	        
	 	        // Agrega cada objeto EmpleadoEnProyecto mapeado a la lista
	 	       pedidos.add(pedidoAux);
	 	        
	 	
	 	}
	 		return pedidoService.altaMuchos(pedidos); 
	 }
	 	/*
	 	 * PARA POSTMAN:
	 	 * [

	 {
	     "idProyecto": 1,
	     "idEmpleado": 1,
	     "diasPrevistos": 21,
	     "fechaIncorporacion": "2024-10-08T22:00:00.000+00:00"
	 },

	 {
	     "idProyecto": 1,
	     "idEmpleado": 1,
	     "diasPrevistos": 31,
	     "fechaIncorporacion": "2024-10-08T22:00:00.000+00:00"
	 },

	 {
	     "idProyecto": 1,
	     "idEmpleado": 1,
	     "diasPrevistos": 41,
	     "fechaIncorporacion": "2024-10-08T22:00:00.000+00:00"
	 }
	 ]
	 	 */
	
	
	
	
}
