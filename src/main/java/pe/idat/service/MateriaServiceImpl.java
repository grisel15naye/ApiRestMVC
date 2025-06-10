package pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.model.Materia;
import pe.idat.repository.MateriaRepository;

import java.util.Collection;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public void insert(Materia materia) {
        materiaRepository.save(materia);

    }

    @Override
    public void update(Materia materia) {
        materiaRepository.save(materia);

    }

    @Override
    public void delete(Long materiaId) {
        materiaRepository.deleteById(materiaId);

    }

    @Override
    public Materia findById(Long materiaId) {
        return materiaRepository.findById(materiaId).orElse(null);
    }

    @Override
    public Collection<Materia> findAll() {
        return (Collection<Materia>) materiaRepository.findAll();
    }
}
