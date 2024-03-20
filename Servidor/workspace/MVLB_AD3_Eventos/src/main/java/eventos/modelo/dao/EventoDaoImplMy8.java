package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.repository.EventoRepository;

@Repository
public class EventoDaoImplMy8 implements EventoDao{
	/**
	 * Repositorio de eventos utilizado para acceder y manipular datos relacionados con eventos en la base de datos.
	 * 
	 * @Autowired Indica la inyección de dependencia de un bean EventoRepository en esta clase.
	 */
	@Autowired
	private EventoRepository erepo;
	
	/**
	 * Lista que almacena eventos para su posterior manipulación.
	 * Puede ser utilizada para almacenar temporalmente eventos recuperados de la base de datos.
	 * La lista puede ser nula si no se ha inicializado.
	 */
	private List<Evento> listaEventos;
	
	/**
	 * 
	 *  Este método utiliza el repositorio de eventos (erepo) para buscar el evento por su identificador.
	 *  Retorna un objeto Evento si se encuentra, o null si no hay coincidencias.
	 *  Este método implementa la interfaz con la anotación @Override.
	 *  
	 * @param idEvento El identificador del evento a buscar.
	 * @return El objeto Evento correspondiente al identificador proporcionado, o null si no se encuentra.
	 * 
	 */
	@Override
	public Evento buscarEventoPorId(int idEvento) {
		// TODO Auto-generated method stub
		return erepo.findById(idEvento).orElse(null);
	}

	/**
	 * Este método utiliza el repositorio de eventos (erepo) para recuperar todos los eventos almacenados.
	 * Retorna una lista que puede contener todos los eventos disponibles en la base de datos.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @return Una lista de objetos Evento que representa todos los eventos disponibles.
	 *         La lista puede estar vacía si no hay eventos almacenados.
	 * 
	 */
	@Override
	public List<Evento> buscarTodosLosEventos() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}

	/**
	 * Este método utiliza el repositorio de eventos (erepo) para recuperar todos los eventos almacenados.
	 * Luego, filtra la lista para obtener solo los eventos cuyo estado es "Activo".
	 * Recupera todos los eventos activos almacenados en la base de datos.
	 * Este método puede retornar una lista vacía si no hay eventos activos.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @return Una lista de objetos Evento que representan todos los eventos activos disponibles.
	 *         La lista puede estar vacía si no hay eventos activos almacenados
	 * 
	 */
	@Override
	public List<Evento> buscarEventosPorActivo() {
		listaEventos = erepo.findAll();
		
		return listaEventos
				.stream()
				.filter(x -> x.getEstado().equalsIgnoreCase("Activo"))
				.toList();
	}

	/**
	 * Este método utiliza el repositorio de eventos (erepo) para recuperar todos los eventos almacenados.
	 * Luego, filtra la lista para obtener solo los eventos que están marcados como "destacados".
	 * Recupera todos los eventos destacados almacenados en la base de datos.
	 * Este método puede retornar una lista vacía si no hay eventos destacados.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @return Una lista de objetos Evento que representan todos los eventos destacados disponibles.
	 *         La lista puede estar vacía si no hay eventos destacados almacenados.
	 * 
	 */
	@Override
	public List<Evento> buscarEventosPorDestacado() {
		listaEventos = erepo.findAll();
		
		return listaEventos
				.stream()
				.filter(x -> x.getDestacado().equalsIgnoreCase("s"))
				.toList();
	}

	/**
	 * Este método utiliza un método personalizado del repositorio de eventos (erepo)
	 * para recuperar eventos del tipo especificado utilizando una consulta personalizada.
	 * Este método puede retornar una lista vacía si no hay eventos del tipo especificado.
	 * Recupera todos los eventos del tipo especificado almacenados en la base de datos.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param idTipo El identificador del tipo de evento a buscar.
	 * @return Una lista de objetos Evento que representan todos los eventos del tipo especificado.
	 *         La lista puede estar vacía si no hay eventos del tipo especificado almacenados.
	 * 
	 */
	@Override
	public List<Evento> buscarEventosPorTipo(int idTipo) {
	    System.out.println("Buscando eventos por tipo: " + idTipo);
	    return erepo.buscarEventosPorTipoRepoQuery(idTipo);
	}

}
