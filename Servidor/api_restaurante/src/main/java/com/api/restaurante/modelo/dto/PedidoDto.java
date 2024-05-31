package com.api.restaurante.modelo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa un pedido.
 * Contiene los datos necesarios para transferir información sobre un pedido.
 */

@NoArgsConstructor
@AllArgsConstructor	
@Data
public class PedidoDto {

	/**
     * El identificador de la mesa asociada con el pedido.
     */
    private int idMesa;

    /**
     * La cantidad de productos en el pedido.
     */
    private int cantidadProductos;

    /**
     * El precio total del pedido.
     */
    private double precioTotal;

    /**
     * La fecha del pedido.
     */
    private Date fecha;
	
	public int getIdMesa() {
		return idMesa;
	}
	
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
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
	
	
	
	
	
	
	
}
