package pe.idat.service;

import pe.idat.model.Alumno;

import java.util.Collection;

public interface AlumnoService {
    public abstract  void insert (Alumno alumno);
    public  abstract void update (Alumno alumno);
    public abstract void delete(Long alumnoId);
    public abstract Alumno findById(Long alumnoId);
    public abstract Collection<Alumno>findAll();
}
