package com.api.restaurante.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		
		@GetMapping("/buscarUno/{idMesa}")
		 public Mesa buscarUnEmpleado(@PathVariable int idMesa) {
			 return mesaService.buscarUna(idMesa);
		 }
}
