package org.gomez.repository;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Premio;

import java.util.List;

public class PremioRespository implements CrudRepository<Premio >{

    // declaracion de atributos y instanciacion con EntityManager
    private final EntityManager em;

    public PremioRespository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Premio> listar() {
        return em.createQuery("SELECT p FROM Premio p", Premio.class).getResultList();
    }

    @Override
    public Premio porId(Integer id) {
        return em.find(Premio.class,id);
    }

    @Override
    public void crear(Premio premio) {
        if (premio.getId()!=null && premio.getId()>0){
            em.merge(premio);
        }else {
            em.persist(premio);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {
        Premio premio= porId(id);
        em.remove(premio);
    }
}
