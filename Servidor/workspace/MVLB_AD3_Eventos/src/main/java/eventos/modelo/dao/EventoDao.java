package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;

public interface EventoDao {

	Evento buscarEventoPorId(int idEvento);
	List<Evento> buscarTodosLosEventos();
	List<Evento> buscarEventosPorActivo();
	List<Evento> buscarEventosPorDestacado();
	List<Evento> buscarEventosPorTipo (int idTipo);
}
