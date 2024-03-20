package restexamen2t.service;

import java.util.List;

import restexamen2t.modelo.entities.Empleado;


public interface EmpleadoService {

	Empleado buscarEmpleado(int idEmpleado);
	List<Empleado> buscarTodosLosEmpleados();
	Empleado altaEmpleado(Empleado empleado);
	int eliminarEmpleado(int idEmpleado);
	Empleado modificar(Empleado empleado);
	
	
}
