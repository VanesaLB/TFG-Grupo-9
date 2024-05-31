package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.repository.ProductoRepository;

/**
 * Implementación del servicio de gestión de productos.
 * Proporciona métodos para buscar, añadir, eliminar y manipular productos en la base de datos MySQL
 * utilizando el repositorio vinculado con JPA.
 */
@Service
public class ProductoServiceImplMy8 implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Busca y devuelve una lista de todos los productos almacenados en la base de datos.
     *
     * @return Una lista que contiene todos los productos almacenados en la base de datos MySQL.
     */
    @Override
    public List<Producto> buscarTodosLosProductos() {
        return productoRepository.findAll();
    }

    /**
     * Busca y devuelve un producto específico basado en su identificador.
     *
     * @param idProducto El identificador único del producto que se desea buscar.
     * @return El producto correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Producto buscarProducto(int idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    /**
     * Busca y devuelve una lista de productos que coinciden con un tipo específico.
     *
     * @param tipo El tipo de producto que se desea buscar.
     * @return Una lista que contiene todos los productos del tipo especificado.
     */
    @Override
    public List<Producto> buscarProductosPorTipo(String tipo) {
        return productoRepository.findByTipo(tipo);
    }

    /**
     * Busca y devuelve una lista de productos veganos.
     *
     * @return Una lista que contiene todos los productos veganos almacenados en la base de datos MySQL.
     */
    @Override
    public List<Producto> buscarProductosVeganos() {
        return productoRepository.findByVegano("si");
    }

    /**
     * Busca y devuelve una lista de productos sin gluten.
     *
     * @return Una lista que contiene todos los productos sin gluten almacenados en la base de datos MySQL.
     */
    @Override
    public List<Producto> buscarProductosSinGluten() {
        return productoRepository.findByGluten("no");
    }

    /**
     * Añade un nuevo producto al sistema si no existe un producto con el mismo identificador.
     *
     * @param producto El producto que se desea añadir al sistema.
     * @return El producto añadido al sistema, o null si ya existe un producto con el mismo identificador.
     */
    @Override
    public Producto altaUno(Producto producto) {
        if (buscarProducto(producto.getIdProducto()) == null)
            return productoRepository.save(producto);
        else
            return null;
    }

    /**
     * Elimina un producto del sistema basado en su identificador.
     *
     * @param idProducto El identificador único del producto que se desea eliminar del sistema.
     * @return 1 si el producto se eliminó exitosamente, 0 si ocurrió un error durante la eliminación
     *         y -1 si no se encontró un producto con el identificador proporcionado.
     */
    @Override
    public int eliminarProducto(int idProducto) {
        if (buscarProducto(idProducto) != null) {
            try {
                productoRepository.deleteById(idProducto);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
