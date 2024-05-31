package com.api.restaurante.modelo.dto;

import java.util.Objects;
/**
 * DTO (Data Transfer Object) que representa una comanda con el estado de servicio.
 * Contiene los datos necesarios para transferir información sobre una comanda
 * y su estado de servicio.
 */

public class ComandaServidoSiDto {

	/**
     * El identificador único de la comanda.
     */
    private int idComanda;

    /**
     * Indica si la comanda ha sido servida.
     */
    private String servido;
	
	public ComandaServidoSiDto() {
		super();
	}

	public ComandaServidoSiDto(int idComanda, String servido) {
		super();
		this.idComanda = idComanda;
		this.servido = servido;
	}

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

	@Override
	public String toString() {
		return "ComandaServidoSiDto [idComanda=" + idComanda + ", servido=" + servido + "]";
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
		ComandaServidoSiDto other = (ComandaServidoSiDto) obj;
		return idComanda == other.idComanda;
	}
	
	

	
	
}
