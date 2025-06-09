package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.idat.model.Materia;
@Repository

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}