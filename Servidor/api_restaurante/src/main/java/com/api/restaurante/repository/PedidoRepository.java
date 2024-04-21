package com.api.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
