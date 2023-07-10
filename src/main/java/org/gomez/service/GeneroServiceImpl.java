package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Genero;
import org.gomez.repository.CrudRepository;
import org.gomez.repository.GeneroRepository;

import java.util.List;
import java.util.Optional;

public class GeneroServiceImpl implements GeneroService{
    private final EntityManager em;
    private CrudRepository<Genero> repository;
    public GeneroServiceImpl(EntityManager em) {
        this.em = em;
        this.repository= new GeneroRepository(em);
    }

    @Override
    public List<Genero> listar() {
        System.out.println("----- Lista de Generos -----");
        return repository.listar();
    }

    @Override
    public Optional<Genero> porId(Integer id) {
        System.out.println("----- Lista de Generos Por Id -----");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void crear(Genero genero) {
        try {
            System.out.println("----- Crear Genero de Pelicula -----");
            em.getTransaction().begin();
            repository.crear(genero);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("----- Editar Genero -----");
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
            System.out.println("----- Eliminar Genero -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
