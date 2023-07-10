package org.gomez.service;

import org.gomez.entity.GanaPremio;

import java.util.List;
import java.util.Optional;

public interface GanaPremioService {

    // declaracion de metodos de interfaces

    List<GanaPremio> listar();
    Optional<GanaPremio> porId(Integer id);
    void crear(GanaPremio premio);
    void editar(Integer id);
    void eliminar(Integer id);
}
