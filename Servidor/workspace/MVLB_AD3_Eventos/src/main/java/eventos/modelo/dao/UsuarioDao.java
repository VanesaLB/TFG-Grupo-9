package eventos.modelo.dao;

import eventos.modelo.entitis.Usuario;

public interface UsuarioDao {
	
	Usuario buscarUsuarioPorUsernamePassword(String username, String password);
	Usuario buscarPorUsername (String username);
	int altaUsuario(Usuario usuario);

}
