package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Participa;
import org.gomez.exception.MensajeExceptiones;
import org.gomez.repository.*;

import java.util.List;

public class ParticipaServiceImpl implements ParticipaService{
    private final EntityManager em;
    private CrudRepository<Participa> repository;
    private EncontrarIdRepository idRepository;
    private BusquedaActorRepository busquedaActorRepository;

    public ParticipaServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new ParticipaRepository(em);
        this.idRepository = new ParticipaRepository(em);
        this.busquedaActorRepository = new ParticipaRepository(em);
    }

    @Override
    public List<Participa> listar() {
        System.out.println("----- Lista de Participa -----");
        return repository.listar();
    }

    @Override
    public List listarPorId(Integer id, TipoBusqueda busqueda) {
        System.out.println("----- Lista de Participacion -----");
        try {
            return idRepository.listarPorId(id,busqueda);
        }catch (Exception e){
            e.printStackTrace();
            throw new MensajeExceptiones("Informacion no Valida");
        }
    }

    @Override
    public void porId(Integer id, TipoBusqueda busqueda){

    }


    @Override
    public void crear(Participa participa) {

        try {
            System.out.println("----- Crear Participa -----");
            em.getTransaction().begin();
            repository.crear(participa);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id, TipoBusqueda tipoBusqueda) {

    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("----- Eliminar Participa -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUnicoId(Integer id1, Integer id2) {
        try {
            System.out.println("----- Eliminar Actor en Pelicula -----");
            em.getTransaction().begin();
            idRepository.eliminarUnicoId(id1,id2);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Object[]> actorMayorParticipacion() {
        System.out.println("----- Actor con Mayor Participaciones -----");
        List<Object[]> resultados = busquedaActorRepository.actorMayorParticipacion();
        return resultados;
    }

}
