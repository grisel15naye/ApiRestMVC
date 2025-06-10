package pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.model.Carrera;
import pe.idat.repository.CarreraRepository;

import java.util.Collection;
import java.util.List;
@Service
public class CarreraServiceImpl implements CarreraService{
    @Autowired
    private CarreraRepository carreraRepository;
    @Override
    public void insert(Carrera carrera) {
        carreraRepository.save(carrera);

    }

    @Override
    public void update(Carrera carrera) {
        carreraRepository.save(carrera);

    }

    @Override
    public void delete(Long carreraId) {
        carreraRepository.deleteById(carreraId);

    }

    @Override
    public Carrera findByid(Long carreraId) {
        return carreraRepository.findById(carreraId).orElse(null);
    }

    @Override
    public Collection<Carrera> findAll() {
        return (Collection<Carrera>) carreraRepository.findAll();
    }
}
