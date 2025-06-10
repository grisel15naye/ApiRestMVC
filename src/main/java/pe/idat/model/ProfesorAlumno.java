package pe.idat.model;

public class ProfesorAlumno {
    private Profesor profesor;
    private Alumno alumno;

    public ProfesorAlumno(){}

    public ProfesorAlumno(Profesor profesor, Alumno alumno) {
        this.profesor = profesor;
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
