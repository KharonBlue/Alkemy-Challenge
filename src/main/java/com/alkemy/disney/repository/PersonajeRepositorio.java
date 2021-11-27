package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kharon
 */
@Repository
public interface PersonajeRepositorio extends CrudRepository<Personaje, String> {

    public Iterable<Object[]> findByNombre(String nombre);

    public Iterable<Object[]> findByEdad(Integer edad);

    @Query(value = "SELECT imagenLink, nombre FROM Personaje;" ,nativeQuery = true)
    public Iterable<Object[]> getAll();

    public List<Object[]> findByPelicula(String pelicula);
}
