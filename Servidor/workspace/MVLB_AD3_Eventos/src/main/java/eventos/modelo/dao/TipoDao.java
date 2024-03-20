package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Tipo;

public interface TipoDao {
	
	Tipo buscarTipoPorID (int idTipo);
	List<Tipo> buscarTodosLosTipos();

}
