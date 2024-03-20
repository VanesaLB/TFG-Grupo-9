package eventos.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservasController {

	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private ReservaDao redao;

	/**
	 * Este método  maneja solicitudes GET para mostrar las reservas activas de un usuario.
	 * Con el model.addAttribute agrega al modelo las reservas activas del usuario a fecha actual.
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param sesion La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @param aut    La información de autenticación del usuario.
	 * @return La vista "misReservas" con las reservas activas del usuario en el modelo.
	 */
	
	/*@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model, HttpSession sesion, Authentication aut) {
		
		model.addAttribute("reservas", redao.buscarReservasPorUsername(aut.getName()));
		return "misReservas";
	}*/
	
	@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model, HttpSession sesion, Authentication aut) {
		
		//LocalDate l = LocalDate.now();
		model.addAttribute("reservas", redao.buscarReservasActivasPorUsernameRepoQuery(aut.getName(), LocalDate.now()));
		
		return "misReservas";
	}
	
	/**
	 * Este método maneja solicitudes GET para cancelar una reserva.
	 * Con el if comprueba que la reserva se haya eliminado y muestra un mensaje en cada caso .
	 * @param idReserva El identificador único de la reserva que se desea cancelar.
	 * @param model     El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @return Una redirección a la página "misReservas" con un mensaje indicando el resultado de la cancelación.
	 */
	
	@GetMapping("/reservas/cancelar/{idReserva}")
	public String borrarElComercial (@PathVariable int idReserva, Model model) {
		
		if (redao.eliminarReserva(idReserva) == 1)
			model.addAttribute("mensaje", "Reserva cancelada Correctamente");
			else
				if (redao.eliminarReserva(idReserva) == 2)
				model.addAttribute("mensaje", "Reserva no se pudo eliminar porque FK en otra tabla");
				else
					model.addAttribute("mensaje", "Reserva no se pudo eliminar porque NO existe");
		
		return "forward:/reservas/misReservas";
	}                        

	/**
	 * Este método se encarga de que funcionen bien los parseos de fecha
	 * @param binder es un atributo interno
	 */

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


}
