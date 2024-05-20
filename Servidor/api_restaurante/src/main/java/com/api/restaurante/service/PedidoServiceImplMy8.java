package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Pedido;
import com.api.restaurante.modelo.entities.Producto;
import com.api.restaurante.repository.PedidoRepository;

@Service
public class PedidoServiceImplMy8 implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public Pedido buscarUno(int idPedido) {
		// TODO Auto-generated method stub
		return pedidoRepository.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> altaMuchos(List<Pedido> pedidosAlta) {
		return pedidoRepository.saveAll(pedidosAlta);
	}

	@Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


}
