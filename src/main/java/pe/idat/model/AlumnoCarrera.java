package pe.idat.model;

public class AlumnoCarrera {
    private Alumno alumno;
    private Carrera carrera;

    public AlumnoCarrera(){}

    public AlumnoCarrera(Alumno alumno,Carrera carrera) {
        this.alumno = alumno;
        this.carrera = carrera;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
