package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Premio;
import org.gomez.repository.CrudRepository;
import org.gomez.repository.PremioRespository;

import java.util.List;
import java.util.Optional;

public class PremioServiceImpl implements PremioService{
    private final EntityManager em;
    private CrudRepository<Premio> repository;

    public PremioServiceImpl(EntityManager em) {
        this.em = em;
        this.repository=new PremioRespository(em);
    }

    @Override
    public List<Premio> listar() {
        System.out.println("----- Lista de Premio -----");
        return repository.listar();
    }

    @Override
    public Optional<Premio> porId(Integer id) {
        System.out.println("----- Busqueda de Premio por ID");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void crear(Premio premio) {
        try {
            System.out.println("----- Crear Premio -----");
            em.getTransaction().begin();
            repository.crear(premio);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("----- Editar Premio");
            em.getTransaction().begin();
            repository.editar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("----- Eliminr Premio -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
