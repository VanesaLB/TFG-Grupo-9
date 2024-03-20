package pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pedidos.modelo.entities.Comercial;
import pedidos.modelo.entities.Pedido;


public interface ComercialRepository extends JpaRepository<Comercial, Integer>{
	
	@Query("select c from Comercial c where c.idComercial = ?1")
	public Comercial comercialPorIdComercial (int idComercial);
	
	//Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.
	@Query("SELECT DISTINCT p.comercial FROM Pedido p WHERE p.cliente.idCliente = ?1")
	public List<Comercial> comercialAtiendePedidosPorCliente(int idCliente);
	
	//Devolver la lista de comerciales que han atendido algún pedido
	//Probar con DISTINCT
	@Query("select c from Comercial c join Pedido p ON c.idComercial=p.comercial.idComercial")
	public List<Comercial> comercialConPedidos();

}
