package restexamen2t.restcontroller;

import java.util.List;

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

import restexamen2t.modelo.entities.Empleado;
import restexamen2t.service.EmpleadoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empleado")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/")
	 public List<Empleado> buscarTodosLosEmpleados() {
		 return empleadoService.buscarTodosLosEmpleados();
	 }
	
	@GetMapping("/buscarUno/{idEmpleado}")
	 public Empleado buscarUnEmpleado(@PathVariable int idEmpleado) {
		 return empleadoService.buscarEmpleado(idEmpleado);
	 }
	
	@PostMapping("/alta")
	public Empleado altaDelEmpleado(@RequestBody Empleado empleado) {
		
		return empleadoService.altaEmpleado(empleado);
		}
	
	@DeleteMapping("/eliminar/{idEmpleado}") 
	public String eliminarElEmpleado(@PathVariable int idEmpleado) {
		
		switch (empleadoService.eliminarEmpleado(idEmpleado)) {
	
		case 1: return "Empleado eliminado correctamente";
		case 0: return "No se pudo eliminar el empleado porque FK en otra tabla";
		case -1: return "No se pudo eliminar el empleado porque NO existe";
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + empleadoService.eliminarEmpleado(idEmpleado));
	
	
	
	}
	
	
	}
	
	@PutMapping("/modificar")
	public Empleado modificarElEmpleado(@RequestBody Empleado empleado) {
		return empleadoService.modificar(empleado);
	}
	
}
	
	
	
	
	
	
	
	
	

