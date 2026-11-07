package com.lavamotos.lavamotos_api.dto;

import com.lavamotos.lavamotos_api.model.TipoServicio;

public class SolicitudLavado {
    private String placa;
    private int cilindrada;
    private TipoServicio tipo;

    public SolicitudLavado() {
    }

    public SolicitudLavado(String placa, int cilindrada, TipoServicio tipo) {
        this.placa = placa;
        this.cilindrada = cilindrada;
        this.tipo = tipo;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    public TipoServicio getTipo() { return tipo; }
    public void setTipo(TipoServicio tipo) { this.tipo = tipo; }
}
