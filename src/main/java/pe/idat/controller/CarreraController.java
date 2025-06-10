package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.model.Carrera;
import pe.idat.service.CarreraService;

import java.util.Collection;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @GetMapping("/listar")
    public ResponseEntity<?>listar(){
        Collection<Carrera> itemsCarrera=carreraService.findAll();
        return new ResponseEntity<>(itemsCarrera, HttpStatus.OK);
    }
    @GetMapping("/buscar/{carreraId}")
    private ResponseEntity<?>buscar(@PathVariable Long carreraId){
        Carrera carrera=carreraService.findByid(carreraId);
        if (carrera!=null){
            return new ResponseEntity<>(carrera,HttpStatus.OK);
        }return new ResponseEntity<>("Carrera no encontrada", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/agregar")
    public ResponseEntity<?>agregar(@RequestBody Carrera carrera){
        carreraService.insert(carrera);
        return new ResponseEntity<>("Materia "+carrera.getDescC()+"agregada correctamente",HttpStatus.CREATED);
    }
    @PutMapping("/editar/{carreraId}")
    private ResponseEntity<?>editar(@PathVariable Long carreraId,
                                    @RequestBody Carrera carrera){
        Carrera carreraExistente=carreraService.findByid(carreraId);
        if (carreraExistente!=null){
            carreraExistente.setDescC(carrera.getDescC());
            carreraExistente.setDuracion(carrera.getDuracion());
            carreraService.update(carreraExistente);
            return new ResponseEntity<>("Carera "+carrera.getDescC()+" actualizada correctamente", HttpStatus.OK);
        }return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_FOUND);
    }


}
