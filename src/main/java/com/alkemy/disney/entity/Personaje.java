package com.alkemy.disney.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Kharon
 */
@Entity
@Data
@Table(name = "Personaje")
public class Personaje {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id_Personaje")
    private String id;
    @Column(name="PersonajeImagen")
    private String imagenLink;
    @Column(name="Nombre")
    private String nombre;
    @Column(name="Edad")
    private Integer edad;
    @Column(name="Peso")
    private Double peso;
    @Column(name="Historia")
    private String historia;
    @ManyToOne
    @JoinColumn(name="PeliculaId")
    private Pelicula pelicula;

    
    

}
