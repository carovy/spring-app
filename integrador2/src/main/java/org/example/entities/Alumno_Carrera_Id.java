package main.java.org.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class Alumno_Carrera_Id implements java.io.Serializable {
    private static final long serialVersionUID = 3255599046800011076L;

    @Column
    private int alumno_id;

    @Column
    private int carrera_id;

    public Alumno_Carrera_Id() {

    }

    public Alumno_Carrera_Id(int a_id, int c_id) {
        alumno_id = a_id;
        carrera_id = c_id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Alumno_Carrera_Id that = (Alumno_Carrera_Id) o;
        return alumno_id == that.alumno_id && carrera_id == that.carrera_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno_id, carrera_id);
    }

    @Override
    public String toString() {
        return "Alumno_Carrera_Id{" +
                "alumno_id=" + alumno_id +
                ", carrera_id=" + carrera_id +
                '}';
    }
}
