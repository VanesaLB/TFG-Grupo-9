package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.PerfilDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private PerfilDao pdao;
	/*@Autowired
 	private PasswordEncoder passwordEncoder;*/
	
	/**
	 * Este método  maneja solicitudes GET para mostrar la página principal ("home") con eventos filtrados o todos los eventos.
	 * Con el if (eventosFiltrados != null && !eventosFiltrados.isEmpty()) comprueba que haya eventrosFiltrados , es decir que la lista de eventos 
	 * no este vacía y cpn model.addtrtribute los añade al modelo.
	 * Con el  if (esVacioOpcion.equalsIgnoreCase("tablaVacia")comprueba si la tabla esta vacia y muestra un mensaje .
	 * Con el model.addAttribute si la tabla no esta vacia y hay eventos filtrados los muestra .
	 * @param model              El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param eventosFiltrados   Lista de eventos filtrados por tipo (puede ser nula o vacía).
	 * @param esVacioOpcion      Cadena indicando si la tabla está vacía debido a la filtración.
	 * @return La vista "home" con la lista de eventos en el modelo, según los eventos filtrados o todos los eventos.
	 */
	
	@GetMapping("/")
	public String mostrarHome(Model model, @ModelAttribute("eventosFiltradosPorTipo") ArrayList<Evento> eventosFiltrados, @ModelAttribute("esVacio") String esVacioOpcion) {
		
		if (eventosFiltrados != null && !eventosFiltrados.isEmpty()) {
			model.addAttribute("eventos", eventosFiltrados);
			return "home";
		}
		
		if (esVacioOpcion.equalsIgnoreCase("tablaVacia")) {
			model.addAttribute("mensaje", "no hay eventos disponibles de este tipo");
			return "home";
		}
		model.addAttribute("eventos", edao.buscarTodosLosEventos());
		
		return "home";
	}
	
	/**
	 * Este método maneja solicitudes GET para mostrar el formulario de inicio de sesión.
	 *
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param sesion La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @param aut    La información de autenticación del usuario.
	 * @return La vista "formLogin" para el formulario de inicio de sesión.
	 */

	@GetMapping("/login")
	public String procesarLogin(Model model, HttpSession sesion, Authentication aut) {
		
		System.out.println("pasando por login getmapping");
		return "formLogin";
	}
	
	/**
	 * Este método maneja la solicitud GET que se ejecuta despúes de un login correcto.
	 *
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param misesion La sesión HTTP, que podría usarse para obtener información de la sesión (Lo utilizamos para
	 * el nombre del usuario).
	 * @param aut La información de autenticación del usuario.
	 * @return Una redirección a la página principal ("/").
	 */
	
	@GetMapping("/inicioSesion")
	public String procesarFormLogin( HttpSession misesion, Model model, Authentication aut) {
		
		Usuario usuario = udao.buscarPorUsername(aut.getName());
		
		if (usuario != null) {
			usuario.setPassword(null);
			misesion.setAttribute("usuario", usuario);
			Usuario miUsuario = (Usuario)misesion.getAttribute("usuario");
			model.addAttribute("nombreUsuario", miUsuario.getNombre() );
			//udao.buscarPorUsername((aut.getName())).getNombre()
		}
		return "forward:/";
	}
	
	/**
	 * Este método maneja la solicitud GET que se ejecuta despúes de un login incorrecto.
	 *
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @return Una redirección a "/login" con un mensaje de error.
	 */
	
	@GetMapping("/falloInicioSesion")
	public String procesarFalloLogin( Model model) {
		model.addAttribute("mensaje", "Usuario o password incorrectos, revise sus credenciales.");
		
		return "forward:/login";
	}
		
	/**
	 * Este método maneja solicitudes GET para mostrar el formulario de registro.
	 *
	 * @param model  El modelo utilizado para agregar atributos que se enviarán a la vista.
	 * @param sesion La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @param aut    La información de autenticación del usuario.
	 * @return La vista "registroFormulario" con el formulario de registro.
	 */
	
	@GetMapping("/registro")
	public String procesarFormRegistro(Model model) {
		
		System.out.println("hola");
		model.addAttribute("usuario", new Usuario());
		System.out.println("creado");
		return "registro";
	}
	
	/**
	 * Este método maneja solicitudes POST para procesar el registro de un nuevo usuario.
	 * Con .addPerfil agrega un perfil al usuario que en este caso siempre es el 3 por que es un cliente.
	 * Con el if comprueba si el usuario se ha dado de alta y muestra los mensajes correspondientes .
	 * @param usuario El objeto Usuario que contiene la información del usuario desde el formulario.
	 * @param ratt    Atributos de redirección para pasar mensajes y datos.
	 * @param sesion  La sesión HTTP, que podría usarse para obtener información de la sesión (actualmente no utilizado).
	 * @param aut     La información de autenticación del usuario.
	 * @return Una redirección a la página principal ("/") .
	 */
	
	@PostMapping("/registro")
	public String procesarRegistroUsuario(Usuario usuario, RedirectAttributes ratt,HttpSession sesion, Authentication aut) {
		System.out.println(usuario);
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
		usuario.addPerfil(pdao.buscarPerfilPorId(3));
		//usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setPassword("{noop}"+ usuario.getPassword());
		if (udao.altaUsuario(usuario) == 1) {
			
			ratt.addFlashAttribute("mensaje", "Cliente dado de alta correctamente");
		}
		else
			ratt.addFlashAttribute("mensaje", "Cliente no se pudo crear");
		
		return "redirect:/";
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
	
	/*
	 
	 * Este método maneja solicitudes POST para procesar la autenticación del usuario.
	 * Con Usuario usuario busca al usuario en la BBDD por su nombre y contraseña .
	 * Con if (usuario != null) comprueba que es usuario exista , si existe realiza la autentificación y redirige a la pagina principal.
	 * Si la autentificación falla muestra un mensaje y redirige al login de nuevo .
	 * @param username El nombre de usuario proporcionado en el formulario.
	 * @param password La contraseña proporcionada en el formulario.
	 * @param sesion   La sesión HTTP, que podría usarse para obtener información de la sesión .
	 * @param ratt     Atributos de redirección para pasar mensajes y datos.
	 * @param aut      La información de autenticación del usuario.
	 * @return Una redirección a la página principal ("/") si la autenticación es exitosa; de lo contrario, redirige de nuevo a "/login" con un mensaje de error.
	 
	@PostMapping("/login")
	public String procesarLoginV2(@RequestParam("username") String username,@RequestParam("password") String password, 
			HttpSession sesion, RedirectAttributes ratt, Authentication aut) {
		System.out.println("el fallo loginjuan");
		System.out.println("hola soy postmaping");
		Usuario usuario = udao.buscarUsuarioPorUsernamePassword(username, password);
		System.out.println(usuario);
		System.out.println("el fallo loginjuan");
		if (usuario != null) {
			usuario.setPassword(null);
			sesion.setAttribute("usuario", usuario);
			
			ratt.addFlashAttribute("nombreUsuario", udao.buscarPorUsername((aut.getName())).getNombre());
			
			return "redirect:/";
		}else {
		ratt.addFlashAttribute("mensaje", "Usuario o password incorrectos, revise sus credenciales.");
		System.out.println("el fallo loginjuan");
		return "redirect:/login";}
		
	}
	
	 */
	

}
