package com.api.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurante.modelo.entities.Mesa;

public interface MesaRepository extends JpaRepository <Mesa,Integer> {

}
