package restexamen2t.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restexamen2t.modelo.entities.Empleado;
import restexamen2t.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoRepository extends JpaRepository<EmpleadoEnProyecto, Integer> {
	//@Query("select DISTINCT p.comercial from Pedido p where p.cliente.idCliente = ?1")
		//public List<Comercial> buscarComercialesEnPedidosConMismoClienteRepoQuery(int idCliente);
		
		//@Query("SELECT DISTINCT p.comercial FROM Pedido p WHERE p.comercial IS NOT NULL")
		//public List<Comercial> buscarComercialesConPedidosRepoQuery();
		
		//@Query("select p from Pedido p where p.comercial.idComercial = ?1")
		//public List<Pedido> buscarPedidosPorComercialRepoQuery(int idComercial);
	
	@Query("select ep.empleado from EmpleadoEnProyecto ep where ep.proyecto.idProyecto = ?1")
	public List<Empleado> buscarEmpleadosDelMismoProyectoRepoQuery(int idProyecto);
}
