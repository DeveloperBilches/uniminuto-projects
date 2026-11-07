package com.lavamotos.lavamotos_api.exception;

public class OrdenNoEncontradaException extends RuntimeException {
    public OrdenNoEncontradaException(Long id) {
        super("No existe la orden de lavado con id " + id);
    }
}