package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Tipo;
import eventos.modelo.repository.TipoRepository;

@Repository
public class TipoDaoImplMy8 implements TipoDao{
	
	/** 
	 * Repositorio de tipos de eventos utilizado para acceder y manipular datos relacionados con tipos de eventos en la base de datos.
	 * 
	 * @Autowired Indica la inyección de dependencia de un bean TipoRepository en esta clase.
	 */
	@Autowired
	private TipoRepository trepo;

	/**
	 * Este método utiliza el repositorio de tipos de eventos (trepo) para buscar el tipo de evento por su identificador.
	 * Retorna un objeto Tipo si se encuentra, o null si no hay coincidencias.
	 * En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param idTipo El identificador del tipo de evento a buscar.
	 * @return El objeto Tipo correspondiente al identificador proporcionado, o null si no se encuentra.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	@Override
	public Tipo buscarTipoPorID(int idTipo) {
		// TODO Auto-generated method stub
		return trepo.findById(idTipo).orElse(null);
	}

	/**
	 * Este método utiliza el repositorio de tipos de eventos (trepo) para recuperar todos los tipos de eventos almacenados.
	 * Retorna una lista que puede contener todos los tipos de eventos disponibles en la base de datos.
	 * En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @return Una lista de objetos Tipo que representa todos los tipos de eventos disponibles.
	 *         La lista puede estar vacía si no hay tipos de eventos almacenados.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * 
	 */
	@Override
	public List<Tipo> buscarTodosLosTipos() {
		// TODO Auto-generated method stub
		return trepo.findAll();
	}

}
