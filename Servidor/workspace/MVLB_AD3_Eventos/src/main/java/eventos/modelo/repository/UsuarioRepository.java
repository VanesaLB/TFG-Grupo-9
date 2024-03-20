package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entitis.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	//Buscar usuario por nombre y contraseña
	public Usuario findByUsernameAndPassword(String username, String password);
	//Buscar usuario por nombre
	public Usuario findByUsername (String username);

}
