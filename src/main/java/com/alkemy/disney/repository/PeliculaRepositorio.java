package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Pelicula;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kharon
 */
@Repository
public interface PeliculaRepositorio extends CrudRepository<Pelicula, String> {

    public Iterable<Object[]> findByTitulo(String titulo);

    @Override
    public Optional<Pelicula> findById(String id);
    
    @Query(value = "SELECT titulo, linkImagen, fechaCreacion FROM Pelicula",nativeQuery = true)
    public Iterable<Object[]> getAll();

    @Query(value = "SELECT titulo,linkImagen,fechaCreacion FROM Pelicula ORDER BY fechaCreacion ASC",nativeQuery = true)
    public Iterable<Object[]> getAllByOrderASC();

    @Query(value = "SELECT titulo, linkImagen, fechaCreacion FROM Pelicula ORDER BY fechaCreacion DESC",nativeQuery = true)
    public Iterable<Object[]> getAllByOrderDESC();
    
    public Pelicula getById(String peliculaId);
}
