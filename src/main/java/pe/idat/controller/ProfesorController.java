package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Profesor;
import pe.idat.service.ProfesorService;

import java.util.Collection;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/listar")
    private ResponseEntity<?>listar(){
        Collection<Profesor> itemsProfesor=profesorService.findAll();
        return new ResponseEntity<>(itemsProfesor, HttpStatus.OK);

    }

    @GetMapping("/buscar/{profesorId}")
    private ResponseEntity<?>buscar(@PathVariable Long profesorId){
        Profesor profesor=profesorService.findById(profesorId);
        if (profesor!=null){
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        }return new ResponseEntity<>("profesor no encontrado", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/agregar")
    private ResponseEntity<?>agregar(@RequestBody Profesor profesor){
        profesorService.insert(profesor);
        return new ResponseEntity<>("Profesor "+profesor.getNombreP()+" agregado correctamente", HttpStatus.OK);
    }

    @PutMapping("editar/{profesorId}")
    private ResponseEntity<?>editar(@PathVariable Long profesorId,
                                    @RequestBody Profesor profesor){
        Profesor profesorExistente=profesorService.findById(profesorId);
        if (profesorExistente!=null){
            profesorExistente.setNombreP(profesor.getNombreP());
            profesorExistente.setDireccion(profesor.getDireccion());
            profesorExistente.setTelefono(profesor.getTelefono());
            profesorService.update(profesorExistente);
            return new ResponseEntity<>("Profesor "+profesor.getNombreP()+" actualizado correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);


    }
}
