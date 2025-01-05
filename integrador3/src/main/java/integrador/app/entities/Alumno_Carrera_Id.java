package integrador.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class Alumno_Carrera_Id implements Serializable {
    @Column
    private Long alumno_id;

    @Column
    private Long carrera_id;

    public Alumno_Carrera_Id() {

    }

    public Alumno_Carrera_Id(Long a_id, Long c_id) {
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
