package pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pedidos.modelo.entities.Pedido;
import pedidos.repository.PedidoRepository;

@Service
public class PedidoServiceMy8Impl implements PedidoService{

	@Autowired
	PedidoRepository prepo;
	
	@Override
	public List<Pedido> verTodosLosPedidos() {
		return prepo.findAll();
	}

	@Override
	public List<Pedido> buscarPedidosPorCliente(int idCliente) {
		return prepo.pedidosPorCliente(idCliente);
	}

	@Override
	public List<Pedido> buscaPedidosPorComercial(int idComercial) {
		return prepo.pedidosPorComercial(idComercial);
	}

	@Override
	public Pedido buscarUno(int idPedido) {
		return prepo.findById(idPedido).orElse(null);
	}

}
