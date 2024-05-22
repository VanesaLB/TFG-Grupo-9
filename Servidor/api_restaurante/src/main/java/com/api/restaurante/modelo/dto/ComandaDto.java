package com.api.restaurante.modelo.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor	
public class ComandaDto {
	private int idProducto;
	private int idPedido;
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

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
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
		return "ComandaDto [idProducto=" + idProducto + ", idPedido=" + idPedido + ", idMesa=" + idMesa + ", servido="
				+ servido + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMesa, idPedido, idProducto, servido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComandaDto other = (ComandaDto) obj;
		return idMesa == other.idMesa && idPedido == other.idPedido && idProducto == other.idProducto
				&& Objects.equals(servido, other.servido);
	}
	
	
	

}


