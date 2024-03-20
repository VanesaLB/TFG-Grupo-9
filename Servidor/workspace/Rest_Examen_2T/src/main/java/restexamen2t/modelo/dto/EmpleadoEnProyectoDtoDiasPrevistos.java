package restexamen2t.modelo.dto;

import java.util.Objects;

public class EmpleadoEnProyectoDtoDiasPrevistos {

	private int idEntrada;
	private int diasPrevistos;
	
	public EmpleadoEnProyectoDtoDiasPrevistos() {
		super();
	}

	public EmpleadoEnProyectoDtoDiasPrevistos(int idEntrada, int diasPrevistos) {
		super();
		this.idEntrada = idEntrada;
		this.diasPrevistos = diasPrevistos;
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
		EmpleadoEnProyectoDtoDiasPrevistos other = (EmpleadoEnProyectoDtoDiasPrevistos) obj;
		return idEntrada == other.idEntrada;
	}

	@Override
	public String toString() {
		return "EmpleadoEnProyectoDtoDiasPrevistos [idEntrada=" + idEntrada + ", diasPrevistos=" + diasPrevistos + "]";
	}
	
	
	
	
}
