package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;


public interface EventoRepository extends JpaRepository<Evento, Integer>{

	//Buscar eventos por tipo
	//Tipo es un objeto que va dentro del objeto Evento
	@Query("select e from Evento e where e.tipo.idTipo = ?1")
	public List<Evento> buscarEventosPorTipoRepoQuery(int idTipo);
	
}
