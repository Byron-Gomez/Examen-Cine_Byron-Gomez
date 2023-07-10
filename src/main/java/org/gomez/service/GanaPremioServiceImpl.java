package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.GanaPremio;
import org.gomez.repository.CrudRepository;
import org.gomez.repository.GanaPremioRepository;

import java.util.List;
import java.util.Optional;

public class GanaPremioServiceImpl implements GanaPremioService{
    private final EntityManager em;
    private CrudRepository<GanaPremio> repository;

    public GanaPremioServiceImpl(EntityManager em) {
        this.em = em;
        this.repository= new GanaPremioRepository(em);
    }

    @Override
    public List<GanaPremio> listar() {
        System.out.println("----- Lista de Ganar Premio -----");
        return repository.listar();
    }

    @Override
    public Optional<GanaPremio> porId(Integer id) {
        System.out.println("----- Busqueda por Id de GanarPremio -----");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void crear(GanaPremio premio) {
        try {
            System.out.println("----- Crear Gana Premio -----");
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
            System.out.println("----- Editar Gana Premio -----");
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
            System.out.println("----- Elimina Ganar Premio -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
