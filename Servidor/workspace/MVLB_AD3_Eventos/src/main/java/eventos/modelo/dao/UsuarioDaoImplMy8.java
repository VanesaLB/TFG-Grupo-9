package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao{
	/**
	 * Repositorio de usuarios utilizado para acceder y manipular datos relacionados con usuarios en la base de datos.
	 * 
	 * @Autowired Indica la inyección de dependencia de un bean UsuarioRepository en esta clase.
	 */
	@Autowired
	private UsuarioRepository urepo;

	/**
	 *  Este método utiliza el repositorio de usuarios (urepo) para buscar un usuario por su nombre de usuario y contraseña.
	 *  Retorna un objeto Usuario si se encuentra, o null si no hay coincidencias.
	 *  En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
	 *  Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param username El nombre de usuario del usuario a buscar.
	 * @param password La contraseña del usuario a buscar.
	 * @return El objeto Usuario correspondiente al nombre de usuario y contraseña proporcionados, o null si no se encuentra.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	@Override
	public Usuario buscarUsuarioPorUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		return urepo.findByUsernameAndPassword(username, password);
	}

	/**
     * Este método utiliza el repositorio de usuarios (urepo) para buscar un usuario por su nombre de usuario.
     * Retorna un objeto Usuario si se encuentra, o null si no hay coincidencias.
     * En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
     * Este método implementa la interfaz con la anotación @Override.
     * 
     * @param username El nombre de usuario del usuario a buscar.
     * @return El objeto Usuario correspondiente al nombre de usuario proporcionado, o null si no se encuentra.
     * 
     */
	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		return urepo.findByUsername(username);
	}

	/**
	 * Este método utiliza el repositorio de usuarios (urepo) para guardar el nuevo usuario.
	 * Retorna 1 si la operación es exitosa, y 0 si ocurre una excepción durante el acceso a la base de datos.
	 * Este método no se encarga de gestionar las excepciones generadas durante la operación,
	 * simplemente imprime el mensaje de error en la consola y retorna 0.
	 * 
	 * @param usuario Objeto Usuario que se va a registrar.
	 * @return Un entero que indica el resultado de la operación:
	 *         - 1 si el usuario se registró con éxito.
	 *         - 0 si ocurrió un error durante el registro.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */	
	@Override
	public int altaUsuario(Usuario usuario) {
		
		    try {
				urepo.save(usuario);
			  return 1;  
			}catch (Exception e) {
				e.getMessage();
				return 0;
			} 
		}
	
	

}
