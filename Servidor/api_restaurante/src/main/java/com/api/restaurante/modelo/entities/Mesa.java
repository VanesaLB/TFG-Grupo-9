package com.api.restaurante.modelo.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Representa una entidad de mesa en la base de datos.
 */
@Entity
@Table(name="mesas")
public class Mesa {

	 /**
     * El identificador único de la mesa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mesa")
    private int idMesa;

    /**
     * El área en la que se encuentra la mesa.
     */
    
    private String area;
	
	public Mesa() {
		super();
	}

	public Mesa(int idMesa, String area) {
		super();
		this.idMesa = idMesa;
		this.area = area;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMesa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return idMesa == other.idMesa;
	}

	@Override
	public String toString() {
		return "Mesa [idMesa=" + idMesa + ", area=" + area + "]";
	}
	
	
	
	
}
