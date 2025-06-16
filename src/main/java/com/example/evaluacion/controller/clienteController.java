package com.example.evaluacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluacion.obj.clienteRequestDTO;
import com.example.evaluacion.obj.clienteResponseDTO;
import com.example.evaluacion.service.clienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class clienteController {
    
    private final clienteService clienteService;
    
    @GetMapping("/getClientes")
    public ResponseEntity<List<clienteResponseDTO>> getClientes() {
        try {
            return new ResponseEntity<>(this.clienteService.getClientes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getClientePorId/{id}")
    public ResponseEntity<clienteResponseDTO> getClientePorId(@PathVariable Long id) {
        try {
            clienteResponseDTO cliente = this.clienteService.getClientePorId(id);
            if (cliente != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getClientePorDocumento")
    public ResponseEntity<clienteResponseDTO> getClientePorDocumento(@RequestParam String docIdentidad) {
        try {
            clienteResponseDTO cliente = this.clienteService.getClientePorDocumento(docIdentidad);
            if (cliente != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addCliente")
    public ResponseEntity<clienteResponseDTO> addCliente(@RequestBody clienteRequestDTO clienteRequestDTO) {
        try {
            return new ResponseEntity<>(this.clienteService.addCliente(clienteRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateCliente/{id}")
    public ResponseEntity<clienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody clienteRequestDTO clienteRequestDTO) {
        try {
            clienteResponseDTO clienteActualizado = this.clienteService.updateCliente(id, clienteRequestDTO);
            if (clienteActualizado != null) {
                return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/deleteCliente/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        try {
            this.clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

