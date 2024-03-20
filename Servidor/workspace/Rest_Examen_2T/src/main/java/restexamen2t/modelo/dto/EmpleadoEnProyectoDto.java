package restexamen2t.modelo.dto;


import java.util.Date;
import java.util.Objects;

public class EmpleadoEnProyectoDto {

	private int idEntrada;
	private int diasPrevistos;
	private Date fechaIncorporacion;
	private int idProyecto;
	private int idEmpleado;
	
	public EmpleadoEnProyectoDto() {
		super();
	}

	public EmpleadoEnProyectoDto(int idEntrada, int diasPrevistos, Date fechaIncorporacion, int idProyecto,
			int idEmpleado) {
		super();
		this.idEntrada = idEntrada;
		this.diasPrevistos = diasPrevistos;
		this.fechaIncorporacion = fechaIncorporacion;
		this.idProyecto = idProyecto;
		this.idEmpleado = idEmpleado;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public int getDiasPrevistos() {
		return diasPrevistos;
	}

	public void setDiasPrevistos(int diasPrevistos) {
		this.diasPrevistos = diasPrevistos;
	}

	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEntrada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoEnProyectoDto other = (EmpleadoEnProyectoDto) obj;
		return idEntrada == other.idEntrada;
	}

	@Override
	public String toString() {
		return "EmpleadoEnProyectoDto [idEntrada=" + idEntrada + ", diasPrevistos=" + diasPrevistos
				+ ", fechaIncorporacion=" + fechaIncorporacion + ", idProyecto=" + idProyecto + ", idEmpleado="
				+ idEmpleado + "]";
	}
	
	
	
	
}
