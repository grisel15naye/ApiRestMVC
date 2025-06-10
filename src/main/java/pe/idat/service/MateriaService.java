package pe.idat.service;

import pe.idat.model.Materia;

import java.util.Collection;

public interface MateriaService {
    public abstract void insert (Materia materia);
    public abstract void update (Materia materia);
    public abstract void  delete (Long materiaId);
    public abstract Materia findById(Long materiaId);
    public abstract Collection<Materia>findAll();

}
