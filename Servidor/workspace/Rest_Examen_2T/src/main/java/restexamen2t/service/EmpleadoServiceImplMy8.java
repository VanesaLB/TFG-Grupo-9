package restexamen2t.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restexamen2t.modelo.entities.Empleado;
import restexamen2t.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImplMy8 implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public Empleado buscarEmpleado(int idEmpleado) {
		// TODO Auto-generated method stub
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}

	@Override
	public List<Empleado> buscarTodosLosEmpleados() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll() ;
	}

	@Override
	public Empleado altaEmpleado(Empleado empleado) {
		if (buscarEmpleado(empleado.getIdEmpleado()) == null)
		return empleadoRepository.save(empleado);
		else
			return null;
	}

	@Override
	public int eliminarEmpleado(int idEmpleado) {
		
		if (buscarEmpleado(idEmpleado)!=null) {
		try {
			empleadoRepository.deleteById(idEmpleado);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		}
		else
			return -1;
	}

	@Override
	public Empleado modificar(Empleado empleado) {
		if(buscarEmpleado(empleado.getIdEmpleado()) != null)
			return empleadoRepository.save(empleado);
		else
			return null;
	}

}
