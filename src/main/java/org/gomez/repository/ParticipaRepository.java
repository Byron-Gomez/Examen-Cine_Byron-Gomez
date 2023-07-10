package org.gomez.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gomez.entity.Actor;
import org.gomez.entity.Participa;


import java.util.List;

public class ParticipaRepository implements CrudRepository<Participa>, EncontrarIdRepository<Participa>, BusquedaActorRepository{

    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;

    public ParticipaRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Participa> listar() {
        return em.createQuery("SELECT p FROM Participa p", Participa.class).getResultList();
    }

    @Override
    public Participa porId(Integer id) {
        return em.find(Participa.class, id);
    }


    @Override
    public void crear(Participa participa) {
        if (participa.getPelicula() != null && participa.getActor() != null) {
            em.merge(participa);
        } else {
            em.persist(participa);
        }
    }

    @Override
    public void editar(Integer id) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void eliminar(Integer id, TipoBusqueda busqueda) {
        TypedQuery<Participa> query = null;
        if (busqueda == TipoBusqueda.POR_ACTOR) {
            System.out.println("----- Eliminar Actor Por ID -----");
            query = em.createQuery(
                    "DELETE FROM Participa pa WHERE pa.actor.id=:id",
                    Participa.class
            );
        } else if (busqueda == TipoBusqueda.POR_PELICULA) {
            System.out.println("----- Eliminar Pelicula Por ID -----");
            query = em.createQuery(
                    "DELETE FROM Participa pa WHERE pa.pelicula.id=:id",
                    Participa.class
            );
        }
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void eliminarUnicoId(Integer id1, Integer id2) {
        System.out.println("----- Eliminar Participa Por ID de Pelicula y ID de Actor -----");
        Query query = em.createQuery(
                "SELECT pa FROM Participa pa WHERE pa.pelicula.id=:id1 AND pa.actor.id=:id2",
                Participa.class
        );
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        Participa participa = (Participa) query.getSingleResult();
        em.remove(participa);
    }

    @Override
    public List<Participa> listarPorId(Integer id, TipoBusqueda busqueda) {
        TypedQuery<Participa> query = null;
        if (busqueda == TipoBusqueda.POR_ACTOR) {
            System.out.println("----- Buscar Participaciones Por ID de Actor ----");
            query = em.createQuery(
                    "SELECT pa FROM Participa pa JOIN pa.pelicula p WHERE pa.actor.id = :id",
                    Participa.class
            );
        } else if (busqueda == TipoBusqueda.POR_PELICULA) {
            System.out.println("----- Buscar Participaciones Por ID de Pelicula ----");
            query = em.createQuery(
                    "SELECT pa FROM Participa pa JOIN pa.actor a WHERE pa.pelicula.id = :id",
                    Participa.class
            );
        }
        query.setParameter("id", id);
        return query.getResultList();
    }


    @Override
    public void porId(Integer id, TipoBusqueda tipoBusqueda) {

    }

    @Override
    public List<Actor> listaActorMuertoNacion(String nacion) {
        return null;
    }

    @Override
    public List<Object[]> actorMayorParticipacion() {
        TypedQuery<Object[]> query = em.createQuery("SELECT pa.actor, COUNT(pa) FROM Participa pa GROUP BY pa.actor ORDER BY COUNT(pa) DESC", Object[].class);
        query.setMaxResults(1);
        List<Object[]> results = query.getResultList();
        return results;
    }

}

