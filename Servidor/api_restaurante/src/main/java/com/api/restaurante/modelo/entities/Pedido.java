package com.api.restaurante.modelo.entities;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name="pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	@ManyToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;
	@Column(name="cantidad_productos")
	private int cantidadProductos;
	@Column(name="precio_total")
	private double precioTotal;
   @Temporal(TemporalType.DATE)
   @Column(name="fecha")
   private Date fecha;
	
	public Pedido() {
		super();
	}

	public Pedido(int idPedido, Mesa mesa, int cantidadProductos, double precioTotal, Date fecha) {
		super();
		this.idPedido = idPedido;
		this.mesa = mesa;
		this.cantidadProductos = cantidadProductos;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return idPedido == other.idPedido;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", mesa=" + mesa + ", cantidadProductos=" + cantidadProductos
				+ ", precioTotal=" + precioTotal + ", fecha=" + fecha + "]";
	}
	
	
	
	
	
	
}
