package com.lavamotos.lavamotos_api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lavamotos.lavamotos_api.dto.SolicitudLavado;
import com.lavamotos.lavamotos_api.model.OrdenLavado;
import com.lavamotos.lavamotos_api.service.LavadoService;

@RestController 
@RequestMapping("api/lavados")
public class LavadoController {
    private final LavadoService service;
    public LavadoController(LavadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenLavado registrar(@RequestBody SolicitudLavado solicitud) {
        return service.registrar(solicitud, LocalDateTime.now());
    }

    @GetMapping
    public List<OrdenLavado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public OrdenLavado buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping("/total")
    public Map<String, Double> total() {
        return Map.of("totalRecaudado", service.totalRecaudado());
    }
}
