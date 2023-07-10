package org.gomez.service;

import org.gomez.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    List<Actor> listar();
    Optional<Actor> porId(Integer id);
    void crear(Actor actor);
    Actor editar(Integer id);
    void eliminar(Integer id);
    List<Actor> listaActorMuertoNacion(String nacion);
}
