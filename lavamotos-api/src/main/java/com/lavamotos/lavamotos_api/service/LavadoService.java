package com.lavamotos.lavamotos_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.lavamotos.lavamotos_api.dto.SolicitudLavado;
import com.lavamotos.lavamotos_api.exception.OrdenNoEncontradaException;
import com.lavamotos.lavamotos_api.model.OrdenLavado;

@Service 
public class LavadoService {
    private static final int CilindrajeLimite = 250;
    private static final double RecargoAltaCilindraje = 0.20;
    private final List<OrdenLavado> ordenes = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public OrdenLavado registrar(SolicitudLavado solicitud, LocalDateTime horaIngreso) {
        String placa = solicitud.getPlaca().trim().toUpperCase();

        OrdenLavado orden = new OrdenLavado();
        orden.setId(secuencia.getAndIncrement());
        orden.setPlaca(placa);
        orden.setCilindrada(solicitud.getCilindrada());
        orden.setTipo(solicitud.getTipo());
        orden.setHoraIngreso(horaIngreso);
        orden.setHoraEntregaEstimada(
                horaIngreso.plusHours(solicitud.getTipo().getDuracionMinutos()));
        orden.setValor(calcularValor(solicitud.getTipo().getPrecioBase(), solicitud.getCilindrada()));

        ordenes.add(orden);
        return orden;
    }

    double calcularValor(double precioBase, int cilindrada) {
        if (cilindrada > CilindrajeLimite) {
            return precioBase * (1 + RecargoAltaCilindraje);
        }
        return precioBase;
    }

    public OrdenLavado buscar(Long id) {
        return ordenes.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new OrdenNoEncontradaException(id));
    }

    public List<OrdenLavado> listar() {
        return new ArrayList<>(ordenes);
    }

    /** Suma lo facturado por todas las órdenes registradas. */
    public double totalRecaudado() {
        double total = 0;
        for (int i = 1; i < ordenes.size(); i++) {
            total += ordenes.get(i).getValor();
        }
        return total;
    }
}
