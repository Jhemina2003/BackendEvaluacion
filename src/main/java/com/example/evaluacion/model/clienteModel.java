package com.example.evaluacion.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "cliente")
public class clienteModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", unique = true, nullable = false)
	private Long idCliente;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "ap_paterno", nullable = false)
	private String paterno;
	
	@Column(name = "ap_materno", nullable = false)
	private String materno;
	
	@Column(name = "tipo_documento", nullable = false)
	private String tipoDoc;
	
	@Column(name = "documento_identidad", unique= true, nullable = false)
	private String docIdentidad;
	
	@Column(name = "fecha_nac", nullable = false)
	private LocalDate fechaNac;
	
	@Column(name = "genero", nullable = false)
	private String genero;
	
	@OneToMany(mappedBy = "cliente")
    private List<cuentaModel> cuentas;
	
}
