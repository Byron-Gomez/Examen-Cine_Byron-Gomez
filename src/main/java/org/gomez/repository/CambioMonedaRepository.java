package org.gomez.repository;

public interface CambioMonedaRepository {

    // declaracion de metodos de interfaces

    public void cambiarMoneda(TipoMoneda moneda);
    public void cambiarMonedaPorPais(Pais pais, TipoMoneda moneda);
}
