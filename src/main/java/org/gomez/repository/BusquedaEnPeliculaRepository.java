package org.gomez.repository;

import java.util.List;

public interface BusquedaEnPeliculaRepository extends BusquedaDistribuidoraRepository, BusquedaRecaudacionRepository {

     List<Object[]> peliculaPorNacion();

    List<Object[]> peliculaPorNacion(String nacionalidad);

    List<Object[]> peliculaYear();
    List<Object[]> peliculaYearNacion(String nacion);
}
