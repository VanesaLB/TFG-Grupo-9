package com.api.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
