package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Genero;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kharon
 */
@Repository
public interface GeneroRepositorio extends CrudRepository<Genero, String> {
    
    @Override
    public Optional<Genero> findById(String id);
    
    public Genero getById(String Id);
}
