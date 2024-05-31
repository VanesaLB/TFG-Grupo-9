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



/**
 * Representa una entidad de pedido de la base de datos.
 */
@Entity
@Table(name="pedidos")
public class Pedido {

	  /**
     * El identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private int idPedido;

    /**
     * El precio total del pedido.
     */
    @Column(name="precio_total")
    private double precioTotal;

    /**
     * La mesa asociada con el pedido.
     */
    @ManyToOne
    @JoinColumn(name="id_mesa")
    private Mesa mesa;

    /**
     * La cantidad de productos en el pedido.
     */
    @Column(name="cantidad_productos")
    private int cantidadProductos;

    /**
     * La fecha del pedido.
     */
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
