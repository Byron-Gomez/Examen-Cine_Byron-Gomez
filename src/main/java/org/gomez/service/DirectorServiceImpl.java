package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Director;
import org.gomez.exception.MensajeExceptiones;
import org.gomez.repository.BusquedaDirectorRepository;
import org.gomez.repository.CrudRepository;
import org.gomez.repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

public class DirectorServiceImpl implements DirectorService{

    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;
    private CrudRepository<Director> repository;
    private BusquedaDirectorRepository busquedaDirectorRepository;
    public DirectorServiceImpl(EntityManager em) {
        this.em = em;
        this.repository= new DirectorRepository(em);
        this.busquedaDirectorRepository= new DirectorRepository(em);
    }

    @Override
    public List<Director> listar() {
        System.out.println("----- Lista de Directores -----");
        return repository.listar();
    }

    @Override
    public Optional<Director> porId(Integer id) {
        System.out.println("----- Busqueda de Actores por su ID -----");
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void crear(Director director) {
        try {
            System.out.println("----- Crear Director -----");
            em.getTransaction().begin();
            repository.crear(director);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new MensajeExceptiones("----- Informacion No Valida -----");
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("----- Editar Director -----");
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
            System.out.println("----- Eliminar Director -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Object[]> directorParticipaciones(Long participaciones) {
        System.out.println("----- Directores que Participaron en: " + participaciones + " -----");
        List<Object[]> resultados = busquedaDirectorRepository.directorParticipaciones(participaciones);
        return resultados;
    }

    @Override
    public List<Director> directorSinParticipaciones() {
        System.out.println("----- Directores sin Participaciones -----");
        List<Director> directores = busquedaDirectorRepository.directorSinParticipaciones();
        return directores;
    }

}
