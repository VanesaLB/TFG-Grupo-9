package com.api.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	List<Producto> findByTipo(String tipo);
	List<Producto> findByVegano(String vegano);
	List<Producto> findByGluten(String gluten);
	}


