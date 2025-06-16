package com.example.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evaluacion.model.clienteModel;

public interface clienteDao extends JpaRepository<clienteModel, Long> {
	
	clienteModel findByDocIdentidad(String docIdentidad);
    
    boolean existsByDocIdentidad(String docIdentidad);
    
    clienteModel findByNombreAndPaterno(String nombre, String paterno);

}

