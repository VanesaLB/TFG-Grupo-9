package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Mesa;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.repository.MesaRepository;

/**
 * Implementación del servicio de gestión de mesas.
 * Proporciona métodos para buscar, añadir, eliminar y manipular mesas en la base de datos MySQL
 * utilizando el repositorio vinculado con JPA.
 */
@Service
public class MesaServiceImplMy8 implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    /**
     * Busca y devuelve una lista de todas las mesas almacenadas en la base de datos.
     *
     * @return Una lista que contiene todas las mesas almacenadas en la base de datos MySQL.
     */
    @Override
    public List<Mesa> buscarTodos() {
        return mesaRepository.findAll();
    }

    /**
     * Busca y devuelve una mesa específica basada en su identificador.
     *
     * @param idMesa El identificador único de la mesa que se desea buscar.
     * @return La mesa correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Mesa buscarUna(int idMesa) {
        return mesaRepository.findById(idMesa).orElse(null);
    }

    /**
     * Elimina una mesa del sistema basada en su identificador.
     *
     * @param idMesa El identificador único de la mesa que se desea eliminar del sistema.
     * @return 1 si la mesa se eliminó exitosamente, 0 si ocurrió un error durante la eliminación
     *         y -1 si no se encontró una mesa con el identificador proporcionado.
     */
    @Override
    public int eliminarMesa(int idMesa) {
        if (buscarUna(idMesa) != null) {
            try {
                mesaRepository.deleteById(idMesa);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        } else {
            return -1;
        }
    }

    /**
     * Añade una nueva mesa al sistema si no existe una mesa con el mismo identificador.
     *
     * @param mesa La mesa que se desea añadir al sistema.
     * @return La mesa añadida al sistema, o null si ya existe una mesa con el mismo identificador.
     */
    @Override
    public Mesa altaUno(Mesa mesa) {
        if (buscarUna(mesa.getIdMesa()) == null)
            return mesaRepository.save(mesa);
        else
            return null;
    }
}
