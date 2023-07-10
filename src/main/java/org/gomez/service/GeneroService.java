package org.gomez.service;


import org.gomez.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroService {

    // declaracion de metodos de interfaces

    List<Genero> listar();
    Optional<Genero> porId(Integer id);
    void crear(Genero genero);
    void editar(Integer id);
    void eliminar(Integer id);
}
