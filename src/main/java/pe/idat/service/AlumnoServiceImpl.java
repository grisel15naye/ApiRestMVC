package pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.model.Alumno;
import pe.idat.repository.AlumnoRepository;

import java.util.Collection;
import java.util.List;
@Service
public class AlumnoServiceImpl implements AlumnoService{
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public void insert(Alumno alumno) {
        alumnoRepository.save(alumno);

    }

    @Override
    public void update(Alumno alumno) {
        alumnoRepository.save(alumno);

    }

    @Override
    public void delete(Long alumnoId) {
        alumnoRepository.deleteById(alumnoId);

    }

    @Override
    @Transactional(readOnly = true)
    public Alumno findById(Long alumnoId) {
        return alumnoRepository.findById(alumnoId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Alumno> findAll() {
        return (Collection<Alumno>) alumnoRepository.findAll();
    }
}
