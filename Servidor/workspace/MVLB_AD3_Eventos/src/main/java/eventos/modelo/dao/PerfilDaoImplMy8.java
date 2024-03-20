package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Perfil;
import eventos.modelo.repository.PerfilRepository;

@Repository
public class PerfilDaoImplMy8 implements PerfilDao{
	/**
	 * Repositorio de perfiles utilizado para acceder y manipular datos relacionados con perfiles en la base de datos.
	 * 
	 * @Autowired Indica la inyección de dependencia de un bean PerfilRepository en esta clase.
	 */
	@Autowired
	private PerfilRepository prepo;

	/**
	 * Este método utiliza el repositorio de perfiles (prepo) para buscar el perfil por su identificador.
	 * Retorna un objeto Perfil si se encuentra, o null si no hay coincidencias.
	 * Busca un perfil por su identificador único.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param idPerfil El identificador del perfil a buscar.
	 * @return El objeto Perfil correspondiente al identificador proporcionado, o null si no se encuentra.
	 * 
	 */
	@Override
	public Perfil buscarPerfilPorId(int idPerfil) {
		// TODO Auto-generated method stub
		return prepo.findById(idPerfil).orElse(null);
	}

}
