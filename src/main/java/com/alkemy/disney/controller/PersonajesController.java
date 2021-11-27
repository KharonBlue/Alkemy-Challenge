
package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Personaje;
import com.alkemy.disney.service.ServicioPersonaje;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@RestController
@RequestMapping("/characters")
public class PersonajesController {

    @Autowired
    private ServicioPersonaje servicioPersonaje;

    @GetMapping()
    public Iterable<Object[]> obtenerTodos(){
        return servicioPersonaje.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Personaje> buscarPorId(@PathVariable("id") String personajeId){
        return servicioPersonaje.findById(personajeId);
    }
    
    @GetMapping(params="name")
    public Iterable<Object[]> buscarPorNombre(@RequestParam("name") String name){
        return servicioPersonaje.findByName(name);
    }
    
    @GetMapping(params="age")
    public Iterable<Object[]> buscarPorEdad(@RequestParam("age") Integer age){
        return servicioPersonaje.findByAge(age);
    }
    @DeleteMapping(path = "delete/{id}")
    public String eliminar(@PathVariable("id") String id){
        try {
            servicioPersonaje.delete(id);
            return "Personaje eliminado correctamente: " + id;
        } catch (Exception e) {
            return "No es posible eliminar el persojaje: " + id;
        }
    }
    
    @PostMapping("save")
    public Personaje guardar(@RequestParam("file") MultipartFile image, @ModelAttribute Personaje personaje){
        if(!image.isEmpty()){
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                personaje.setImagenLink(image.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return servicioPersonaje.save(personaje);
    }
}
