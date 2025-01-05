package main.java.org.example.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
public class Alumno_Carrera implements Serializable{

    @EmbeddedId
    private Alumno_Carrera_Id id;

    @ManyToOne (fetch = FetchType.LAZY)
    @MapsId("alumno_id")
    @JoinColumn(name ="id_alumno", referencedColumnName = "alumno_id")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carrera_id")
    @JoinColumn(name="id_carrera", referencedColumnName = "carrera_id")
    private Carrera carrera;

    @Column(nullable = false)
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;

    public Alumno_Carrera(Alumno alumno, Carrera carrera, int inscripcion, int graduacion, int antiguedad) {
        this.id = new Alumno_Carrera_Id();
        this.alumno = alumno;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

    public Alumno_Carrera() {
    }

}
