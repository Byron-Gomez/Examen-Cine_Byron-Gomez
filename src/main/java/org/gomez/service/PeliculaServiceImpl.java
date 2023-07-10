package org.gomez.service;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Pelicula;
import org.gomez.repository.*;

import java.util.List;

public class PeliculaServiceImpl implements PeliculaService {
    private final EntityManager em;
    private CrudRepository<Pelicula> repository;
    private CambioMonedaRepository monedaRepository;
    private BusquedaEnPeliculaRepository peliculaRepository;

    public PeliculaServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new PeliculaRepository(em);
        this.monedaRepository = new PeliculaRepository(em);
        this.peliculaRepository = new PeliculaRepository(em);
    }

    @Override
    public List<Pelicula> listar() {
        System.out.println("----- Lista de Peliculas -----");
        return repository.listar();
    }

    @Override
    public Pelicula porId(Integer id) {
        System.out.println("----- Busqueda de Pelicula por ID -----");
        return repository.porId(id);
    }

    @Override
    public void crear(Pelicula pelicula) {
        try {
            System.out.println("----- Crear Pelicula -----");
            em.getTransaction().begin();
            repository.crear(pelicula);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Integer id) {
        try {
            System.out.println("----- Editar Pelicula -----");
            em.getTransaction().begin();
            repository.editar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            System.out.println("----- Eliminar Pelicula -----");
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void cambiarMonedaPorPais(Pais pais, TipoMoneda moneda) {
        try {
            System.out.println("----- Cambio de Moneda -----");
            em.getTransaction().begin();
            monedaRepository.cambiarMonedaPorPais(pais, moneda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public String distribuidora(String pelicula) {
        System.out.println("----- Búsqueda de Distribuidora de: " + pelicula.toUpperCase() + " -----");
        return peliculaRepository.distribuidora(pelicula);
    }

    @Override
    public List<Object[]> peliculaPorNacion() {
        System.out.println("----- Lista de Número de Películas por País -----");
        return peliculaRepository.peliculaPorNacion();
    }

    @Override
    public List<Object[]> peliculaPorNacion(String nacion) {
        System.out.println("----- Lista de Número de Películas por País: " + nacion.toUpperCase() + " -----");
        return peliculaRepository.peliculaPorNacion(nacion);
    }

    @Override
    public List<Object[]> recaudacionPeliculasNacion(String nacion) {
        System.out.println("----- Recaudación de Dinero de Película por País -----");
        return peliculaRepository.recaudacionPeliculasNacion(nacion);
    }

    @Override
    public List<Object[]> peliculaYear() {
        System.out.println("----- Película y Año -----");
        return peliculaRepository.peliculaYear();
    }

    @Override
    public List<Object[]> peliculaYearNacion(String nacion) {
        System.out.println("----- Película con Año y Título Buscado por Nación " + nacion.toUpperCase() + " -----");
        return peliculaRepository.peliculaYearNacion(nacion);
    }
}