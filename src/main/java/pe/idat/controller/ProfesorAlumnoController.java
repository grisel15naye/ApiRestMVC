package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Alumno;
import pe.idat.model.AlumnoMateria;
import pe.idat.model.Materia;
import pe.idat.model.Profesor;
import pe.idat.service.AlumnoService;
import pe.idat.service.MateriaService;
import pe.idat.service.ProfesorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("profesor_alumno")
public class ProfesorAlumnoController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumno/{profesorId}")
    public ResponseEntity<?> listarAalumnoPorProfesor(@PathVariable Long profesorId ){
        Profesor profesor =profesorService.findById(profesorId);
        if (profesor!=null){
            return new ResponseEntity<>(profesor.getItemsAlumno(), HttpStatus.OK);
        }return new ResponseEntity<>("Profesor no encotrado", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/profesor/{alumnoId}")
    public ResponseEntity<?> listarProfesorPorAlumnos(@PathVariable Long alumnoId){
        Alumno alumno=alumnoService.findById(alumnoId);
        if (alumno!=null){
            return new ResponseEntity<>(alumno.getItemsProfesor(),HttpStatus.OK);
        }return new ResponseEntity<>("Alumno no econtrado no encontrada",HttpStatus.NOT_FOUND);
    }
    @PostMapping("/agregar")
    public ResponseEntity<?> inscribirAlumnoDelProfesor(@RequestBody Map<String, Long> payload){
        Long alumnoId = payload.get("alumnoId");
        Long profesorId = payload.get("profesorId");

        if (alumnoId == null || profesorId == null) {
            return new ResponseEntity<>("Faltan alumnoId o profesorId", HttpStatus.BAD_REQUEST);
        }

        Alumno alumno = alumnoService.findById(alumnoId);
        Profesor profesor = profesorService.findById(profesorId);

        if (alumno == null) {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
        }
        if (profesor == null) {
            return new ResponseEntity<>("Materia no encontrda", HttpStatus.NOT_FOUND);
        }

        if (profesor.getItemsAlumno().contains(alumno)) {
            return new ResponseEntity<>("Alumno ya inscrito en esta amteria", HttpStatus.CONFLICT);
        }

        profesor.getItemsAlumno().add(alumno);
        profesorService.update(profesor);
        return new ResponseEntity<>("Inscripcion Realizada Correctamente", HttpStatus.CREATED);
    }

    // Desinscribir alumno de materia
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarAlumnoDeProfesor(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long profesorId = payload.get("profesorId");

        if (alumnoId == null || profesorId == null) {
            return new ResponseEntity<>("Faltan alumnoId o profesorId", HttpStatus.BAD_REQUEST);
        }

        Profesor profesor = profesorService.findById(profesorId);
        Alumno alumno = alumnoService.findById(alumnoId);

        if (alumno == null || profesor == null) {
            return new ResponseEntity<>("Alumno o Profesor no encontrada",HttpStatus.NOT_FOUND);
        }

        if (!profesor.getItemsAlumno().contains(alumno)) {
            return new ResponseEntity<>("el docente no enseña a este alumno",HttpStatus.NOT_FOUND);

        }

        profesor.getItemsAlumno().remove(alumno);
        profesorService.update(profesor);
        return new  ResponseEntity<>("Desinscripción realizada correctamente", HttpStatus.OK);
    }

    // Verificar inscripción
    @GetMapping("/verificar/{profesorId}/{alumnoId}")
    public ResponseEntity<?> verificarInscripcion(@PathVariable Long profesorId, @PathVariable Long alumnoId) {
        Profesor profesor = profesorService.findById(profesorId);
        Alumno alumno = alumnoService.findById(alumnoId);

        if (alumno == null || profesor == null) {
            return new ResponseEntity<>("Alumno o Profesor no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Profesor: "+profesor.getNombreP()+" enseña a la alumna: " + alumno.getNombre(), HttpStatus.OK);
    }
}