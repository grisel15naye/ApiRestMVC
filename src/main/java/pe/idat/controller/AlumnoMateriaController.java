package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Alumno;
import pe.idat.model.Materia;
import pe.idat.service.AlumnoService;
import pe.idat.service.MateriaService;

import java.util.*;


@RestController
@RequestMapping("/alumno-materia")
public class AlumnoMateriaController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private MateriaService materiaService;

    // Listar materias de un alumno
    @GetMapping("/materias/{alumnoId}")
    public ResponseEntity<?> listarMateriasPorAlumno(@PathVariable Long alumnoId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        if (alumno != null) {
            return ResponseEntity.ok(alumno.getItemsMateria());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
    }

    // Listar alumnos de una materia
    @GetMapping("/alumnos/{materiaId}")
    public ResponseEntity<?> listarAlumnosPorMateria(@PathVariable Long materiaId) {
        Materia materia = materiaService.findById(materiaId);
        if (materia != null) {
            return ResponseEntity.ok(materia.getItemsAlumno());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
    }

    // Inscribir alumno en materia
    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribirAlumno(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long materiaId = payload.get("materiaId");

        if (alumnoId == null || materiaId == null) {
            return ResponseEntity.badRequest().body("Faltan alumnoId o materiaId");
        }

        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        }
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
        }

        if (alumno.getItemsMateria().contains(materia)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Alumno ya inscrito en esta materia");
        }

        alumno.getItemsMateria().add(materia);
        alumnoService.update(alumno);

        return ResponseEntity.status(HttpStatus.CREATED).body("Inscripción realizada correctamente");
    }

    // Desinscribir alumno de materia
    @DeleteMapping("/desinscribir")
    public ResponseEntity<?> desinscribirAlumno(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long materiaId = payload.get("materiaId");

        if (alumnoId == null || materiaId == null) {
            return ResponseEntity.badRequest().body("Faltan alumnoId o materiaId");
        }

        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null || materia == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno o Materia no encontrada");
        }

        if (!alumno.getItemsMateria().contains(materia)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no está inscrito en esta materia");
        }

        alumno.getItemsMateria().remove(materia);
        alumnoService.update(alumno);

        return ResponseEntity.ok("Desinscripción realizada correctamente");
    }

    // Verificar inscripción
    @GetMapping("/verificar/{alumnoId}/{materiaId}")
    public ResponseEntity<?> verificarInscripcion(@PathVariable Long alumnoId, @PathVariable Long materiaId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null || materia == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno o materia no encontrada");
        }

        return ResponseEntity.ok("alumno: "+alumno.getNombre()+" Inscrito: " + materia.getDescC());
    }
}