package org.gomez.service;

import org.gomez.entity.Participa;
import org.gomez.repository.TipoBusqueda;

import java.util.List;

public interface ParticipaService {
    List<Participa> listar();
    List listarPorId(Integer id, TipoBusqueda busqueda);
    void porId(Integer id, TipoBusqueda busqueda);
    void crear(Participa participa);
    void editar(Integer id,TipoBusqueda tipoBusqueda);
    void eliminar(Integer id);
    void eliminarUnicoId(Integer id1,Integer id2);
    List<Object[]> actorMayorParticipacion();
}
