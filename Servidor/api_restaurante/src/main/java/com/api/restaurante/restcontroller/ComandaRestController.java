package com.api.restaurante.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurante.modelo.entities.Comanda;

import com.api.restaurante.service.ComandaService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comanda")
public class ComandaRestController {
	
	@Autowired
	private ComandaService comandaService;
	
	
	@GetMapping("/buscarUno/{idComanda}")
	 public Comanda buscarUnaComanda(@PathVariable int idComanda) {
		 return comandaService.buscarUno(idComanda);
	 }

}
