package org.gomez.repository;

import org.gomez.entity.Director;

import java.util.List;

public interface BusquedaDirectorRepository {
    Director directorMayorParticipacion();
    List<Object[]> directorParticipaciones(Long participaciones);
    List<Director>  directorSinParticipaciones();
}
