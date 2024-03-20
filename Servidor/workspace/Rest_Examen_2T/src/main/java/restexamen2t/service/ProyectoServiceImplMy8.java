package restexamen2t.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restexamen2t.modelo.entities.Proyecto;
import restexamen2t.repository.ProyectoRepository;

@Service
public class ProyectoServiceImplMy8 implements ProyectoService {

	
	@Autowired
	private ProyectoRepository proyectoRepository;
	@Override
	public List<Proyecto> buscarTodosLosProyectos() {
		// TODO Auto-generated method stub
		return proyectoRepository.findAll() ;
	}
	@Override
	public Proyecto buscarProyecto(int idProyecto) {
		// TODO Auto-generated method stub
		return proyectoRepository.findById(idProyecto).orElse(null);
	}
	@Override
	public Proyecto altaProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		if (buscarProyecto(proyecto.getIdProyecto())== null)
		return proyectoRepository.save(proyecto);
		else
			return null;
	}
	@Override
	public int eliminarProyecto(int idProyecto) {
		if (buscarProyecto(idProyecto)!= null)
			try {
				proyectoRepository.deleteById(idProyecto);
				return 1;
			} catch (Exception e) {
				return 0;
			}else
				return -1;
	}
	@Override
	public Proyecto modificarProyecto(Proyecto proyecto) {
		if (buscarProyecto(proyecto.getIdProyecto()) != null)
			return proyectoRepository.save(proyecto);
			else
				return null;
	}
	

	
}
