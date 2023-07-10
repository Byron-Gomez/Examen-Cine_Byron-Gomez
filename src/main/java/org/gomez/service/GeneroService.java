package org.gomez.service;


import org.gomez.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    List<Genero> listar();
    Optional<Genero> porId(Integer id);
    void crear(Genero genero);
    void editar(Integer id);
    void eliminar(Integer id);
}
