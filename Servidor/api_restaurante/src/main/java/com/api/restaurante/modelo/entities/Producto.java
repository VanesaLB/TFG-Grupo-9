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
	@Column(name="id_producto")
	private int idProducto;
	private String ingredientes;
	private String descripcion;
	private String tipo;
	private String vegano;
	private String gluten;
	private double precio;
	
	public Producto() {
		super();
	}

	public Producto(int idProducto, String ingredientes, String descripcion, String tipo, String vegano, String gluten,
			double precio) {
		super();
		this.idProducto = idProducto;
		this.ingredientes = ingredientes;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.vegano = vegano;
		this.gluten = gluten;
		this.precio = precio;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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
	public int hashCode() {
		return Objects.hash(idProducto);
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
		return idProducto == other.idProducto;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", ingredientes=" + ingredientes + ", descripcion=" + descripcion
				+ ", tipo=" + tipo + ", vegano=" + vegano + ", gluten=" + gluten + ", precio=" + precio + "]";
	}
	
	

	
	
	

}
