package org.gomez.service;

import org.gomez.entity.Pelicula;
import org.gomez.repository.Pais;
import org.gomez.repository.TipoMoneda;

import java.util.List;

public interface PeliculaService {

    // declaracion de metodos de interfaces

    List<Pelicula> listar();
    Pelicula porId(Integer id);
    void crear(Pelicula pelicula);
    void editar(Integer id);
    void eliminar(Integer id);
    void cambiarMonedaPorPais(Pais pais, TipoMoneda moneda);
     String distribuidora(String pelicula);
    List<Object[]> peliculaPorNacion(String nacion);
    List<Object[]> recaudacionPeliculasNacion(String nacion);
    List<Object[]> peliculaPorNacion();

    List<Object[]> peliculaYear();
    List<Object[]> peliculaYearNacion(String pelicula);
}
