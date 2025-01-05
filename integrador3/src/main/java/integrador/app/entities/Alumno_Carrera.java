package integrador.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
public class Alumno_Carrera implements Serializable{

    @EmbeddedId
    private Alumno_Carrera_Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alumno_id")
    @JoinColumn(name ="id_alumno", referencedColumnName = "alumno_id")
    @JsonIgnore
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carrera_id")
    @JoinColumn(name="id_carrera", referencedColumnName = "carrera_id")
    @JsonIgnore
    private Carrera carrera;

    @Column(nullable = false)
    private Long inscripcion;
    @Column
    private Long graduacion;
    @Column
    private Long antiguedad;

    public Alumno_Carrera(Alumno alumno, Carrera carrera, Long inscripcion, Long graduacion, Long antiguedad) {
        this.id = new Alumno_Carrera_Id();
        this.alumno = alumno;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

    public Alumno_Carrera() {
    }

    @Override
    public String toString() {
        return "Alumno_Carrera{" +
                "id=" + id +
                ", alumno=" + alumno.getAlumno_id() +
                ", carrera=" + carrera.getCarrera_id() +
                ", inscripcion=" + inscripcion +
                ", graduacion=" + graduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
