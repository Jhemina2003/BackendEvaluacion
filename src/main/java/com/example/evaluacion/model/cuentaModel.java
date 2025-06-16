package com.example.evaluacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cuenta")
public class cuentaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta", unique = true, nullable = false)
	private Long idCuenta;
	
	@ManyToOne(optional = false)  // Indica que este campo no puede ser nulo
	@JoinColumn(name = "id_cliente", nullable = false, updatable = false)  // restricción de no actualizable
	private clienteModel cliente;
	
	@Column(name = "tipo_producto", nullable = false)
	private String tipoProducto;
	
	@Column(name = "numero_cuenta", nullable = false, unique = true)
	private String numeroCuenta;
	
	@Column(name = "moneda", nullable = false)
	private String moneda;
	
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@Column(name = "fecha_creacion", nullable = false)
	private LocalDate fecha_cre;
	
	@Column(name = "sucursal", nullable = false)
	private String sucursal;
	
	
}
