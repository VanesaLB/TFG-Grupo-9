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
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;
	private String servido;

	
	public Comanda() {
		super();
	}

	public Comanda(int idComanda, Producto producto, Pedido pedido, String servido,Mesa mesa) {
		super();
		this.idComanda = idComanda;
		this.producto = producto;
		this.pedido = pedido;
		this.servido = servido;
		this.mesa=mesa;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getServido() {
		return servido;
	}

	public void setServido(String servido) {
		this.servido = servido;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa=mesa;
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
				+ servido + ", mesa=" + mesa + "]";
	}
	
	

}
