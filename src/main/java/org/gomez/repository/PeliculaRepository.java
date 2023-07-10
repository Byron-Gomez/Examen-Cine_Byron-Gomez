package org.gomez.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gomez.entity.Pelicula;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PeliculaRepository implements CrudRepository<Pelicula>,CambioMonedaRepository, BusquedaEnPeliculaRepository {

    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;

    public PeliculaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Pelicula> listar() {
        return em.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList();
    }

    @Override
    public Pelicula porId(Integer id) {
        return em.find(Pelicula.class,id);
    }

    @Override
    public void crear(Pelicula pelicula) {
        if (pelicula.getId()!=null && pelicula.getId()>0){
            em.merge(pelicula);
        }else {
            em.persist(pelicula);
        }
    }

    @Override
    public void editar(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Pelicula pelicula = porId(id);
        if (pelicula.getId() != null && pelicula.getId() > 0) {
            pelicula.setDistribuidora(JOptionPane.showInputDialog("Ingrese la distribuidora: ", pelicula.getDistribuidora()));
            pelicula.setNacionalidad(JOptionPane.showInputDialog("Ingrese la nacionalidad: ", pelicula.getNacionalidad()));

            String fechaDeEstrenoInput = JOptionPane.showInputDialog("Ingrese la fecha de estreno (yyyy/MM/dd):", pelicula.getFechaEstreno());
            if (!fechaDeEstrenoInput.isEmpty()) {
                LocalDate fechaDeEstreno = LocalDate.parse(fechaDeEstrenoInput, formatter);
                pelicula.setFechaEstreno(fechaDeEstreno.atStartOfDay());
            }

            pelicula.setYear(JOptionPane.showInputDialog("Ingrese el año: ", pelicula.getYear()));
            pelicula.setDuracion(Float.valueOf(JOptionPane.showInputDialog("Ingrese la duración de la película: ", pelicula.getDuracion())));
            pelicula.setTaquilla(Double.valueOf(JOptionPane.showInputDialog("Ingrese la taquilla recaudada: ", pelicula.getTaquilla())));
            pelicula.setProductora(JOptionPane.showInputDialog("Ingrese la productora: ", pelicula.getProductora()));

            em.merge(pelicula);
        }
    }


    @Override
    public void eliminar(Integer id) {
        Pelicula pelicula = porId(id);
        em.remove(pelicula);
    }

    @Override
    public void cambiarMoneda(TipoMoneda moneda) {

    }

    @Override
    public void cambiarMonedaPorPais(Pais pais, TipoMoneda moneda) {
        Query query = em.createQuery("UPDATE Pelicula p SET p.taquilla = p.taquilla * :factorConversion WHERE p.nacionalidad = :pais");
        query.setParameter("factorConversion", moneda.getValor());
        query.setParameter("pais", pais.getNacion());
        query.executeUpdate();
    }

    @Override
    public String distribuidora(String pelicula) {
        TypedQuery<String> query = em.createQuery("SELECT p.distribuidora FROM Pelicula p WHERE p.titulo = :pelicula", String.class);
        query.setParameter("pelicula", pelicula);
        String distribuidora = query.getSingleResult();

        return distribuidora;
    }


    @Override
    public List<Object[]> peliculaPorNacion() {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.nacionalidad, COUNT(p) FROM Pelicula p GROUP BY p.nacionalidad", Object[].class);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }

    @Override
    public List<Object[]> peliculaPorNacion(String nacionalidad) {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.nacionalidad, COUNT(p) FROM Pelicula p WHERE p.nacionalidad = :nacionalidad GROUP BY p.nacionalidad", Object[].class);
        query.setParameter("nacionalidad", nacionalidad);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }


    @Override
    public List<Object[]> peliculaYear() {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.titulo, p.year, CONCAT(p.titulo, ' (', p.year, ')'), p.nacionalidad FROM Pelicula p", Object[].class);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }


    @Override
    public List<Object[]> peliculaYearNacion(String nacion) {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.titulo, p.year, CONCAT(p.titulo, ' (', p.year, ')'), p.nacionalidad FROM Pelicula p WHERE p.nacionalidad = :nacion", Object[].class);
        query.setParameter("nacion", nacion);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }



    @Override
    public List<Object[]> recaudacionPeliculasNacion(String nacion) {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.nacionalidad, SUM(p.taquilla) FROM Pelicula p WHERE p.nacionalidad = :nacion GROUP BY p.nacionalidad", Object[].class);
        query.setParameter("nacion", nacion);
        List<Object[]> results = query.getResultList();
        return results;
    }



}
