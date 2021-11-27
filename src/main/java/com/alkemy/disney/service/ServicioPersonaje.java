package com.alkemy.disney.service;

import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.repository.PersonajeRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kharon
 */
@Service
public class ServicioPersonaje {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    //Busca a todos
    public Iterable<Personaje> findAll() {
        return personajeRepositorio.findAll();
    }

    //trae todos
    public Iterable<Object[]> getAll() {
        return personajeRepositorio.getAll();
    }

    //Buscar por id del personaje
    public Optional<Personaje> findById(String personajeId) {
        return personajeRepositorio.findById(personajeId);
    }

    //Buscar por nombre
    public Iterable<Object[]> findByName(String nombre) {
        return personajeRepositorio.findByNombre(nombre);
    }

    //Buscar por edad
    public Iterable<Object[]> findByAge(Integer edad) {
        return personajeRepositorio.findByEdad(edad);
    }

    //borrar por id(retorna un true si se completa la operacion)
    public boolean delete(String id) {
        try {
            personajeRepositorio.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }

    }
    
    //Guardar personaje
    public Personaje save(Personaje personaje) {
        return personajeRepositorio.save(personaje);
    }
}
