package integrador.app.dtos;

import lombok.Data;

@Data
public class AlumnoCarreraDTO {
    private Long id_alumno;
    private Long id_carrera;
    private Long inscripcion;
    private Long graduacion;
    private Long antiguedad;
}
