package restexamen2t.service;

import java.util.List;

import restexamen2t.modelo.dto.EmpleadoEnProyectoDto;
import restexamen2t.modelo.dto.EmpleadoEnProyectoDtoDiasPrevistos;
import restexamen2t.modelo.entities.Empleado;
import restexamen2t.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoService {

	List<EmpleadoEnProyecto> buscarTodosLosEmpleadosEnProyecto();
	EmpleadoEnProyecto buscarUno(int idEntrada);
	EmpleadoEnProyecto alta(EmpleadoEnProyecto empleadoEnProyecto);
	int eliminar(int idEntrada);
	EmpleadoEnProyecto modificar(EmpleadoEnProyecto empleadoEnProyecto);
	EmpleadoEnProyecto modificarDiasPrevistos(EmpleadoEnProyecto empleadoEnProyecto);
	List<Empleado> buscarEmpleadosDelMismoProyecto(int idProyecto);
}
