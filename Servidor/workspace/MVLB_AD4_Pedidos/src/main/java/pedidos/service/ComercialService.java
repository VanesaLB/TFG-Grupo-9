package pedidos.service;

import java.util.List;

import pedidos.modelo.entities.Comercial;

public interface ComercialService {

	List<Comercial> buscarTodosLosComerciales();
	Comercial buscarUno (int idComercial);
	Comercial insertarComercial(Comercial comercial);
	boolean eliminar (int idComercial);
	Comercial modificarComercial (Comercial comercial);
	List<Comercial> buscarComercialesPorPedidosDeCliente(int idCliente);
	List<Comercial> buscarComercialesConPedidos();
	//int modificarComercial (Comercial comercial);
}
