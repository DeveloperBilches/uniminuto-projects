package com.lavamotos.lavamotos_api.model;

public enum TipoServicio {
    BASICO(15000, 30),
    COMPLETO(25000, 45),
    PREMIUM(40000, 70);

    private final double precioBase;
    private final int duracionMinutos;

    TipoServicio(double precioBase, int duracionMinutos) {
        this.precioBase = precioBase;
        this.duracionMinutos = duracionMinutos;
    }

    public double getPrecioBase() { return precioBase; }
    public int getDuracionMinutos() { return duracionMinutos; }
}
