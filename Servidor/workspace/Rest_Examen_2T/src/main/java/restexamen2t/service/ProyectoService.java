package restexamen2t.service;

import java.util.List;

import restexamen2t.modelo.entities.Proyecto;

public interface ProyectoService {

	List<Proyecto> buscarTodosLosProyectos();
	Proyecto buscarProyecto(int idProyecto);
	Proyecto altaProyecto(Proyecto proyecto);
	int eliminarProyecto(int idProyecto);
	Proyecto modificarProyecto(Proyecto proyecto);
}
