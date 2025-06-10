package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Alumno;
import pe.idat.service.AlumnoService;

import java.util.Collection;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        Collection<Alumno>itemsAlumno=alumnoService.findAll();
        return new ResponseEntity<>(itemsAlumno, HttpStatus.OK);
    }

    @GetMapping("/buscar/{alumnoId}")
    public ResponseEntity<?> buscar(@PathVariable Long alumnoId){
        Alumno alumno=alumnoService.findById(alumnoId);
        if (alumno!=null){
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        }return new ResponseEntity<>("alumno no encontrado", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/agregar")
    public ResponseEntity<?>agregar(@RequestBody Alumno alumno){
        {
            try {
                alumnoService.insert(alumno);
                return new ResponseEntity<>("Instructor"+alumno.getNombre()+" creado correctamente", HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>("error al agregar", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PutMapping("/editar/{alumnoId}")
    public ResponseEntity<?>editar (@PathVariable Long alumnoId,
                                    @RequestBody Alumno alumno){
        Alumno alumnoExistente=alumnoService.findById(alumnoId);
        if (alumnoExistente!=null){
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setEdad(alumno.getEdad());
            alumnoExistente.setSemestre(alumno.getSemestre());
            alumnoExistente.setGenero(alumno.getGenero());
            alumnoService.update(alumnoExistente);
            return new ResponseEntity<>("Alumno "+alumnoExistente.getNombre()+" actualizado correctamente", HttpStatus.OK);
        }return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/borrar/{alumnoId}")
    public ResponseEntity<?> borrar(@PathVariable Long alumnoId)
    {
        Alumno instructor =alumnoService.findById(alumnoId);
        if (instructor!=null)
        {
            alumnoService.delete(alumnoId);
            return new ResponseEntity<>("Alumno Eliminado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("no se pudo eliminar al Alumno", HttpStatus.NOT_FOUND);
    }
}
