package eventos.modelo.dao;

import java.time.LocalDate;
import java.util.List;

import eventos.modelo.entitis.Reserva;

public interface ReservaDao {
	
	Reserva buscarReservaPorId (int idReserva);
	List<Reserva> buscarTodasLasReservas();
	int altaReserva (Reserva reserva);
	int eliminarReserva (int idReserva);
	int cantidadReservasMismoEventoAndUser(int idEvento, String username);
	int cantidadReservasMismoEvento(int idEvento);
	List<Reserva> buscarReservasPorUsername (String username);
	List<Reserva> buscarReservasActivas(LocalDate fechaHoy);
	List<Reserva> buscarReservasActivasPorUsernameRepoQuery(String username, LocalDate fechaHoy );

}
