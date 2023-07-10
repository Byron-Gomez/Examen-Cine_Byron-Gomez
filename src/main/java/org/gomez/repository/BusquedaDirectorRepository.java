package org.gomez.repository;

import org.gomez.entity.Director;

import java.util.List;

public interface BusquedaDirectorRepository {

    // declaracion de metodos de interfaces

    Director directorMayorParticipacion();
    List<Object[]> directorParticipaciones(Long participaciones);
    List<Director>  directorSinParticipaciones();
}
