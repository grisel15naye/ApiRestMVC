package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Materia;
import pe.idat.repository.MateriaRepository;
import pe.idat.service.MateriaService;

import java.util.Collection;

@RestController
@RequestMapping("/materia")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping("/listar")
    public ResponseEntity<?>listar(){
        Collection<Materia> itemsMateria=materiaService.findAll();
        return new ResponseEntity<>(itemsMateria, HttpStatus.OK);
    }
    @GetMapping("/buscar/{materiaId}")
    private ResponseEntity<?>bucar(@PathVariable Long materiaId){
        Materia materia=materiaService.findById(materiaId);
        if (materia!= null){
            return new ResponseEntity<>(materia, HttpStatus.OK);
        }return new ResponseEntity<>("Materia no encontrada",HttpStatus.NOT_FOUND);

    }
    @PostMapping("/agregar")
    public ResponseEntity<?>agregar (@RequestBody Materia materia){
        materiaService.insert(materia);
        return new ResponseEntity<>("Materia "+materia.getDescC()+" Agregado correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{materiaId}")
    public ResponseEntity<?>editar(@PathVariable Long materiaId,
                                   @RequestBody Materia materia){
        Materia materiaExistente=materiaService.findById(materiaId);
        if (materiaExistente!=null){
            materiaExistente.setDescC(materia.getDescC());
            materiaExistente.setCreditos(materia.getCreditos());
            materiaService.update(materiaExistente);
            return new ResponseEntity<>("Materia "+materia.getDescC()+" actualizado correctamente", HttpStatus.OK);
        }return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }

}
