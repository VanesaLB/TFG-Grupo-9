package com.api.restaurante.restcontroller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.dto.ComandaDto;
import com.api.restaurante.modelo.dto.ComandaServidoSiDto;
import com.api.restaurante.modelo.dto.PedidoDto;
import com.api.restaurante.modelo.entities.Comanda;
import com.api.restaurante.modelo.entities.Mesa;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.service.ComandaService;
import com.api.restaurante.service.MesaService;
import com.api.restaurante.service.PedidoService;
import com.api.restaurante.service.ProductoService;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comanda")
public class ComandaRestController {
	
	@Autowired
	private ComandaService comandaService;
	
	@Autowired
    private PedidoService pedidoService; // Servicio para gestionar pedidos

    @Autowired
    private ProductoService productoService; // Servicio para gestionar productos

    @Autowired
    private MesaService mesaService; // Servicio para gestionar mesas

    @GetMapping("/buscarTodos")
	 public List<Comanda> buscarTodosLasComandas() {
		 return comandaService.buscarTodos();
	 }
	
	
	@GetMapping("/buscarUno/{idComanda}")
	 public Comanda buscarUnaComanda(@PathVariable int idComanda) {
		 return comandaService.buscarUno(idComanda);
	 }
	
	@GetMapping("/comandasServidoNo/{servido}")
	public List<Comanda> buscarEmpleamosDelMismoProyecto (@PathVariable String servido) {
		return comandaService.buscarComandasServidoNo(servido);
	}
	
	//Este método recibe un JSON con idEntrada y diasPrevistos, y devuelve el objeto completo modificado
		@PutMapping("/modificarServidoSi/{id}")
		public Comanda modificarDiasPrevistoEnEmpleadoEnProyecto(@RequestBody ComandaServidoSiDto 
				comandaServidoSiDto
				) {
			Comanda comanda = comandaService.buscarUno(comandaServidoSiDto.getIdComanda());
			comanda.setServido(comandaServidoSiDto.getServido());
			return comandaService.modificarServidoSi(comanda);
			
		}
		
		
	@PostMapping("/alta")
	   
    
	 public ResponseEntity<Comanda> createComanda(@RequestBody ComandaDto comandadto) {
	        try {
	            // Creo un nuevo pedido
	            Pedido nuevoPedido = new Pedido();
	            // Asigno la mesa por su ID 
	            Mesa mesa = mesaService.buscarUna(comandadto.getIdMesa()); 
	            nuevoPedido.setMesa(mesa);
	            nuevoPedido.setFecha(new Date());
	            // Guardo el nuevo pedido 
	            Pedido savedPedido = pedidoService.altaUno(nuevoPedido);
	            
	            // Creo una nueva comanda asociada al pedido recién creado
	            Comanda nuevaComanda = new Comanda();
	            Producto producto=productoService.buscarProducto(comandadto.getIdProducto());
	            nuevaComanda.setProducto(producto);
	            Mesa mesa1 = mesaService.buscarUna(comandadto.getIdMesa()); 
	            nuevaComanda.setMesa(mesa1); // Asigno la mesa por su ID
	            nuevaComanda.setServido(comandadto.getServido());
	            nuevaComanda.setPedido(savedPedido); // Asigno el pedido creado
	            
	            // Guardo la nueva comanda
	            Comanda savedComanda = comandaService.altaUno(nuevaComanda);
	            return ResponseEntity.ok(savedComanda);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	
	
	@PostMapping("/altaMuchos")
	 public List<Comanda> darDeAltaMuchos(@RequestBody List<ComandaDto> comandasDto) {
	 		
	 		
	 		List<Comanda> comandas = new ArrayList<>();
	 		
	 		for (ComandaDto dto : comandasDto) {	
	 	        Comanda comandaAux  = new Comanda();
	 	       
	 	      // modelMapper.map(dto, pedidoAux );
	 	       
	 	      comandaAux.setProducto(productoService.buscarProducto(dto.getIdProducto()));
	 	      comandaAux.setPedido(pedidoService.buscarUno(dto.getIdPedido()));
	 	      comandaAux.setMesa(mesaService.buscarUna(dto.getIdMesa()));
	 	      comandaAux.setServido(dto.getServido());
	 	    
	 	     
	 	      
	 	        // Luego puedes realizar otras operaciones necesarias, como asignar el empleado y el proyecto
	 	        // empleadoEnProyecto.setEmpleado(empleadoService.buscarEmpleado(dto.getIdEmpleado()));
	 	        // empleadoEnProyecto.setProyecto(proyectoService.buscarProyecto(dto.getIdProyecto()));
	 	        
	 	        // Agrega cada objeto EmpleadoEnProyecto mapeado a la lista
	 	     comandas.add(comandaAux);
	 	        
	 	
	 	}
	 		return comandaService.altaMuchos(comandas); 
	 }
	 	/*
	 	 * PARA POSTMAN:
	 	 * [

	 {
	     "idProducto": 1,
	     "idPedido": 1,
	     "idMesa": 2,
	     "servido": "si"
	 },

	 {
	     "idProducto": 1,
	     "idPedido": 1,
	     "idMesa": 2,
	     "servido": "si"
	 },

	 {
	     "idProducto": 1,
	     "idPedido": 1,
	     "idMesa": 2,
	     "servido": "si"
	 }
	 ]
	 	 */
	

}
