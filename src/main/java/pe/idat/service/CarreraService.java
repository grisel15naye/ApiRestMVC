package pe.idat.service;

import pe.idat.model.Carrera;

import java.util.Collection;

public interface CarreraService {
    public abstract void  insert(Carrera carrera);
    public abstract void update(Carrera carrera);
    public abstract void delete(Long carreraId);
    public abstract Carrera findByid(Long carreraId);
    public abstract Collection<Carrera>findAll();

}
