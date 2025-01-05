package integrador.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long alumno_id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private int dni;
    @Column
    private String ciudad;
    @Column
    private int nro_libreta;
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Alumno_Carrera> carreras;

    public Alumno(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int nro_libreta) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.nro_libreta = nro_libreta;
        carreras = new ArrayList<>();
    }

    public Alumno() {
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "ciudad='" + ciudad + '\'' +
                ", dni=" + dni +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", LU='" + nro_libreta + '\'' +
                ", alumno_id=" + alumno_id +
                '}';
    }
}
