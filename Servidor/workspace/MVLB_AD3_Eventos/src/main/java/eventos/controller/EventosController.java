package eventos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventosController {

	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private ReservaDao redao;
	
	/**
	 * Método que maneja solicitudes GET para mostrar eventos destacados.
	 * Con model addAttribute agrega la lista de eventos destacados al modelo.
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param sesion La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @return La vista "home" con la lista de eventos destacados en el modelo.
	 */
	
	@GetMapping("/eventos/destacados")
	public String mostrarEventosDestacados(Model model, HttpSession sesion) {
		
		model.addAttribute("eventos", edao.buscarEventosPorDestacado());
		return "home";
	}
	
	/**
	 * Método que maneja solicitudes GET para mostrar eventos activos.
	 *  Con model addAttribute agrega la lista de eventos activos al modelo.
	 * @param model El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @return La vista "home" con la lista de eventos activos en el modelo.
	 */
	
	@GetMapping("/eventos/activos")
	public String mostrarEventosActivos(Model model) {
		
		model.addAttribute("eventos", edao.buscarEventosPorActivo());
		return "home";
	}
	
	/**
	 * Método controlador que procesa la solicitud de filtrar eventos por tipo.
	 * Con request.getParameter("tipoEvento") obtenemos el parámetro tipoEvento del formulario.
	 * Con Integer.parseInt parseamos el tipoEvento que como lo obtenemos del post es un string a entero .
	 * Con Switch maneja diferentes casos según el tipo de Evento .Caso 0 no se selecciono ninguna opción . Casos 1,2,3 y 4 se
	 * selecciona alguna de estas opciones y verifica que la lista de eventos este vacía .
	 * Con addFlashAttribute agregaremos el mensaje "no hay eventos de este tipo" en caso de que no haya ninguno de este tipo , además
	 * agrega la lista vacía de eventos filtrados a los atributos de redirección y agrega un indicador de que la tabla de eventos está vacía.
	 * Con (ratt.addFlashAttribute("eventosFiltradosPorTipo", edao.buscarEventosPorTipo(tipoEventoParseado)))estamos agregando 
	 * los eventosFiltradosPorTipo como atibuto flash para redireccionarlos y mostrarlos.
	 * Este método se invoca cuando se envía el formulario para filtrar eventos por un tipo específico.
	 * Se encarga de obtener el tipo de evento seleccionado, buscar eventos correspondientes en la base de datos
	 * y redirigir a la página principal con los resultados o un mensaje indicando que no hay eventos del tipo seleccionado 
	 * si no hay ninguno en la BBDD y proporcionar la tabla de eventos vacía .
	 *
	 * @param request    Objeto HttpServletRequest que contiene los parámetros de la solicitud HTTP.
	 * @param ratt       Objeto RedirectAttributes utilizado para pasar atributos entre solicitudes.
	 *
	 * @return  "redirect:/"  nos redirige al home .
	 */
	
	@PostMapping("/eventos/filtrarTipo")
	public String mostrarEventosPorTipo(HttpServletRequest request, RedirectAttributes ratt) {
		
		String tipoEvento = request.getParameter("tipoEvento");
		int tipoEventoParseado = Integer.parseInt(tipoEvento);
		
		List<Evento> eaux = edao.buscarEventosPorTipo(tipoEventoParseado);
		List<Evento> eaux2 = new ArrayList<Evento>();
		
		switch (tipoEventoParseado) {
		
		case 0:
			ratt.addFlashAttribute("mensaje", "Debes seleccionar una opción válida");
		    return "redirect:/";
		case 1,2,3,4:
			if (eaux.isEmpty()) {
				ratt.addFlashAttribute("mensaje", "No tienes eventos de este tipo en tu base de datos");
				ratt.addFlashAttribute("eventosFiltradosPorTipo", eaux);
				ratt.addFlashAttribute("esVacio", "tablaVacia");
			}else{  
				ratt.addFlashAttribute("mensaje", "Filtrado con éxito");
				ratt.addFlashAttribute("eventosFiltradosPorTipo", edao.buscarEventosPorTipo(tipoEventoParseado)); }
		return "redirect:/";
			  
		default: {
			ratt.addFlashAttribute("mensaje", "No existe este tipo de evento"); 
			return "redirect:/"; }
        }	 
	}
	
	/**
	 * Método que maneja solicitudes GET para mostrar detalles de un evento y el formulario de reserva.
	 * Con int entradasRestantes calcula la cantidad de entradas restantes restandole al aforo máximo del evento la 
	 * cantidad de reservas hechas para ese evento.
	 * Con model.addAttribute("evento", edao.buscarEventoPorId(idEvento))agrega el evento al modelo.
	 * Con model addAttribute agrega la cantidad de entradas restantes al modelo.
	 * Con el if agrega el nombre del usuario al modelo considerando si esta autenticado o no. Si no esta autenticado 
	 * sale invitado.
	 * @param idEvento El identificador único del evento cuyos detalles se mostrarán.
	 * @param model    El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param sesion   La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @param aut      La información de autenticación del usuario.
	 * @return La vista "detallesEventoAndFormReserva" con los detalles del evento y el formulario de reserva en el modelo.
	 */
	
	@GetMapping("/eventos/detalle/{idEvento}")
	public String mostrarDetallesAndFormReserva(@PathVariable int idEvento, Model model, HttpSession sesion, Authentication aut) {
		model.addAttribute("evento", edao.buscarEventoPorId(idEvento));
		
		// Calcula la cantidad de entradas restantes
	    int entradasRestantes = edao.buscarEventoPorId(idEvento).getAforoMaximo() - redao.cantidadReservasMismoEvento(idEvento);
	    model.addAttribute("entradasRestantes", entradasRestantes);
	    
	    //System.out.println(aut.getName());
	  	model.addAttribute("evento", edao.buscarEventoPorId(idEvento));
	  		
		if (aut != null && aut.getName() != null ) {
			model.addAttribute("nombreUsuario", udao.buscarPorUsername((aut.getName())).getNombre());
			} else {
			model.addAttribute("nombreUsuario", "Invitado");
			}
		
		return "detallesEventoAndFormReserva";
	}
	
	/**
	 * Método que maneja solicitudes POST para realizar una reserva de entradas para un evento específico.
	 * Con aforoMaximoEvento y entradasRestantes obtiene información sobre el aforo máximo y las entradas que quedan.
	 * Con restaReservasRestantesyActuales obtiene el resultado de restar a las entradasRestantes que son las que quedaban, las entradas que 
	 * actualmente esta reservando el usuario como cantidad.
	 * Con el if (restaReservasRestantesyActuales < 0 ), comprueba que no queden entradas, si no quedan entonces saldra un mensaje "La reserva no se dio de alta porque se supera el Aforo del Evento".
	 * Con int reservasTotalesEventoPorPersona comprueba cuantas reservas tiene un usuario para un evento.
	 * Con int sumaReservas suma las reservas que ya tenia el usuario para ese evento mas las que esta haciendo en ese mismo momento.
	 * Con este if (sumaReservas > 10) verifica si la suma de Reservas del usuario no excedan el limite de 10 reservas, si es así proporciona 
	 * este mensaje "La reserva no se dio de alta porque no puedes tener mas de 10 reservas en un mismo tipo de evento".
	 * Con edao.buscarEventoPorId(idEvento).getFechaInicio(); se obtiene la fecha de inicio del evento y la 
	 * almacenamos en la variable Date fechaInicioEvento.
	 * Con new Date(); creamos un objeto nuevo Date fechaActual.
	 * En caso de que la fecha de inicio del evento haya pasado, se agrega un mensaje a los atributos flash
	 * que se utilizarán en la redirección. Finalmente, se realiza una redirección a la página principal ("/") después de agregar el mensaje a los atributos flash.
	 * Con el else si no se cumple ninguna de las condiciones anteriores crea una nueva reserva y la añade a la BBDD.
	 * Con este if (redao.altaReserva(reservaAux) == 1) verifica  el resultado de la operación de alta de reserva y redirige con un mensaje.
	 * @param idEvento   El identificador único del evento para el cual se desea realizar la reserva.
	 * @param model      El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param cantidad   La cantidad de entradas que se desean reservar.
	 * @param sesion     La sesión HTTP, que podría usarse para obtener información de la sesión .
	 * @param aut        La información de autenticación del usuario.
	 * @param ratt       Atributos de redirección para pasar mensajes y datos.
	 * @return Una redirección a la página principal con un mensaje indicando el resultado de la reserva.
	 */
	
	@PostMapping("/eventos/reservar/{idEvento}")
	public String reservarEvento(@PathVariable int idEvento, Model model, @RequestParam("cantidad") int cantidad,
			HttpSession sesion, Authentication aut, RedirectAttributes ratt) {
		

		if (cantidad == 0) {
			ratt.addFlashAttribute("mensaje", "La reserva no se dio de alta porque no puedes seleccionar ninguna cantidad");
			return "redirect:/";
		}
		
		int aforoMaximoEvento = edao.buscarEventoPorId(idEvento).getAforoMaximo();
		int entradasRestantes = aforoMaximoEvento - redao.cantidadReservasMismoEvento(idEvento);
		int restaReservasRestantesyActuales = entradasRestantes - cantidad;
		
		if (restaReservasRestantesyActuales < 0 ) {
			ratt.addFlashAttribute("mensaje", "La reserva no se dio de alta porque se supera el Aforo del Evento");
			return "redirect:/";
			}
		
		int reservasTotalesEventoPorPersona = redao.cantidadReservasMismoEventoAndUser(idEvento, aut.getName());
		int sumaReservas = reservasTotalesEventoPorPersona + cantidad;
		
		if (sumaReservas > 10) {
			
			ratt.addFlashAttribute("mensaje", "La reserva no se dio de alta porque no puedes tener mas de 10 reservas en un mismo tipo de evento");
			return "redirect:/";
		}
		
		Date fechaInicioEvento = edao.buscarEventoPorId(idEvento).getFechaInicio();
		Date fechaActual = new Date(); // Obtener la fecha actual como un objeto Date

		if (fechaInicioEvento.before(fechaActual)) {
		    ratt.addFlashAttribute("mensaje", "La reserva no se dio de alta porque el evento ya pasó");
		    return "redirect:/";
		}
		
		else {
			Reserva reservaAux = new Reserva();
			Evento eventoAux = new Evento();
			eventoAux = edao.buscarEventoPorId(idEvento);
			Usuario usuarioAux = new Usuario();
			usuarioAux = udao.buscarPorUsername(aut.getName());
			reservaAux.setEvento(eventoAux);
			reservaAux.setUsuario(usuarioAux);
			reservaAux.setPrecioVenta(eventoAux.getPrecio());
			reservaAux.setObservaciones(eventoAux.getDescripcion());
			reservaAux.setCantidad(cantidad);
			
			if (redao.altaReserva(reservaAux) == 1)
				ratt.addFlashAttribute("mensaje", "Reserva dada de alta");
			else {
				ratt.addFlashAttribute("mensaje", "Reserva no pudo darse de alta");
			}
			return "redirect:/";
		}
		
	}

}
