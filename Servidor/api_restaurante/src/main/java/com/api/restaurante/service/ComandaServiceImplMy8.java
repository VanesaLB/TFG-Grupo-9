package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Comanda;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.repository.ComandaRepository;
import com.api.restaurante.repository.PedidoRepository;


/**
 * Implementación del servicio de gestión de comandas.
 * Proporciona métodos para buscar, añadir, modificar y manipular comandas en la base de datos MySQL
 * utilizando los repositorios vinculados con JPA.
 */
@Service
public class ComandaServiceImplMy8 implements ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Busca y devuelve una comanda específica basada en su identificador.
     *
     * @param idComanda El identificador único de la comanda que se desea buscar.
     * @return La comanda correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Comanda buscarUno(int idComanda) {
        return comandaRepository.findById(idComanda).orElse(null);
    }

    /**
     * Busca y devuelve una lista de todas las comandas almacenadas en la base de datos.
     *
     * @return Una lista que contiene todas las comandas almacenadas en la base de datos MySQL.
     */
    @Override
    public List<Comanda> buscarTodos() {
        return comandaRepository.findAll();
    }

    /**
     * Añade una lista de comandas al sistema.
     *
     * @param comandasAlta La lista de comandas que se desea añadir al sistema.
     * @return Una lista que contiene las comandas añadidas al sistema.
     */
    @Override
    public List<Comanda> altaMuchos(List<Comanda> comandasAlta) {
        return comandaRepository.saveAll(comandasAlta);
    }

    /**
     * Añade una nueva comanda al sistema si no existe una comanda con el mismo identificador.
     *
     * @param comanda La comanda que se desea añadir al sistema.
     * @return La comanda añadida al sistema, o null si ya existe una comanda con el mismo identificador.
     */
    @Override
    public Comanda altaUno(Comanda comanda) {
        if (buscarUno(comanda.getIdComanda()) == null)
            return comandaRepository.save(comanda);
        else
            return null;
    }

    /**
     * Busca y devuelve una lista de comandas que no han sido servidas.
     *
     * @param servido El estado de servicio que se desea buscar ("no" indica que no ha sido servida).
     * @return Una lista que contiene las comandas que no han sido servidas.
     */
    @Override
    public List<Comanda> buscarComandasServidoNo(String servido) {
        return comandaRepository.buscarComandasServidoNoRepoQuery(servido);
    }

    /**
     * Modifica el estado de servicio de una comanda a "si" si la comanda existe en el sistema.
     *
     * @param comanda La comanda que se desea modificar.
     * @return La comanda modificada si existe en el sistema, o null si no se encuentra.
     */
    @Override
    public Comanda modificarServidoSi(Comanda comanda) {
        if (buscarUno(comanda.getIdComanda()) != null) {
            comandaRepository.save(comanda);
            return comanda;
        } else {
            return null;
        }
    }
}
