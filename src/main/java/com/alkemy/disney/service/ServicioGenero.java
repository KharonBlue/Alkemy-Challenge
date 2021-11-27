
package com.alkemy.disney.service;

import com.alkemy.disney.entity.Genero;
import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.repository.GeneroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kharon
 */
@Service
public class ServicioGenero {

    @Autowired
    private GeneroRepositorio generoRepositorio;
    
    //Guardar genero
    public Genero guardar(Genero genero){
        return generoRepositorio.save(genero);       
    }
    //Borrar genero por id
    public boolean borrar(String id){
        try{
            generoRepositorio.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
        
    }
    //Buscar por id y retorna un optional para validar si el resultado esta presente
    public Optional<Genero> findById(String generoId) {
        return generoRepositorio.findById(generoId);
    }
    
    //Traigo una lista Id de las peliculas que posee
    public List<Pelicula> getMoviesByGenreId(String generoId) {
        Genero genero = generoRepositorio.getById(generoId);
        if(genero != null){ //valido si el genero esta vacio y si no traigo la lista de idPeliculas que posee
            List<Pelicula> peliculas = genero.getPelicula();   
            return peliculas;
        }else{
            return null;
        }

    }
}
