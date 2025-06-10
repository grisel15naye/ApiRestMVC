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
@RequestMapping("/alumno_materia")
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
            return new ResponseEntity<>(alumno.getItemsMateria(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Alumno no encontrado",HttpStatus.NOT_FOUND);
    }

    // Listar alumnos de una materia
    @GetMapping("/alumnos/{materiaId}")
    public ResponseEntity<?> listarAlumnosPorMateria(@PathVariable Long materiaId) {
        Materia materia = materiaService.findById(materiaId);
        if (materia != null) {
            return new ResponseEntity<>(materia.getItemsAlumno(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Materia no encontrada",HttpStatus.NOT_FOUND);
    }

    // Inscribir alumno en materia
    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribirAlumno(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long materiaId = payload.get("materiaId");

        if (alumnoId == null || materiaId == null) {
            return new ResponseEntity<>("Faltan alumnoId o materiaId", HttpStatus.BAD_REQUEST);
        }

        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null) {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
        }
        if (materia == null) {
            return new ResponseEntity<>("Materia no encontrda", HttpStatus.NOT_FOUND);
        }

        if (alumno.getItemsMateria().contains(materia)) {
            return new ResponseEntity<>("Alumno y niscrito en esta amteria", HttpStatus.CONFLICT);
        }

        alumno.getItemsMateria().add(materia);
        alumnoService.update(alumno);
        return new ResponseEntity<>("Inscripcion Realizada Correctamente", HttpStatus.CREATED);
    }

    // 5. INSCRIBIR ALUMNO EN MÚLTIPLES MATERIAS
    @PostMapping("/inscribir_multiple/{alumnoId}")
    public ResponseEntity<?>inscribirAlumnoEnMultiplesMaterias(@PathVariable Long alumnoId,
                                                               @RequestBody List<Long>materiaIds){
        try {
            Alumno alumno = alumnoService.findById(alumnoId);
            if (alumno == null) {
                return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
            }

            List<String> materiasInscritas = new ArrayList<>();
            List<String> errores = new ArrayList<>();

            for (Long materiaId : materiaIds) {
                Materia materia = materiaService.findById(materiaId);
                if (materia == null) {
                    errores.add("Materia con ID " + materiaId + " no encontrada");
                    continue;
                }

                if (alumno.getItemsMateria().contains(materia)) {
                    errores.add("Ya inscrito en " + materia.getDescC());
                    continue;
                }

                alumno.getItemsMateria().add(materia);
                materiasInscritas.add(materia.getDescC());
            }

            alumnoService.update(alumno);

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("materiasInscritas", materiasInscritas);
            resultado.put("errores", errores);

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al inscribir en múltiples materias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Desinscribir alumno de materia
    @DeleteMapping("/desinscribir")
    public ResponseEntity<?> desinscribirAlumno(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long materiaId = payload.get("materiaId");

        if (alumnoId == null || materiaId == null) {
            return new ResponseEntity<>("Faltan alumnoId o materiaId", HttpStatus.BAD_REQUEST);
        }

        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null || materia == null) {
            return new ResponseEntity<>("Alumno o Materia no encontrada",HttpStatus.NOT_FOUND);
        }

        if (!alumno.getItemsMateria().contains(materia)) {
            return new ResponseEntity<>("Alumno no está inscrito en esta materia",HttpStatus.NOT_FOUND);

        }

        alumno.getItemsMateria().remove(materia);
        alumnoService.update(alumno);
        return new  ResponseEntity<>("Desinscripción realizada correctamente", HttpStatus.OK);
    }

    // Verificar inscripción
    @GetMapping("/verificar/{alumnoId}/{materiaId}")
    public ResponseEntity<?> verificarInscripcion(@PathVariable Long alumnoId, @PathVariable Long materiaId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        Materia materia = materiaService.findById(materiaId);

        if (alumno == null || materia == null) {
            return new ResponseEntity<>("Alumno o materia no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("alumno: "+alumno.getNombre()+" Inscrito: " + materia.getDescC(), HttpStatus.OK);
    }
}