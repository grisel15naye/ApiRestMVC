package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Alumno;
import pe.idat.model.Carrera;
import pe.idat.model.Materia;
import pe.idat.service.AlumnoService;
import pe.idat.service.CarreraService;

import java.util.Map;

@RestController
@RequestMapping("/alumno_carrera")
public class AlumnoCarreraContorller {
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CarreraService carreraService;

    //listar carrera de un alumno
    @GetMapping("/carreras/{alumnoId}")
    public ResponseEntity<?> listarCarreraPorAlumno(@PathVariable Long alumnoId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        if (alumno != null) {
            return new ResponseEntity<>(alumno.getItemsCarrera(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Alumno no encotrado", HttpStatus.NOT_FOUND);


    }

    @GetMapping("/alumnos/{carreraId}")
    public ResponseEntity<?> listarlumnoPorCarrera(@PathVariable Long carreraId) {
        Carrera carrera = carreraService.findByid(carreraId);
        if (carrera != null) {
            return new ResponseEntity<>(carrera.getItemsAlumno(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Carrera no encontrada", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/inscribir")
    public ResponseEntity<?> InscribirAlumno(@RequestBody Map<String, Long> paylod) {
        Long alumnoId = paylod.get("alumnoId");
        Long carreraId = paylod.get("carreraId");

        if (alumnoId == null || carreraId == null) {
            return new ResponseEntity<>("Faltan Alumnoid y CarreraId", HttpStatus.BAD_REQUEST);
        }
        Alumno alumno = alumnoService.findById(alumnoId);
        Carrera carrera = carreraService.findByid(carreraId);

        if (alumno == null) {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);

        }
        if (carrera == null) {
            return new ResponseEntity<>("Carrera no encontrado", HttpStatus.NOT_FOUND);
        }
        if (alumno.getItemsCarrera().contains(carrera)) {
            return new ResponseEntity<>("Alumno ya inscrito en esta Carrera", HttpStatus.CONFLICT);
        }
        alumno.getItemsCarrera().add(carrera);
        alumnoService.update(alumno);
        return new ResponseEntity<>("Inscripcion realizada correctamente", HttpStatus.CREATED);

    }

    // Desinscribir alumno de carera
    @DeleteMapping("/desinscribir")
    public ResponseEntity<?> desinscribirAlumno(@RequestBody Map<String, Long> payload) {
        Long alumnoId = payload.get("alumnoId");
        Long carreraId = payload.get("carreraId");

        if (alumnoId == null || carreraId == null) {
            return new ResponseEntity<>("Faltan alumnoId o CarreraId", HttpStatus.BAD_REQUEST);
        }
        Alumno alumno = alumnoService.findById(alumnoId);
        Carrera carrera = carreraService.findByid(carreraId);

        if (alumno == null || carrera == null) {
            return new ResponseEntity<>("Alumno o Carrera no encontrada", HttpStatus.NOT_FOUND);

        }
        if (!alumno.getItemsCarrera().contains(carrera)) {
            return new ResponseEntity<>("Alumno no está inscrito en esta carrera", HttpStatus.NOT_FOUND);


        }
        alumno.getItemsCarrera().remove(carrera);
        alumnoService.update(alumno);
        return new ResponseEntity<>("Desinscripción realizada correctamente", HttpStatus.OK);

    }
    @GetMapping("/verificar/{alumnoId}/{carreraId}")
    public ResponseEntity<?> verificarInscripcion(@PathVariable Long alumnoId, @PathVariable Long carreraId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        Carrera carrera = carreraService.findByid(carreraId);

        if (alumno == null || carrera == null) {
            return new ResponseEntity<>("Alumno o Carrera no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("alumno: "+alumno.getNombre()+" Inscrito en la carrera: " + carrera.getDescC(), HttpStatus.OK);
    }
}






