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
	private int idPlato;
	private int cantidad;
	private int numeroComensales;
	private Date fecha;
	private String servido;
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getIdProducto() {
		return idPlato;
	}
	public void setIdProducto(int idProducto) {
		this.idPlato	 = idProducto;
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
	public int getIdPlato() {
		return idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public String getServido() {
		return servido;
	}
	public void setServido(String servido) {
		this.servido = servido;
	}
	
	
	
	
	
}
