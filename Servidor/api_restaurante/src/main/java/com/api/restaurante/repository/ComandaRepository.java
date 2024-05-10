package com.api.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Comanda;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
	

}
