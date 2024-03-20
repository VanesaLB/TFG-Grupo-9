package restexamen2t.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restexamen2t.modelo.dto.ProyectoDto;
import restexamen2t.modelo.entities.Proyecto;
import restexamen2t.service.EmpleadoService;
import restexamen2t.service.ProyectoService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto")
public class ProyectoRestController {

	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public List<Proyecto>buscarTodosLosProyectos() {
		return proyectoService.buscarTodosLosProyectos();
	}
	
	@GetMapping("/buscarUno/{idProyecto}")
	public Proyecto buscarElProyecto(@PathVariable int idProyecto) {
		
		return proyectoService.buscarProyecto(idProyecto);
	}
	
	@PostMapping("/alta")
	public Proyecto altaDelProyecto(@RequestBody ProyectoDto proyectoDto) {
		Proyecto proyecto = new Proyecto();
		/*	producto.setColor(productoDto.getColor());
		producto.setMarca(productoDto.getMarca());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setPrecioUnitario(productoDto.getPrecioUnitario());
		producto.setFamilia(familiaService.buscarUno(productoDto.getCodigoFamilia()));
*/		
		
		modelMapper.map(proyectoDto,proyecto);
		proyecto.setEmpleado(empleadoService.buscarEmpleado(proyectoDto.getIdEmpleado()));
		return proyectoService.altaProyecto(proyecto);
	}
	
	@DeleteMapping("/eliminar/{idProyecto}")
	public String eliminarElProyecto(@PathVariable int idProyecto) {
	
		switch (proyectoService.eliminarProyecto(idProyecto)) {
		
		case 1: return "Proyecto eliminado correctamente";
		case 0: return "No se pudo eliminar el proyecto porque FK en otra tabla";
		case -1: return "No se pudo eliminar el proyecto porque NO existe";
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + proyectoService.eliminarProyecto(idProyecto));
		}
		
	}
	
	@PutMapping("/modificar")
	public Proyecto modificarElProyecto(@RequestBody Proyecto proyecto) {
		return proyectoService.modificarProyecto(proyecto);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
