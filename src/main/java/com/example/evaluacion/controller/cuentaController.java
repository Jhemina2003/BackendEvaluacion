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

import com.example.evaluacion.obj.cuentaRequestDTO;
import com.example.evaluacion.obj.cuentaResponseDTO;
import com.example.evaluacion.service.cuentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuenta")
@RequiredArgsConstructor
public class cuentaController {
    
    private final cuentaService cuentaService;
    
    @GetMapping("/getCuentas")
    public ResponseEntity<List<cuentaResponseDTO>> getCuentas() {
        try {
            return new ResponseEntity<>(this.cuentaService.getCuentas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getCuentaPorId/{id}")
    public ResponseEntity<cuentaResponseDTO> getCuentaPorId(@PathVariable Long id) {
        try {
            cuentaResponseDTO cuenta = this.cuentaService.getCuentaPorId(id);
            if (cuenta != null) {
                return new ResponseEntity<>(cuenta, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getCuentaPorNumero")
    public ResponseEntity<cuentaResponseDTO> getCuentaPorNumero(@RequestParam String numeroCuenta) {
        try {
            cuentaResponseDTO cuenta = this.cuentaService.getCuentaPorNumero(numeroCuenta);
            if (cuenta != null) {
                return new ResponseEntity<>(cuenta, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getCuentasPorCliente/{idCliente}")
    public ResponseEntity<List<cuentaResponseDTO>> getCuentasPorCliente(@PathVariable Long idCliente) {
        try {
            List<cuentaResponseDTO> cuentas = this.cuentaService.getCuentasPorCliente(idCliente);
            if (cuentas != null && !cuentas.isEmpty()) {
                return new ResponseEntity<>(cuentas, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@PostMapping("/addCuenta")
    public ResponseEntity<cuentaResponseDTO> addCuenta(@RequestBody cuentaRequestDTO cuentaRequestDTO) {
        try {
            cuentaResponseDTO nuevaCuenta = this.cuentaService.addCuenta(cuentaRequestDTO);
            if (nuevaCuenta != null) {
                return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    @PostMapping("/addCuenta")
    public ResponseEntity<?> addCuenta(@RequestBody cuentaRequestDTO cuentaRequestDTO) {
        try {
            // Validaciones previas
            if (cuentaRequestDTO == null) {
                return ResponseEntity.badRequest().body("Datos de cuenta inválidos");
            }

            // Validar campos obligatorios
            if (cuentaRequestDTO.getIdCliente() == null) {
                return ResponseEntity.badRequest().body("El cliente es obligatorio");
            }
            if (cuentaRequestDTO.getNumeroCuenta() == null || cuentaRequestDTO.getNumeroCuenta().isEmpty()) {
                return ResponseEntity.badRequest().body("El número de cuenta es obligatorio");
            }
            
            // Imprimir los datos recibidos para depuración
            System.out.println("Datos de cuenta recibidos: " + cuentaRequestDTO);

            cuentaResponseDTO nuevaCuenta = this.cuentaService.addCuenta(cuentaRequestDTO);
            
            if (nuevaCuenta == null) {
                return ResponseEntity.badRequest().body("No se pudo crear la cuenta");
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);

        } catch (Exception e) {
            // Loguear el error completo
            e.printStackTrace();

            // Devolver mensaje de error detallado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear cuenta: " + e.getMessage());
        }
    }
    
    @PutMapping("/updateCuenta/{id}")
    public ResponseEntity<cuentaResponseDTO> updateCuenta(@PathVariable Long id, @RequestBody cuentaRequestDTO cuentaRequestDTO) {
        try {
            cuentaResponseDTO cuentaActualizada = this.cuentaService.updateCuenta(id, cuentaRequestDTO);
            if (cuentaActualizada != null) {
                return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/deleteCuenta/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        try {
            this.cuentaService.deleteCuenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}