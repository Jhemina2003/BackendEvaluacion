package com.example.evaluacion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evaluacion.model.clienteModel;
import com.example.evaluacion.model.cuentaModel;

public interface cuentaDao extends JpaRepository<cuentaModel, Long>{
	cuentaModel findByNumeroCuenta(String numeroCuenta);
    
    boolean existsByNumeroCuenta(String numeroCuenta);
    
    List<cuentaModel> findByCliente(clienteModel cliente);
    
    List<cuentaModel> findByTipoProducto(String tipoProducto);
    
    List<cuentaModel> findBySucursal(String sucursal);
}


