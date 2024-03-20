package pedidos.service;

import java.util.List;

import pedidos.modelo.entities.Pedido;

public interface PedidoService {
	
	List<Pedido> verTodosLosPedidos();
	List<Pedido> buscarPedidosPorCliente(int idCliente); 
	List<Pedido> buscaPedidosPorComercial(int idComercial);
	//Pedido insertarPedido(Pedido pedido);
	Pedido buscarUno (int idPedido);
	//Pedido delete (int idPedido);
	//Pedido modificarPedido (Pedido pedido);

}
