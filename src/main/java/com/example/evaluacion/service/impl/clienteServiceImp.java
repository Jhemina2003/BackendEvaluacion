package com.example.evaluacion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.evaluacion.dao.clienteDao;
import com.example.evaluacion.model.clienteModel;
import com.example.evaluacion.obj.clienteRequestDTO;
import com.example.evaluacion.obj.clienteResponseDTO;
import com.example.evaluacion.service.clienteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class clienteServiceImp implements clienteService {
    private final clienteDao clienteDao;
    private final ModelMapper modelMapper;

    @Transactional
    public List<clienteResponseDTO> getClientes() {
        List<clienteModel> listaClientes = clienteDao.findAll();
        return listaClientes.stream()
            .map(cliente -> modelMapper.map(cliente, clienteResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public clienteResponseDTO getClientePorId(Long id) {
        clienteModel cliente = clienteDao.findById(id).orElse(null);
        return cliente != null 
            ? modelMapper.map(cliente, clienteResponseDTO.class) 
            : null;
    }

    @Transactional
    public clienteResponseDTO getClientePorDocumento(String docIdentidad) {
        clienteModel cliente = clienteDao.findByDocIdentidad(docIdentidad);
        return cliente != null 
            ? modelMapper.map(cliente, clienteResponseDTO.class) 
            : null;
    }

    @Transactional
    public clienteResponseDTO addCliente(clienteRequestDTO clienteRequestDTO) {
        clienteModel cliente = modelMapper.map(clienteRequestDTO, clienteModel.class);
        cliente = clienteDao.save(cliente);
        return modelMapper.map(cliente, clienteResponseDTO.class);
    }

    @Transactional
    public clienteResponseDTO updateCliente(Long id, clienteRequestDTO clienteRequestDTO) {
        clienteModel cliente = clienteDao.findById(id).orElse(null);
        if (cliente != null) {
            modelMapper.map(clienteRequestDTO, cliente);
            cliente = clienteDao.save(cliente);
            return modelMapper.map(cliente, clienteResponseDTO.class);
        }
        return null;
    }

    @Transactional
    public void deleteCliente(Long id) {
        clienteDao.deleteById(id);
    }
}
