package com.lavamotos.lavamotos_api.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorErrores {
    @ExceptionHandler(OrdenNoEncontradaException.class)
    public ResponseEntity<Map<String, String>> noEncontrada(OrdenNoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }
    
    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<Map<String, String>> datosInvalidos(DatosInvalidosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
}
