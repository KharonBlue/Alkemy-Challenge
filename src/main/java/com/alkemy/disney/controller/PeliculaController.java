
package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Pelicula;
import com.alkemy.disney.service.ServicioGenero;
import com.alkemy.disney.service.ServicioPeliculas;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Kharon
 */
@RequestMapping("/movies")
@RestController
public class PeliculaController {

    
    @Autowired
    private ServicioPeliculas servicioPeliculas;
    @Autowired
    private ServicioGenero servicioGenero; 


    @GetMapping()
    public Iterable<Object[]> getAll(){
        return servicioPeliculas.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> findById(@PathVariable("id") String id){
        return servicioPeliculas.findById(id);
    }
    
    @GetMapping(params="name")
    public Iterable<Object[]> findByTitle(@RequestParam("name") String title){
        return servicioPeliculas.findByTitulo(title);
    }
    @GetMapping(params="order")
    public Iterable<Object[]> getByOrder(@RequestParam("order") String order){
        return servicioPeliculas.getByOrder(order);
    }
    @GetMapping(value = "", params="genreId")
    public List<Pelicula> getByGenre(@RequestParam("genreId") String generoId){
        return servicioGenero.getMoviesByGenreId(generoId);
    }
    
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") String id){
        try {
            servicioPeliculas.delete(id);
            return "Pelicula borrada: " + id;
        } catch (Exception e) {
            return "No es posible eliminar la pelicula: " + id;
        }
    }

    @PostMapping("save")
    public Pelicula save(@RequestParam("file") MultipartFile image, @ModelAttribute Pelicula pelicula){
        if(!image.isEmpty()){
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                pelicula.setLinkImagen(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return servicioPeliculas.guardar(pelicula);
    }
}
