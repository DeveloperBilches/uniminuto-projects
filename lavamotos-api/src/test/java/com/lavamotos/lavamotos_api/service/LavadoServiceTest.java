package com.lavamotos.lavamotos_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lavamotos.lavamotos_api.dto.SolicitudLavado;
import com.lavamotos.lavamotos_api.model.OrdenLavado;
import com.lavamotos.lavamotos_api.model.TipoServicio;

public class LavadoServiceTest {
    private static final LocalDateTime OCHO_AM = LocalDateTime.of(2026, 11, 9, 8, 0);

    private LavadoService service;

    @BeforeEach
    void setUp() {
        service = new LavadoService();
    }

    @Test
    @DisplayName("Caso normal: lavado básico de una moto de 125 cc cuesta 15000")
    void lavadoBasico() {
        OrdenLavado o = service.registrar(new SolicitudLavado("abc12d", 125, TipoServicio.BASICO), OCHO_AM);
        assertEquals(15000.0, o.getValor(), 0.001);
        assertEquals("ABC12D", o.getPlaca());
    }

    @Test
    @DisplayName("Caso normal: moto de 300 cc paga 20% de recargo (25000 -> 30000)")
    void recargoAltoCilindraje() {
        OrdenLavado o = service.registrar(new SolicitudLavado("XYZ34E", 300, TipoServicio.COMPLETO), OCHO_AM);
        assertEquals(30000.0, o.getValor(), 0.001);
    }

    @Test
    @DisplayName("Borde: moto de exactamente 250 cc no paga recargo")
    void limiteDeCilindraje() {
        OrdenLavado o = service.registrar(new SolicitudLavado("KLM56F", 250, TipoServicio.COMPLETO), OCHO_AM);
        assertEquals(25000.0, o.getValor(), 0.001);
    }

    @Test
    @DisplayName("Caso normal: lavado básico que entra a las 8:00 se entrega a las 8:30")
    void horaDeEntrega() {
        OrdenLavado o = service.registrar(new SolicitudLavado("ABC12D", 125, TipoServicio.BASICO), OCHO_AM);
        assertEquals(LocalDateTime.of(2026, 11, 9, 8, 30), o.getHoraEntregaEstimada());
    }

    @Test
    @DisplayName("Caso normal: total recaudado de tres lavados (15000 + 25000 + 40000) es 80000")
    void totalRecaudado() {
        service.registrar(new SolicitudLavado("AAA11A", 125, TipoServicio.BASICO), OCHO_AM);
        service.registrar(new SolicitudLavado("BBB22B", 150, TipoServicio.COMPLETO), OCHO_AM);
        service.registrar(new SolicitudLavado("CCC33C", 200, TipoServicio.PREMIUM), OCHO_AM);
        assertEquals(80000.0, service.totalRecaudado(), 0.001);
    }

    @Test
    @DisplayName("Borde: sin lavados registrados el total es 0")
    void totalSinLavados() {
        assertEquals(0.0, service.totalRecaudado(), 0.001);
    }
}
