
package com.alkemy.disney.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Kharon
 */
@Entity
@Data
@Table(name="Genero")
public class Genero{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="Id_Genero")
    private String id;
    @Column(name="Nombre")
    private String nombre;
    @Column(name="GeneroImagen")
    private String imagen;
    @OneToMany(mappedBy = "genero")
    private List<Pelicula> pelicula;

    
}
