package pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.model.Profesor;
import pe.idat.repository.ProfesorRepository;

import java.util.Collection;
import java.util.List;
@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    @Override
    public void insert(Profesor profesor) {
        profesorRepository.save(profesor);

    }

    @Override
    public void update(Profesor profesor) {
        profesorRepository.save(profesor);

    }

    @Override
    public void delete(Long profesorId) {
        profesorRepository.deleteById(profesorId);

    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findById(Long profesorId) {
        return profesorRepository.findById(profesorId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Profesor> findAll() {
        return (Collection<Profesor>) profesorRepository.findAll();
    }
}
