package restexamen2t.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restexamen2t.modelo.dto.EmpleadoEnProyectoDto;
import restexamen2t.modelo.dto.EmpleadoEnProyectoDtoDiasPrevistos;
import restexamen2t.modelo.entities.Empleado;
import restexamen2t.modelo.entities.EmpleadoEnProyecto;
import restexamen2t.repository.EmpleadoEnProyectoRepository;

@Service
public class EmpleadoEnProyectoImplMy8 implements EmpleadoEnProyectoService {

	@Autowired
	private EmpleadoEnProyectoRepository empleadoEnProyectoRepository;
	
	@Override
	public List<EmpleadoEnProyecto> buscarTodosLosEmpleadosEnProyecto() {
		// TODO Auto-generated method stub
		return empleadoEnProyectoRepository.findAll();
	}

	@Override
	public EmpleadoEnProyecto buscarUno(int idEntrada) {
		return empleadoEnProyectoRepository.findById(idEntrada).orElse(null);
	}

	@Override
	public EmpleadoEnProyecto alta(EmpleadoEnProyecto empleadoEnProyecto) {
		if (buscarUno(empleadoEnProyecto.getIdEntrada()) == null)
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
			else
				return null;
	}

	@Override
	public int eliminar(int idEntrada) {
		if (buscarUno(idEntrada) != null)
			try {
				empleadoEnProyectoRepository.deleteById(idEntrada);
				return 1;
			} catch (Exception e) {
				return 0;
			}else
				return -1;
	}

	@Override
	public EmpleadoEnProyecto modificar(EmpleadoEnProyecto empleadoEnProyecto) {
		if (buscarUno(empleadoEnProyecto.getIdEntrada())!= null)
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
			else
				return null;
		
	}

	@Override
	public EmpleadoEnProyecto modificarDiasPrevistos(EmpleadoEnProyecto empleadoEnProyecto) {
		if (buscarUno(empleadoEnProyecto.getIdEntrada())!= null)
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
			else
				return null;
		
	}

	@Override
	public List<Empleado> buscarEmpleadosDelMismoProyecto(int idProyecto) {
		
		return empleadoEnProyectoRepository.buscarEmpleadosDelMismoProyectoRepoQuery(idProyecto);
	}

	


	

}
