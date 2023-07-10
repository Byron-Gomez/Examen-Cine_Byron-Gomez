package org.gomez.repository;

import jakarta.persistence.EntityManager;
import org.gomez.entity.GanaPremio;

import java.util.List;

public class GanaPremioRepository implements CrudRepository<GanaPremio>{
    private final EntityManager em;

    public GanaPremioRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<GanaPremio> listar() {
        return em.createQuery("SELECT g FROM GanaPremio g", GanaPremio.class).getResultList();
    }

    @Override
    public GanaPremio porId(Integer id) {
        return em.find(GanaPremio.class,id);
    }

    @Override
    public void crear(GanaPremio ganaPremio) {
        if (ganaPremio.getPremio()!=null
                && ganaPremio.getPelicula()!=null
                && ganaPremio.getYear()!=null ){
            em.merge(ganaPremio);
        }else {
            em.persist(ganaPremio);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {
        GanaPremio premio = porId(id);
        em.remove(premio);
    }
}
