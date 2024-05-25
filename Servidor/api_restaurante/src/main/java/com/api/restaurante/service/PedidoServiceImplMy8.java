package com.api.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurante.modelo.entities.Comanda;
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
		return pedidoRepository.findAll();
	}

	@Override
	public List<Pedido> altaMuchos(List<Pedido> pedidosAlta) {
		return pedidoRepository.saveAll(pedidosAlta);
	}

	
	@Override
	public int eliminarPedido(int idPedido) {
		if (buscarUno(idPedido)!= null)
			try {
				pedidoRepository.deleteById(idPedido);
				return 1;
			} catch (Exception e) {
				return 0;
			}else
				return -1;
	}

	@Override
	public Pedido altaUno(Pedido pedido) {
		if (buscarUno(pedido.getIdPedido()) == null)
			return pedidoRepository.save(pedido);
			else
				return null;
	}

	@Override
	public int altaPedidoId(Pedido pedido) {
		Pedido pedidoaux = new Pedido();
		int idPedidoAux = 0;
		if (buscarUno(pedido.getIdPedido()) == null)
			pedidoaux = pedidoRepository.save(pedido);
		idPedidoAux = pedidoaux.getIdPedido();
		return idPedidoAux;
				
			
	}


}
