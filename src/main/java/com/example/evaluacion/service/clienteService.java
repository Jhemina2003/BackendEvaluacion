package com.example.evaluacion.service;

import java.util.List;

import com.example.evaluacion.obj.clienteRequestDTO;
import com.example.evaluacion.obj.clienteResponseDTO;

public interface clienteService {
	List<clienteResponseDTO> getClientes();
    clienteResponseDTO getClientePorId(Long id);
    clienteResponseDTO getClientePorDocumento(String docIdentidad);
    clienteResponseDTO addCliente(clienteRequestDTO clienteRequestDTO);
    clienteResponseDTO updateCliente(Long id, clienteRequestDTO clienteRequestDTO);
    void deleteCliente(Long id);
}
