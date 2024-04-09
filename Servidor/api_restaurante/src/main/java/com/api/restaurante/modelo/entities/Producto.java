package com.api.restaurante.modelo.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_plato")
	private int idPlato;
	private String ingredientes;
	private String descripcion;
	private String tipo;
	private String vegano;
	private String gluten;
	private double precio;
	public int getIdPlato() {
		return idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getVegano() {
		return vegano;
	}
	public void setVegano(String vegano) {
		this.vegano = vegano;
	}
	public String getGluten() {
		return gluten;
	}
	public void setGluten(String gluten) {
		this.gluten = gluten;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [idPlato=" + idPlato + ", ingredientes=" + ingredientes + ", descripcion=" + descripcion
				+ ", tipo=" + tipo + ", vegano=" + vegano + ", gluten=" + gluten + ", precio=" + precio + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idPlato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return idPlato == other.idPlato;
	}
	
	

}
