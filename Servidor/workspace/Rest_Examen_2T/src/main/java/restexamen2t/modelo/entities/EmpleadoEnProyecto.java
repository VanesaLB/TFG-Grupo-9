package restexamen2t.modelo.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="empleados_en_proyecto")
public class EmpleadoEnProyecto implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_entrada")
		private int idEntrada;
		@Column (name ="dias_previstos")
		private int diasPrevistos;
		@Column (name ="fecha_incorporacion")
		private Date fechaIncorporacion;
		@ManyToOne
		@JoinColumn(name="id_proyecto")
		private Proyecto proyecto;
		@ManyToOne
		@JoinColumn(name="id_empleado")
		private Empleado empleado;
		
		public EmpleadoEnProyecto() {
			super();
		}

		public EmpleadoEnProyecto(int idEntrada, int diasPrevistos, Date fechaIncorporacion, Proyecto proyecto,
				Empleado empleado) {
			super();
			this.idEntrada = idEntrada;
			this.diasPrevistos = diasPrevistos;
			this.fechaIncorporacion = fechaIncorporacion;
			this.proyecto = proyecto;
			this.empleado = empleado;
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

		public Proyecto getProyecto() {
			return proyecto;
		}

		public void setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
		}

		public Empleado getEmpleado() {
			return empleado;
		}

		public void setEmpleado(Empleado empleado) {
			this.empleado = empleado;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
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
			EmpleadoEnProyecto other = (EmpleadoEnProyecto) obj;
			return idEntrada == other.idEntrada;
		}

		@Override
		public String toString() {
			return "EmpleadoEnProyecto [idEntrada=" + idEntrada + ", diasPrevistos=" + diasPrevistos
					+ ", fechaIncorporacion=" + fechaIncorporacion + ", proyecto=" + proyecto + ", empleado=" + empleado
					+ "]";
		}
		
		
		
}

