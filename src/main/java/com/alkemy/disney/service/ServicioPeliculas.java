
package com.alkemy.disney.service;

import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.repository.PeliculaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kharon
 */
@Service
public class ServicioPeliculas {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    //Buscar todas las peliculas
    public Iterable<Pelicula> findAll(){
        return peliculaRepositorio.findAll();
    }
    //Traer a todas
    public Iterable<Object[]> getAll(){
        return peliculaRepositorio.getAll();
    }

    //Buscar por id
    public Optional<Pelicula> findById(String id){
        return peliculaRepositorio.findById(id);
    }
    //Guardar
    public Pelicula guardar(Pelicula pelicula)
    {
        return peliculaRepositorio.save(pelicula);
    }
    
    //Buscar por titulo
    public Iterable<Object[]> findByTitulo(String titulo){
        return peliculaRepositorio.findByTitulo(titulo);
    }

    //Trae en orden segun lo que elija el usuario
    public Iterable<Object[]> getByOrder(String order){
        if(order.equals("ASC")){
            return peliculaRepositorio.getAllByOrderASC();            
        }else if(order.equals("DESC")){
            return peliculaRepositorio.getAllByOrderDESC();  
        }else{
            return peliculaRepositorio.getAll();
        }
    }
    //Borrar por id
    public boolean delete(String id){
        try{
            peliculaRepositorio.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
        
    }
}
