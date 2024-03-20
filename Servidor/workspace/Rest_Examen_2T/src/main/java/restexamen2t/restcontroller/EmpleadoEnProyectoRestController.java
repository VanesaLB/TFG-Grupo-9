package restexamen2t.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restexamen2t.modelo.dto.EmpleadoEnProyectoDto;
import restexamen2t.modelo.dto.EmpleadoEnProyectoDtoDiasPrevistos;
import restexamen2t.modelo.entities.Empleado;
import restexamen2t.modelo.entities.EmpleadoEnProyecto;
import restexamen2t.service.EmpleadoEnProyectoService;
import restexamen2t.service.EmpleadoService;
import restexamen2t.service.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empleadoEnProyecto")
public class EmpleadoEnProyectoRestController {

	
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public List<EmpleadoEnProyecto> todosLosEmpleadosEnProyecto () {
		return empleadoEnProyectoService.buscarTodosLosEmpleadosEnProyecto();
	}
	
	@GetMapping("/buscarUno/{idEmpleadoEnProyecto}")
	public EmpleadoEnProyecto buscarUnEmpleadoEnProyecto(@PathVariable int idEmpleadoEnProyecto)  {
		
		return empleadoEnProyectoService.buscarUno(idEmpleadoEnProyecto);
	}
	
	//debería funcionar pero pasa una historia de jerarquías rarísimas. Se puede hacer de la forma larga
	//pero la verdad que que horror, si en vez de tener 2 fk tuviera 7 yo me pego un tiro
	@PostMapping("/alta")
	public EmpleadoEnProyecto darDeAlta(@RequestBody EmpleadoEnProyectoDto empleadoEnProyectoDto) {
		
		EmpleadoEnProyecto empleadoEnProyecto = new EmpleadoEnProyecto();
		
		modelMapper.map(empleadoEnProyectoDto, empleadoEnProyecto);
		//empleadoEnProyecto.setEmpleado(empleadoService.buscarEmpleado(empleadoEnProyectoDto.getIdEmpleado()));
		//empleadoEnProyecto.setProyecto(proyectoService.buscarProyecto(empleadoEnProyectoDto.getIdProyecto()));
		
		return empleadoEnProyectoService.alta(empleadoEnProyecto);
	
	}
	
	@PutMapping("/modificar")
	public EmpleadoEnProyecto modificarEmpleadoEnProyector(@RequestBody EmpleadoEnProyecto empleadoEnProyecto) {
		return empleadoEnProyectoService.modificar(empleadoEnProyecto);
        
        }
	//Este método recibe un JSON con idEntrada y diasPrevistos, y devuelve el objeto completo modificado
	@PutMapping("/modificarDiasPrevistos")
	public EmpleadoEnProyecto modificarDiasPrevistoEnEmpleadoEnProyecto(@RequestBody EmpleadoEnProyectoDtoDiasPrevistos 
			empleadoEnProyectoDtoDiasPrevistos
			) {
		EmpleadoEnProyecto empleadoEnProyecto = empleadoEnProyectoService.buscarUno(empleadoEnProyectoDtoDiasPrevistos.getIdEntrada());
		empleadoEnProyecto.setDiasPrevistos(empleadoEnProyectoDtoDiasPrevistos.getDiasPrevistos());
		return empleadoEnProyectoService.modificar(empleadoEnProyecto);
		
	}
	
	@GetMapping("/empleadosMismoProyecto/{idProyecto}")
	public List<Empleado> buscarEmpleamosDelMismoProyecto (@PathVariable int idProyecto) {
		return empleadoEnProyectoService.buscarEmpleadosDelMismoProyecto(idProyecto);
	}
}























