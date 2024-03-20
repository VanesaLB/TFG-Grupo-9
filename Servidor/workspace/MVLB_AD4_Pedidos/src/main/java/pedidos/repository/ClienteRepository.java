package pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pedidos.modelo.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("select c from Cliente c where c.idCliente = ?1")
	public Cliente clientePorIdCliente (int idCliente);
	
	//Clientes por categoría
	@Query("select c from Cliente c where c.categoria = ?1")
	public List<Cliente> clientesCategoria (Integer categoria);
	
	//Clientes por comercial, que no he comprobado que funcione
	//@Query ("select c from Pedido p join p.cliente c join p.comercial com where com.idComercial = ?1")
	//public List<Cliente> clientesPorComercial (int idComercial);

}
