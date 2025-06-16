package com.example.evaluacion.obj;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class clienteResponseDTO {
	
	private Long idCliente;
    private String nombre;
    private String paterno;
    private String materno;
    private String tipoDoc;
    private String docIdentidad;
    private LocalDate fechaNac;
    private String genero;

}
