package com.api.restaurante.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	
public class ComandaDto {
	private int idProducto;
    private int idMesa;
    private String servido;
    
	public ComandaDto() {
		super();
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public String getServido() {
		return servido;
	}
	public void setServido(String servido) {
		this.servido = servido;
	}
	@Override
	public String toString() {
		return "ComandaDto [idProducto=" + idProducto + ", idMesa=" + idMesa + ", servido=" + servido + "]";
	}
	

}


