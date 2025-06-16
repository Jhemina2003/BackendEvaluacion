package com.example.evaluacion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.evaluacion.dao.clienteDao;
import com.example.evaluacion.dao.cuentaDao;
import com.example.evaluacion.model.clienteModel;
import com.example.evaluacion.model.cuentaModel;
import com.example.evaluacion.obj.cuentaRequestDTO;
import com.example.evaluacion.obj.cuentaResponseDTO;
import com.example.evaluacion.service.cuentaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class cuentaServiceImp implements cuentaService {
    private final cuentaDao cuentaDao;
    private final clienteDao clienteDao;
    private final ModelMapper modelMapper;

    @Transactional
    public List<cuentaResponseDTO> getCuentas() {
        List<cuentaModel> listaCuentas = cuentaDao.findAll();
        return listaCuentas.stream()
            .map(cuenta -> modelMapper.map(cuenta, cuentaResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public cuentaResponseDTO getCuentaPorId(Long id) {
        cuentaModel cuenta = cuentaDao.findById(id).orElse(null);
        return cuenta != null 
            ? modelMapper.map(cuenta, cuentaResponseDTO.class) 
            : null;
    }

    @Transactional
    public cuentaResponseDTO getCuentaPorNumero(String numeroCuenta) {
        cuentaModel cuenta = cuentaDao.findByNumeroCuenta(numeroCuenta);
        return cuenta != null 
            ? modelMapper.map(cuenta, cuentaResponseDTO.class) 
            : null;
    }

    @Transactional
    public List<cuentaResponseDTO> getCuentasPorCliente(Long idCliente) {
        clienteModel cliente = clienteDao.findById(idCliente).orElse(null);
        if (cliente != null) {
            List<cuentaModel> cuentas = cuentaDao.findByCliente(cliente);
            return cuentas.stream()
                .map(cuenta -> modelMapper.map(cuenta, cuentaResponseDTO.class))
                .collect(Collectors.toList());
        }
        return null;
    }

    /*@Transactional
    public cuentaResponseDTO addCuenta(cuentaRequestDTO cuentaRequestDTO) {
        // Buscar el cliente
        clienteModel cliente = clienteDao.findById(cuentaRequestDTO.getIdCliente()).orElse(null);
        if (cliente == null) {
            return null; // O manejar el error de otra manera
        }

        // Mapear el DTO a modelo
        cuentaModel cuenta = modelMapper.map(cuentaRequestDTO, cuentaModel.class);
        cuenta.setCliente(cliente);

        // Guardar la cuenta
        cuenta = cuentaDao.save(cuenta);
        return modelMapper.map(cuenta, cuentaResponseDTO.class);
    }*/
    
    @Transactional
    public cuentaResponseDTO addCuenta(cuentaRequestDTO cuentaRequestDTO) {
        // Buscar el cliente
        clienteModel cliente = clienteDao.findById(cuentaRequestDTO.getIdCliente())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Crear nueva cuenta
        cuentaModel cuenta = new cuentaModel();
        cuenta.setCliente(cliente);
        cuenta.setTipoProducto(cuentaRequestDTO.getTipoProducto());
        cuenta.setNumeroCuenta(cuentaRequestDTO.getNumeroCuenta());
        cuenta.setMoneda(cuentaRequestDTO.getMoneda());
        cuenta.setMonto(cuentaRequestDTO.getMonto());
        cuenta.setFecha_cre(cuentaRequestDTO.getFecha_cre());
        cuenta.setSucursal(cuentaRequestDTO.getSucursal());

        // Guardar la cuenta
        cuenta = cuentaDao.save(cuenta);

        // Convertir a DTO
        cuentaResponseDTO responseDTO = new cuentaResponseDTO();
        responseDTO.setIdCuenta(cuenta.getIdCuenta());
        responseDTO.setIdCliente(cliente.getIdCliente());
        responseDTO.setTipoProducto(cuenta.getTipoProducto());
        responseDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        responseDTO.setMoneda(cuenta.getMoneda());
        responseDTO.setMonto(cuenta.getMonto());
        responseDTO.setFecha_cre(cuenta.getFecha_cre());
        responseDTO.setSucursal(cuenta.getSucursal());

        return responseDTO;
    }

    /*@Transactional
    public cuentaResponseDTO updateCuenta(Long id, cuentaRequestDTO cuentaRequestDTO) {
        cuentaModel cuenta = cuentaDao.findById(id).orElse(null);
        if (cuenta != null) {
            // Buscar el cliente si se proporciona un nuevo ID de cliente
            if (cuentaRequestDTO.getIdCliente() != null) {
                clienteModel cliente = clienteDao.findById(cuentaRequestDTO.getIdCliente()).orElse(null);
                if (cliente != null) {
                    cuenta.setCliente(cliente);
                }
            }

            // Mapear los otros campos
            modelMapper.map(cuentaRequestDTO, cuenta);
            cuenta = cuentaDao.save(cuenta);
            return modelMapper.map(cuenta, cuentaResponseDTO.class);
        }
        return null;
    }*/
    @Transactional
    public cuentaResponseDTO updateCuenta(Long id, cuentaRequestDTO cuentaRequestDTO) {
        cuentaModel cuenta = cuentaDao.findById(id).orElse(null);
        if (cuenta != null) {
            // Buscar el cliente si se proporciona un nuevo ID de cliente
            if (cuentaRequestDTO.getIdCliente() != null) {
                clienteModel cliente = clienteDao.findById(cuentaRequestDTO.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                cuenta.setCliente(cliente);
            }

            // Asignar explícitamente los campos en lugar de usar modelMapper
            cuenta.setTipoProducto(cuentaRequestDTO.getTipoProducto());
            cuenta.setNumeroCuenta(cuentaRequestDTO.getNumeroCuenta());
            cuenta.setMoneda(cuentaRequestDTO.getMoneda());
            cuenta.setMonto(cuentaRequestDTO.getMonto());
            cuenta.setFecha_cre(cuentaRequestDTO.getFecha_cre());
            cuenta.setSucursal(cuentaRequestDTO.getSucursal());

            cuenta = cuentaDao.save(cuenta);
            
            // Convertir a DTO y devolver
            cuentaResponseDTO responseDTO = new cuentaResponseDTO();
            responseDTO.setIdCuenta(cuenta.getIdCuenta());
            responseDTO.setIdCliente(cuenta.getCliente().getIdCliente());
            responseDTO.setTipoProducto(cuenta.getTipoProducto());
            responseDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            responseDTO.setMoneda(cuenta.getMoneda());
            responseDTO.setMonto(cuenta.getMonto());
            responseDTO.setFecha_cre(cuenta.getFecha_cre());
            responseDTO.setSucursal(cuenta.getSucursal());

            return responseDTO;
        }
        return null;
    }

    @Transactional
    public void deleteCuenta(Long id) {
        cuentaDao.deleteById(id);
    }
}