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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Producto producto;
	private int cantidad;
	@Column(name="numero_comensales")
	private int numeroComensales;
//@Temporal(TemporalType.DATE)
	private Date fecha;
	private String servido;
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
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getNumeroComensales() {
		return numeroComensales;
	}
	public void setNumeroComensales(int numeroComensales) {
		this.numeroComensales = numeroComensales;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getServido() {
		return servido;
	}
	public void setServido(String servido) {
		this.servido = servido;
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
	
	
	
	
}
