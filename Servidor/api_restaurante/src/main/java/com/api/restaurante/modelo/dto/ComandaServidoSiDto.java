package com.api.restaurante.modelo.dto;

import java.util.Objects;

public class ComandaServidoSiDto {

	private int idComanda;
	private String servido;
	
	
	
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	public String getServido() {
		return servido;
	}
	public void setServido(String servido) {
		this.servido = servido;
	}
	public ComandaServidoSiDto() {
		super();
	}
	public ComandaServidoSiDto(int idComanda, String servido) {
		super();
		this.idComanda = idComanda;
		this.servido = servido;
	}
	@Override
	public String toString() {
		return "ComandaServidoSiDto [idComanda=" + idComanda + ", servido=" + servido + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idComanda, servido);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComandaServidoSiDto other = (ComandaServidoSiDto) obj;
		return idComanda == other.idComanda && Objects.equals(servido, other.servido);
	}
	
	
}
