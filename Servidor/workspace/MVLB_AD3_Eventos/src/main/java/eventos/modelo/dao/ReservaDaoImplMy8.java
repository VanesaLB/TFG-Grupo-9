package eventos.modelo.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;
import eventos.modelo.repository.ReservaRepository;

@Repository
public class ReservaDaoImplMy8 implements ReservaDao{
	/**
	 * Repositorio de reservas utilizado para acceder y manipular datos relacionados con reservas en la base de datos.
	 * 
	 * @Autowired Indica la inyección de dependencia de un bean ReservaRepository en esta clase.
	 */
	@Autowired
	private ReservaRepository rerepo;

	/**
	 * Este método utiliza el repositorio de reservas (rerepo) para buscar la reserva por su identificador.
	 * Retorna un objeto Reserva si se encuentra, o null si no hay coincidencias.
	 * En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param idReserva El identificador de la reserva a buscar.
	 * @return El objeto Reserva correspondiente al identificador proporcionado, o null si no se encuentra.
	 * 
	 */
	@Override
	public Reserva buscarReservaPorId(int idReserva) {
		// TODO Auto-generated method stub
		return rerepo.findById(idReserva).orElse(null);
	}

	/**
	 *  Este método utiliza el repositorio de reservas (rerepo) para recuperar todas las reservas almacenadas.
	 *  Retorna una lista que puede contener todas las reservas disponibles en la base de datos.
	 *  En caso de excepción durante el acceso a la base de datos, se lanza una excepción de tipo DataAccessException.
	 *  Este método implementa la interfaz con la anotación @Override.
	 * @return Una lista de objetos Reserva que representa todas las reservas disponibles.
	 *  La lista puede estar vacía si no hay reservas almacenadas. 
	 */
	@Override
	public List<Reserva> buscarTodasLasReservas() {
		// TODO Auto-generated method stub
		return rerepo.findAll();
	}

	/**
	 * Este método utiliza el repositorio de reservas (rerepo) para guardar la nueva reserva.
	 * Retorna 1 si la operación es exitosa, y 0 si ocurre una excepción durante el acceso a la base de datos.
	 * 
	 * Este método no se encarga de gestionar las excepciones generadas durante la operación,
	 * simplemente imprime el mensaje de error en la consola y retorna 0.
	 * Este método implementa la interfaz con la anotación @Override.
	 * @param reserva Objeto Reserva que se va a registrar.
	 * @return Un entero que indica el resultado de la operación:
	 *         - 1 si la reserva se registró con éxito.
	 *         - 0 si ocurrió un error durante el registro.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * 
	 * 
	 */
	@Override
	public int altaReserva(Reserva reserva) {
		try {
			rerepo.save(reserva);
		  return 1;  
		}catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	/**
	 *  Este método verifica primero si la reserva con el identificador proporcionado existe.
	 *  Si la reserva existe, intenta eliminarla utilizando el repositorio de reservas (rerepo).
	 *  Retorna el resultado de la operación: 1 si la operación es exitosa, 2 si ocurre una excepción
	 *  durante el acceso a la base de datos, y 0 si la reserva con el identificador dado no fue encontrada.
	 *  Este método no se encarga de gestionar las excepciones generadas durante la operación,
	 *  simplemente imprime el mensaje de error en la consola y retorna 2 en caso de error.
	 *  Elimina una reserva de la base de datos por su identificador único.
	 *  Este método implementa la interfaz con la anotación @Override.
	 * @param idReserva El identificador de la reserva que se desea eliminar.
	 * @return Un entero que indica el resultado de la operación:
	 *         - 1 si la reserva se eliminó con éxito.
	 *         - 2 si ocurrió un error durante la eliminación.
	 *         - 0 si la reserva con el identificador dado no fue encontrada.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	@Override
	public int eliminarReserva(int idReserva) {
		if (rerepo.findById(idReserva).orElse(null) != null) {
			
			try {
			rerepo.deleteById(idReserva);
			  return 1;  
			}catch (Exception e) {
				e.getMessage();
				return 2;
			}
		}
		else
			return 0;
	}

	/**
	 * Este método utiliza un método personalizado del repositorio de reservas (rerepo)
	 * para obtener la cantidad de reservas para un evento específico y un usuario dado. 
	 * Este método no se encarga de gestionar las excepciones generadas durante la operación,
	 * simplemente imprime el mensaje de error en la consola y retorna 0 en caso de error.
	 * Este método implementa la interfaz con la anotación @Override.
	 *       
	 * @param idEvento El identificador del evento.
	 * @param username El nombre de usuario del usuario.
	 * @return Un entero que representa la cantidad de reservas para el evento y usuario especificados.
	 *         Retorna 0 si ocurre un error durante la consulta.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	@Override
	public int cantidadReservasMismoEventoAndUser(int idEvento, String username) {
		try {
			return rerepo.cantidadReservasMismoEventoAndUserRepoQuery(idEvento, username); 
		}catch (Exception e) {
			e.getMessage();
			return 0;
			
		}
	}

	/**
	 * Este método utiliza un método personalizado del repositorio de reservas (rerepo)
	 * para recuperar todas las reservas asociadas a un usuario por su nombre de usuario.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * 
	 * @param username El nombre de usuario del usuario para el cual se buscan las reservas.
	 * @return Una lista de objetos Reserva que representa todas las reservas asociadas al usuario especificado.
	 *         La lista puede estar vacía si no hay reservas asociadas al usuario.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * 
	 */
	
	@Override
	public List<Reserva> buscarReservasPorUsername(String username) {
		// TODO Auto-generated method stub
		return rerepo.buscarReservasPorUsernameRepoQuery(username);
	}

	/**
	 * Este método utiliza un método personalizado del repositorio de reservas (rerepo)
	 * para obtener la cantidad de reservas para un evento específico.
	 * Este método no se encarga de gestionar las excepciones generadas durante la operación,
	 * simplemente imprime el mensaje de error en la consola y retorna 0 en caso de error.
	 * Obtiene la cantidad de reservas para un evento específico.
	 * Este método implementa la interfaz con la anotación @Override.
	 * @param idEvento El identificador del evento.
	 * @return Un entero que representa la cantidad de reservas para el evento especificado.
	 *         Retorna 0 si ocurre un error durante la consulta.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	
	@Override
	public int cantidadReservasMismoEvento(int idEvento) {
		try {
			return rerepo.cantidadReservasMismoEventoRepoQuery(idEvento);
		}catch (Exception e) {
			e.getMessage();
			return 0;
		}
		
	}

	/**
	 * Este método utiliza un método personalizado del repositorio de reservas (rerepo)
	 * para recuperar todas las reservas activas en la fecha proporcionada.
	 * Este método implementa la interfaz con la anotación @Override.
	 * 
	 * @param fechaHoy La fecha actual para la cual se buscan las reservas activas.
	 * @return Una lista de objetos Reserva que representa todas las reservas activas en la fecha especificada.
	 *         La lista puede estar vacía si no hay reservas activas en la fecha proporcionada.
	 * 
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * 
	 */
	
	@Override
	public List<Reserva> buscarReservasActivas(LocalDate fechaHoy) {
		// TODO Auto-generated method stub
		return rerepo.buscarReservasActivasRepoQuery(fechaHoy);
	}
	
	/**
	 * Este método utiliza un método personalizado del repositorio de reservas (rerepo)
	 * para recuperar todas las reservas activas asociadas a un usuario por su nombre de usuario
	 * en la fecha proporcionada.Este método implementa la interfaz con la anotación @Override.
	 * 
	 * 
	 * @param username El nombre de usuario del usuario para el cual se buscan las reservas activas.
	 * @param fechaHoy La fecha actual para la cual se buscan las reservas activas.
	 * @return Una lista de objetos Reserva que representa todas las reservas activas asociadas al usuario
	 *         en la fecha especificada. La lista puede estar vacía si no hay reservas activas.
	 * 
	 */

	@Override
	public List<Reserva> buscarReservasActivasPorUsernameRepoQuery(String username, LocalDate fechaHoy) {
		// TODO Auto-generated method stub
		return rerepo.buscarReservasActivasPorUsernameRepoQuery(username, fechaHoy);
	}

}
