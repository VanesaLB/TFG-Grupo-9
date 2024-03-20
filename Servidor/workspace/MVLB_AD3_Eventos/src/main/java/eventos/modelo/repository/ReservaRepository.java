package eventos.modelo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eventos.modelo.entitis.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

	
	@Query("SELECT SUM(r.cantidad) FROM Reserva r WHERE r.evento.idEvento = :idEvento AND r.usuario.username = :username")
	public int cantidadReservasMismoEventoAndUserRepoQuery(int idEvento, String username);
	
	@Query ("select r from Reserva r where r.usuario.username = ?1")
	public List<Reserva> buscarReservasPorUsernameRepoQuery(String username);
	
	@Query ("select r from Reserva r where r.evento.fechaInicio > :fechaHoy")
	public List<Reserva> buscarReservasActivasRepoQuery(LocalDate fechaHoy);
	
	@Query ("select r from Reserva r where r.usuario.username = :username AND r.evento.fechaInicio > :fechaHoy" )
	public List<Reserva> buscarReservasActivasPorUsernameRepoQuery(String username, LocalDate fechaHoy );
	
	//Para la cantidad de reservas totales de un evento
	@Query("SELECT SUM(r.cantidad) FROM Reserva r WHERE r.evento.idEvento = :idEvento")
	public int cantidadReservasMismoEventoRepoQuery(int idEvento);

}
