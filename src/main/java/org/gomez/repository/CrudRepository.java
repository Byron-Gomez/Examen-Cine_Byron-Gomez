package org.gomez.repository;

import java.util.List;

public interface CrudRepository <T>{

    // declaracion de metodos de interfaces

    List<T> listar();
    T porId(Integer id);
    void crear(T t);
    void editar(Integer id);
    void eliminar(Integer id);
}
