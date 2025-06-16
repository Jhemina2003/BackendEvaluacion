package com.example.evaluacion.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class cuentaResponseDTO {
	private Long idCuenta;
	private Long idCliente;  // ID del cliente al que pertenece la cuenta
    private String tipoProducto;
    private String numeroCuenta;
    private String moneda;
    private Double monto;
    private LocalDate fecha_cre;
    private String sucursal;
}
