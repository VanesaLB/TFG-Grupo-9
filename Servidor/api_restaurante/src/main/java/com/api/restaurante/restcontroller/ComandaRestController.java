package com.api.restaurante.restcontroller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.dto.ComandaDto;
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

	
	
	@GetMapping("/buscarUno/{idComanda}")
	 public Comanda buscarUnaComanda(@PathVariable int idComanda) {
		 return comandaService.buscarUno(idComanda);
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
	            Pedido savedPedido = pedidoService.save(nuevoPedido);
	            
	            // Creo una nueva comanda asociada al pedido recién creado
	            Comanda nuevaComanda = new Comanda();
	            Producto producto=productoService.buscarProducto(comandadto.getIdProducto());
	            nuevaComanda.setProducto(producto);
	            Mesa mesa1 = mesaService.buscarUna(comandadto.getIdMesa()); 
	            nuevaComanda.setMesa(mesa1); // Asigno la mesa por su ID
	            nuevaComanda.setServido(comandadto.getServido());
	            nuevaComanda.setPedido(savedPedido); // Asigno el pedido creado
	            
	            // Guardo la nueva comanda
	            Comanda savedComanda = comandaService.save(nuevaComanda);
	            return ResponseEntity.ok(savedComanda);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }


}
