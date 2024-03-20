package restexamen2t.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restexamen2t.modelo.entities.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
	//@Query("select DISTINCT p.comercial from Pedido p where p.cliente.idCliente = ?1")
		//public List<Comercial> buscarComercialesEnPedidosConMismoClienteRepoQuery(int idCliente);
		
		//@Query("SELECT DISTINCT p.comercial FROM Pedido p WHERE p.comercial IS NOT NULL")
		//public List<Comercial> buscarComercialesConPedidosRepoQuery();
		
		//@Query("select p from Pedido p where p.comercial.idComercial = ?1")
		//public List<Pedido> buscarPedidosPorComercialRepoQuery(int idComercial); 
}
