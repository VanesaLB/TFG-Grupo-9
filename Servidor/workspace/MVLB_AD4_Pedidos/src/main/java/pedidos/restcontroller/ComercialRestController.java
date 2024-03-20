package pedidos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pedidos.modelo.entities.Comercial;
import pedidos.modelo.entities.Pedido;
import pedidos.service.ComercialService;
import pedidos.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comercial")
public class ComercialRestController {
	
	@Autowired
	private ComercialService comercialService;
	
	@Autowired
	private PedidoService pedidoService;
	
	//Devolvemos todos los comerciales
	@GetMapping("/")
	public List<Comercial> todos(){
		return comercialService.buscarTodosLosComerciales();
	}
	
	//Devolver los datos del comercial cuyo id coincida
	/*Hay que poner @PathVariable("id") porque el nombre de la ruta no coincide con el int idComercial
	 * Si fuera el mismo no haría falta ponerlo entre paréntesis y comillas*/
	@GetMapping("/uno/{id}")
	public Comercial uno(@PathVariable("id") int idComercial){
		return comercialService.buscarUno(idComercial);
	}
	
	//Dar de alta el comercial
	//Lo ponemos sin código
	@PostMapping("/alta")
	public Comercial alta(@RequestBody Comercial comercial) {
		return comercialService.insertarComercial(comercial);
	}
	
	//Eliminar de la bbdd el comercial cuyo id coincida
	@DeleteMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idComercial) {
		if (comercialService.eliminar(idComercial))
			return "Comercial eliminado correctamente";
		else
			return "Comercial no se ha podido eliminar";
	}
	
	//Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.
	@GetMapping("/bycliente/{id}")
	public List<Comercial> porCliente(@PathVariable("id") int idCliente){
		return comercialService.buscarComercialesPorPedidosDeCliente(idCliente);
	}
	
	//Devolver la lista de comerciales que han atendido algún pedido
	@GetMapping("/conpedidos")
	public List<Comercial> conPedidos(){
		return comercialService.buscarComercialesConPedidos();
	}
	
	
	//Devolver la lista de pedidos gestionados por el comercial que coincida con ese id
	@GetMapping("/pedidos/{id}")
	public List<Pedido> pedidosPorComercial(@PathVariable("id") int idComercial){
		return pedidoService.buscaPedidosPorComercial(idComercial);
	}
	

}
