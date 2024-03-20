package pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pedidos.modelo.entities.Pedido;



public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query("select p from Pedido p where p.idPedido = ?1")
	public Pedido pedidoPorIdPedido(int idPedido);

	//Pedido por cliente, recibe el objeto y del objeto sacamos el id
	@Query("select p from Pedido p where p.cliente.idCliente = ?1")
	public List<Pedido> pedidosPorCliente(int idCliente);
	
	//Pedido por comercial
	@Query("select p from Pedido p join p.cliente c where p.comercial.idComercial = ?1")
	public List<Pedido> pedidosPorComercial(int idComercial);
	/*@Query("select p from Pedido p where p.comercial.idComercial = ?1")
	public List<Pedido> pedidosPorComercial(int idComercial);*/
	
	//Cliente por comercial
	// De aquí sacamos los pedidos y tenemos los clientes
	// Si queremos sacar solo los clientes ir al PedidoDaoImpl
	//@Query ("select p from Pedido p join Comercial c on c.idComercial")
	//@Query ("select p from Pedido p join p.comercial where p.comercial.idComercial = ?1")
	//@Query ("select c from Pedido p join p.cliente c join p.comercial com where com.idComercial = ?1")
	//public List<Pedido> clientesPorComercial (int idComercial);
	
	/*
	 * Yo la que intente pero no me salió, era dentro de comerciales y de ver el
	 * pedido, ver que cliente en concreto lo había hecho y k me mostrara los
	 * datos de ese cliente.
	 * 
	 * Estoy en comerciales, pues de ahi paso a la ventana de ver que pedidos
	 * tiene ese comercial, y dentro de los pedidos, señalar uno y que me diga
	 * que cliente lo ha hecho.
	 */
	

	
	
}
