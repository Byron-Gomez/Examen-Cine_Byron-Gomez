package org.gomez.repository;

import jakarta.persistence.EntityManager;
import org.gomez.entity.Genero;

import javax.swing.*;
import java.util.List;

public class GeneroRepository implements CrudRepository<Genero>{
    // declaracion de atributos y instanciacion con EntityManager

    private final EntityManager em;

    public GeneroRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genero> listar() {
        return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
    }

    @Override
    public Genero porId(Integer id) {
        return em.find(Genero.class,id);
    }

    @Override
    public void crear(Genero genero) {
        if (genero.getId()!=null && genero.getId()>0){
            em.merge(genero);
        }else{
            em.persist(genero);
        }
    }

    @Override
    public void editar(Integer id) {
        Genero genero = porId(id);
        if (genero.getId() != null && genero.getId() > 0) {
            genero.setNombre(JOptionPane.showInputDialog("Ingrese Género de película: ", genero.getNombre()));
            em.merge(genero);
        }
    }


    @Override
    public void eliminar(Integer id) {
        Genero genero = porId(id);
        em.remove(genero);
    }
}
