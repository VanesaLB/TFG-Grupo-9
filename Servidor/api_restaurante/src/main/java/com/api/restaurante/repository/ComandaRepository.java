package com.api.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.restaurante.modelo.entities.Comanda;



public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
	
	@Query("select c from Comanda c where c.servido = ?1")
	public List<Comanda> buscarComandasServidoNoRepoQuery(String servido);
}
