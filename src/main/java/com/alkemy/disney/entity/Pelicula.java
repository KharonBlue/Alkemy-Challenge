
package com.alkemy.disney.entity;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Kharon
 */
@Entity
@Table(name="Pelicula")
@Data
public class Pelicula {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="Id_Pelicula")
    private String id;
    @Column(name="LinkImagen")
    private String linkImagen;
    @Column(name="Titulo")
    private String titulo;
    
    @Temporal(TemporalType.DATE)
    @Column(name="Fecha_Creacion")
    private Date fechaCreacion;
    @Column(name="Calificacion")
    private Integer calificacion;
    
    @ManyToOne
    @JoinColumn(name="GeneroId")
    private Genero genero;
    
    @OneToMany(mappedBy = "pelicula")
    private List<Personaje> personajesAsociados;

}
