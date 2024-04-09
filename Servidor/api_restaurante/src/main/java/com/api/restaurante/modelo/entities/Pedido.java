package com.api.restaurante.modelo.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="productos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	@ManyToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Producto producto;
	private int cantidad;
	@Column(name="numero_comensales")
	private int numeroComensales;
//@Temporal(TemporalType.DATE)
	private Date fecha;
	private String servido;
}
