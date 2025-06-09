package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.idat.model.Carrera;
@Repository

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}