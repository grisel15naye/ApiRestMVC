package pe.idat.service;

import pe.idat.model.Profesor;

import java.util.Collection;

public interface ProfesorService {
    public abstract void insert (Profesor profesor);
    public abstract  void  update(Profesor profesor);
    public abstract void delete (Long profesorId);
    public abstract Profesor findById(Long profesorId);
    public abstract Collection<Profesor>findAll();
}
