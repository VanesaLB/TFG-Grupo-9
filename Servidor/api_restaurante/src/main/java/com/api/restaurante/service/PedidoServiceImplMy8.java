package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Comanda;
import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.repository.PedidoRepository;

/**
 * Implementación del servicio de gestión de pedidos.
 * Proporciona métodos para buscar, añadir, eliminar y manipular pedidos en la base de datos MySQL
 * utilizando el repositorio vinculado con JPA.
 */
@Service
public class PedidoServiceImplMy8 implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Busca y devuelve un pedido específico basado en su identificador.
     *
     * @param idPedido El identificador único del pedido que se desea buscar.
     * @return El pedido correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Pedido buscarUno(int idPedido) {
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    /**
     * Busca y devuelve una lista de todos los pedidos almacenados en la base de datos.
     *
     * @return Una lista que contiene todos los pedidos almacenados en la base de datos MySQL.
     */
    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    /**
     * Añade una lista de pedidos al sistema.
     *
     * @param pedidosAlta La lista de pedidos que se desea añadir al sistema.
     * @return Una lista que contiene los pedidos añadidos al sistema.
     */
    @Override
    public List<Pedido> altaMuchos(List<Pedido> pedidosAlta) {
        return pedidoRepository.saveAll(pedidosAlta);
    }

    /**
     * Elimina un pedido del sistema basado en su identificador.
     *
     * @param idPedido El identificador único del pedido que se desea eliminar del sistema.
     * @return 1 si el pedido se eliminó exitosamente, 0 si ocurrió un error durante la eliminación
     *         y -1 si no se encontró un pedido con el identificador proporcionado.
     */
    @Override
    public int eliminarPedido(int idPedido) {
        if (buscarUno(idPedido) != null) {
            try {
                pedidoRepository.deleteById(idPedido);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        } else {
            return -1;
        }
    }

    /**
     * Añade un nuevo pedido al sistema si no existe un pedido con el mismo identificador.
     *
     * @param pedido El pedido que se desea añadir al sistema.
     * @return El pedido añadido al sistema, o null si ya existe un pedido con el mismo identificador.
     */
    @Override
    public Pedido altaUno(Pedido pedido) {
        if (buscarUno(pedido.getIdPedido()) == null)
            return pedidoRepository.save(pedido);
        else
            return null;
    }

    /**
     * Añade un nuevo pedido al sistema y devuelve su identificador.
     *
     * @param pedido El pedido que se desea añadir al sistema.
     * @return El identificador único del pedido añadido al sistema.
     */
    @Override
    public int altaPedidoId(Pedido pedido) {
        Pedido pedidoAux = new Pedido();
        int idPedidoAux = 0;
        if (buscarUno(pedido.getIdPedido()) == null)
            pedidoAux = pedidoRepository.save(pedido);
        idPedidoAux = pedidoAux.getIdPedido();
        return idPedidoAux;
    }
}
