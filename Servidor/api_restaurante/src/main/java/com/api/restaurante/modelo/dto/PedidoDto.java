package com.api.restaurante.modelo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor	
@Data
public class PedidoDto {

	private int idMesa;
	private int cantidadProductos;
	private double precioTotal;
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
