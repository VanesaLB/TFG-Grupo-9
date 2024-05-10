package com.api.restaurante.modelo.entities;

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
@Table(name="comandas")
public class Comanda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comanda")
	private int idComanda;
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Producto pedido;
	private String servido;
	
	public Comanda() {
		super();
	}

	public Comanda(int idComanda, Producto producto, Producto pedido, String servido) {
		super();
		this.idComanda = idComanda;
		this.producto = producto;
		this.pedido = pedido;
		this.servido = servido;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getPedido() {
		return pedido;
	}

	public void setPedido(Producto pedido) {
		this.pedido = pedido;
	}

	public String getServido() {
		return servido;
	}

	public void setServido(String servido) {
		this.servido = servido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComanda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comanda other = (Comanda) obj;
		return idComanda == other.idComanda;
	}

	@Override
	public String toString() {
		return "Comanda [idComanda=" + idComanda + ", producto=" + producto + ", pedido=" + pedido + ", servido="
				+ servido + "]";
	}
	
	

}
