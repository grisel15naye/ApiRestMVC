package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.idat.model.Profesor;
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}