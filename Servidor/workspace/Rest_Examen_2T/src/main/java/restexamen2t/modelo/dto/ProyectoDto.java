package restexamen2t.modelo.dto;


import java.util.Date;
import java.util.Objects;

public class ProyectoDto {
		
		private int idProyecto;
		private String descripcion;
		private Date fechaInicio;
		private int diasPrevistos;
		private String estado;
		private int idEmpleado; //Pero podría ser director..??
		
		public ProyectoDto() {
			super();
		}

		public ProyectoDto(int idProyecto, String descripcion, Date fechaInicio, int diasPrevistos, String estado,
				int idEmpleado) {
			super();
			this.idProyecto = idProyecto;
			this.descripcion = descripcion;
			this.fechaInicio = fechaInicio;
			this.diasPrevistos = diasPrevistos;
			this.estado = estado;
			this.idEmpleado = idEmpleado;
		}

		public int getIdProyecto() {
			return idProyecto;
		}

		public void setIdProyecto(int idProyecto) {
			this.idProyecto = idProyecto;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Date getFechaInicio() {
			return fechaInicio;
		}

		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		public int getDiasPrevistos() {
			return diasPrevistos;
		}

		public void setDiasPrevistos(int diasPrevistos) {
			this.diasPrevistos = diasPrevistos;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public int getIdEmpleado() {
			return idEmpleado;
		}

		public void setIdEmpleado(int idEmpleado) {
			this.idEmpleado = idEmpleado;
		}

		@Override
		public int hashCode() {
			return Objects.hash(idEmpleado, idProyecto);
		}

		//No tengo claro este método
		//No sé si debo poner todos o solo el proyecto y el empleado como he hecho aquí
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProyectoDto other = (ProyectoDto) obj;
			return idEmpleado == other.idEmpleado && idProyecto == other.idProyecto;
		}

		@Override
		public String toString() {
			return "ProyectoDto [idProyecto=" + idProyecto + ", descripcion=" + descripcion + ", fechaInicio="
					+ fechaInicio + ", diasPrevistos=" + diasPrevistos + ", estado=" + estado + ", idEmpleado="
					+ idEmpleado + "]";
		}
		
		
		
		
}

