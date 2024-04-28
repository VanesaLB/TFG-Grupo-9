package com.api.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	public List<Producto> findByTipo(String tipo);
	public List<Producto> findByVegano(String vegano);
	public List<Producto> findByGluten(String gluten);
	}


//@Query("select p from Producto p where p.color = ?1 AND p.marca = ?2")