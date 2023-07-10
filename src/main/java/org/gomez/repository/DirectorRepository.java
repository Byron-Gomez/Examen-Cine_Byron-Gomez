package org.gomez.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gomez.entity.Director;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DirectorRepository implements CrudRepository<Director>,BusquedaDirectorRepository{
    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;

    public DirectorRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Director> listar() {
        return em.createQuery("SELECT d FROM Director d",Director.class).getResultList();
    }

    @Override
    public Director porId(Integer id) {
        return em.find(Director.class,id);
    }

    @Override
    public void crear(Director director) {
        if (director.getId()!=null && director.getId()>0){
            em.merge(director);
        }else {
            em.persist(director);
        }
    }


    @Override
    public void editar(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Director director = porId(id);
        if (director.getId() != null && director.getId() > 0) {
            director.setNombre(JOptionPane.showInputDialog("Ingrese el nombre: ", director.getNombre()));
            director.setNacionalidad(JOptionPane.showInputDialog("Ingrese la nacionalidad: ", director.getNacionalidad()));

            String fechaDeNacimientoInput = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (yyyy/MM/dd):", director.getFNacimiento());
            if (!fechaDeNacimientoInput.isEmpty()) {
                LocalDate fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoInput, formatter);
                director.setFNacimiento(fechaDeNacimiento.atStartOfDay());
            }

            director.setLNacimiento(JOptionPane.showInputDialog("Ingrese el lugar de nacimiento: ", director.getLNacimiento()));

            String fechaDefuncionInput = JOptionPane.showInputDialog("Ingrese la fecha de defunción (yyyy/MM/dd):", director.getFMuerte());
            if (!fechaDefuncionInput.isEmpty()) {
                LocalDate fechaDefuncion = LocalDate.parse(fechaDefuncionInput, formatter);
                director.setFMuerte(fechaDefuncion.atStartOfDay());
            } else {
                director.setFMuerte(null);
            }

            director.setLMuerte(JOptionPane.showInputDialog("Ingrese el lugar de fallecimiento: ", director.getLMuerte()));

            em.merge(director);
        }
    }


    @Override
    public void eliminar(Integer id) {
        Director director = porId(id);
        em.remove(director);
    }

    @Override
    public Director directorMayorParticipacion() {
        return null;
    }

    @Override
    public List<Object[]> directorParticipaciones(Long participaciones) {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.director, COUNT(p) FROM Pelicula p GROUP BY p.director HAVING COUNT(p) = :participaciones", Object[].class);
        query.setParameter("participaciones", participaciones);
        List<Object[]> results = query.getResultList();
        return results;
    }


    @Override
    public List<Director> directorSinParticipaciones() {
        TypedQuery<Director> query = em.createQuery("SELECT d FROM Director d WHERE NOT EXISTS (SELECT p FROM Pelicula p WHERE p.director = d)", Director.class);
        List<Director> directors = query.getResultList();
        return directors;
    }

}
