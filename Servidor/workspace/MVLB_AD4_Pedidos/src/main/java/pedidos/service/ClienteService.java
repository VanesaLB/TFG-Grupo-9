package pedidos.service;

import java.util.List;

import pedidos.modelo.entities.Cliente;

public interface ClienteService {
	List<Cliente> verTodosLosClientes();
	Cliente buscarUno (int idCliente);
	Cliente insertarCliente(Cliente cliente);
	//Cliente delete (int idCliente);
	//Cliente modificarCliente (Cliente cliente);
	//List<Cliente> buscarClientesPorComercial (int idComercial);
	List<Cliente> clientesPorCategoria (Integer categoria);
}
