package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.idat.model.Alumno;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}