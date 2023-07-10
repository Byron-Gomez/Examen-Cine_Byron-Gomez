package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Actor;
import org.gomez.exception.MensajeExceptiones;
import org.gomez.repository.ActorRepository;
import org.gomez.repository.BusquedaActorRepository;
import org.gomez.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class ActorServiceImpl implements ActorService{

    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;
    private CrudRepository<Actor> repository;
    private BusquedaActorRepository busquedaActorRepository;
    public ActorServiceImpl(EntityManager em) {
        this.em = em;
        this.repository= new ActorRepository(em);
        this.busquedaActorRepository= new ActorRepository(em);
    }

    @Override
    public List<Actor> listar() {
        System.out.println("----- Lista de Actores -----");
        return repository.listar();
    }

    @Override
    public Optional<Actor> porId(Integer id) {
        System.out.println("----- Busqueda de Actores por su Id -----");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void crear(Actor actor) {
        try {
            System.out.println("----- Crear Actor -----");
            em.getTransaction().begin();
            repository.crear(actor);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new MensajeExceptiones("----- Informacion No Valida -----");

        }
    }

    @Override
    public Actor editar(Integer id) {
        try {
            System.out.println("----- Editar Actor -----");
            em.getTransaction().begin();
            repository.editar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("----- Eliminar Actor -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Actor> listaActorMuertoNacion(String nacion) {
        System.out.println("----- Lista de Actores Muertos Por Nación " + nacion.toUpperCase() + " -----");
        List<Actor> actoresFallecidos = busquedaActorRepository.listaActorMuertoNacion(nacion);
        return actoresFallecidos;
    }

}
