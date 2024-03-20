package pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pedidos.modelo.entities.Cliente;
import pedidos.repository.ClienteRepository;

@Service
public class ClienteServiceMy8Impl implements ClienteService{
	
	@Autowired
	ClienteRepository crepo;

	@Override
	public List<Cliente> verTodosLosClientes() {
		return crepo.findAll();
	}

	@Override
	public Cliente buscarUno(int idCliente) {
		return crepo.findById(idCliente).orElse(null);
	}

	@Override
	public Cliente insertarCliente(Cliente cliente) {
		return crepo.save(cliente);
	}

	@Override
	public List<Cliente> clientesPorCategoria(Integer categoria) {
		return crepo.clientesCategoria(categoria);
	}

}
