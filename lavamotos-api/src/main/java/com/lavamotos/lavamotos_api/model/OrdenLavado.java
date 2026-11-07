package com.lavamotos.lavamotos_api.model;

import java.time.LocalDateTime;

public class OrdenLavado {
    private Long id;
    private String placa;
    private int cilindrada;
    private TipoServicio tipo;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaEntregaEstimada;
    private double valor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    public TipoServicio getTipo() { return tipo; }
    public void setTipo(TipoServicio tipo) { this.tipo = tipo; }

    public LocalDateTime getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(LocalDateTime horaIngreso) { this.horaIngreso = horaIngreso; }

    public LocalDateTime getHoraEntregaEstimada() { return horaEntregaEstimada; }
    public void setHoraEntregaEstimada(LocalDateTime horaEntregaEstimada) { this.horaEntregaEstimada = horaEntregaEstimada; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}
