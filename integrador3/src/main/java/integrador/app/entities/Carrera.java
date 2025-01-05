package integrador.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carrera_id;
    @Column
    private String nombre;
    @Column
    private int anios;
    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Alumno_Carrera> alumnos;

    public Carrera(String nombre, int anios) {
        super();
        this.nombre = nombre;
        this.anios = anios;
        this.alumnos = new ArrayList<>();
    }

    public Carrera() {

    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id: " + this.carrera_id +
                ", nombre: '" + nombre + '\'' +
                '}';
    }
}
