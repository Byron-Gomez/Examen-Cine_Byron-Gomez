package org.gomez.service;


import org.gomez.entity.Premio;

import java.util.List;
import java.util.Optional;

public interface PremioService {
    List<Premio> listar();
    Optional<Premio> porId(Integer id);
    void crear(Premio premio);
    void editar(Integer id);
    void eliminar(Integer id);
}
