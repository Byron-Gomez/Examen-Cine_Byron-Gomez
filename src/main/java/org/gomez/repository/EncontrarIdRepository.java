package org.gomez.repository;

import java.util.List;

public interface EncontrarIdRepository<T>{
    List<T> listarPorId(Integer id,TipoBusqueda tipo);
    void porId(Integer id,TipoBusqueda tipoBusqueda);
    void eliminar(Integer id,TipoBusqueda tipoBusqueda);
    void eliminarUnicoId(Integer id1,Integer id2);

}
