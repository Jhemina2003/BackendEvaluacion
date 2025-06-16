package com.example.evaluacion.service;

import java.util.List;

import com.example.evaluacion.obj.cuentaRequestDTO;
import com.example.evaluacion.obj.cuentaResponseDTO;

public interface cuentaService {
	List<cuentaResponseDTO> getCuentas();
    cuentaResponseDTO getCuentaPorId(Long id);
    cuentaResponseDTO getCuentaPorNumero(String numeroCuenta);
    List<cuentaResponseDTO> getCuentasPorCliente(Long idCliente);
    cuentaResponseDTO addCuenta(cuentaRequestDTO cuentaRequestDTO);
    cuentaResponseDTO updateCuenta(Long id, cuentaRequestDTO cuentaRequestDTO);
    void deleteCuenta(Long id);
}
