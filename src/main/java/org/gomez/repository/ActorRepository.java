package org.gomez.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gomez.entity.Actor;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActorRepository implements CrudRepository<Actor>,BusquedaActorRepository{
    private final EntityManager em;

    public ActorRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Actor> listar() {
        return em.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
    }

    @Override
    public Actor porId(Integer id) {
        return em.find(Actor.class,id);
    }

    @Override
    public void crear(Actor actor) {
        if (actor.getId()!=null && actor.getId()>0){
            em.merge(actor);
        }else {
            em.persist(actor);
        }
    }

    @Override
    public void editar(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Actor actor = porId(id);
        if (actor.getId() != null && actor.getId() > 0) {
            actor.setNombre(JOptionPane.showInputDialog("Ingrese el nombre: ", actor.getNombre()));
            actor.setNacionalidad(JOptionPane.showInputDialog("Ingrese la nacionalidad: ", actor.getNacionalidad()));

            String fechaDeNacimientoInput = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (yyyy/MM/dd):", actor.getFNacimiento());
            if (!fechaDeNacimientoInput.isEmpty()) {
                LocalDate fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoInput, formatter);
                actor.setFNacimiento(fechaDeNacimiento.atStartOfDay());
            }

            actor.setLNacimiento(JOptionPane.showInputDialog("Ingrese el lugar de nacimiento: ", actor.getLNacimiento()));

            String fechaDefuncionInput = JOptionPane.showInputDialog("Ingrese la fecha de defunción (yyyy/MM/dd):", actor.getFMuerte());
            if (!fechaDefuncionInput.isEmpty()) {
                LocalDate fechaDefuncion = LocalDate.parse(fechaDefuncionInput, formatter);
                actor.setFMuerte(fechaDefuncion.atStartOfDay());
            } else {
                actor.setFMuerte(null);
            }

            actor.setLMuerte(JOptionPane.showInputDialog("Ingrese el lugar de fallecimiento: ", actor.getLMuerte()));

            em.merge(actor);
        }
    }


    @Override
    public void eliminar(Integer id) {
        Actor actor = porId(id);
        em.remove(actor);
    }

    @Override
    public List<Actor> listaActorMuertoNacion(String nacionalidad) {
        TypedQuery<Actor> query = em.createQuery("SELECT a FROM Actor a WHERE a.nacionalidad = :nacionalidad AND a.fMuerte IS NOT NULL", Actor.class);
        query.setParameter("nacionalidad", nacionalidad);
        List<Actor> actoresFallecidos = query.getResultList();
        return actoresFallecidos;
    }

    @Override
    public List<Object[]> actorMayorParticipacion() {
       return null;
    }

}
