/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponent.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import inacap.webcomponent.project.model.DevolucionModel;
import inacap.webcomponent.project.repository.devolucionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/devolucion")
public class DevolucionController {
    
   @Autowired
    private devolucionRepository devolucionRepository;
    
    @GetMapping()
    public Iterable<DevolucionModel> listarTodos() {
        return devolucionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <DevolucionModel> MuestraDevolucion(@PathVariable String id) {
        
        Optional<DevolucionModel> devolucionOptional = devolucionRepository.findById(Integer.parseInt(id));
        
        if (devolucionOptional.isPresent()) {
            DevolucionModel devolucionEncontrado = devolucionOptional.get();
            return new ResponseEntity<>(devolucionEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DevolucionModel> editaDevolucion (@PathVariable String id, @RequestBody DevolucionModel devolucionEditar) {
        
        Optional<DevolucionModel> devolucionOptional = devolucionRepository.findById(Integer.parseInt(id));
        
        if (devolucionOptional.isPresent()) {
            DevolucionModel devolucionEncontrado = devolucionOptional.get();
            
            devolucionEditar.setIdDevolucion(devolucionEncontrado.getIdDevolucion());
            
            devolucionRepository.save(devolucionEditar);
            
            return new ResponseEntity<>(devolucionEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody DevolucionModel nuevoDevolucion) {
        
        nuevoDevolucion= devolucionRepository.save(nuevoDevolucion);
        
        Optional<DevolucionModel> devolucionOptional = devolucionRepository.findById(nuevoDevolucion.getIdDevolucion());
        
        if (devolucionOptional.isPresent()) {
            DevolucionModel devolucionEncontrado = devolucionOptional.get();
            return new ResponseEntity<>(devolucionEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<DevolucionModel> devolucionOptional = devolucionRepository.findById(Integer.parseInt(id));
        
        if (devolucionOptional.isPresent()) {
            DevolucionModel devolucionEncontrado = devolucionOptional.get();
            devolucionRepository.deleteById(devolucionEncontrado.getIdDevolucion());
            return new ResponseEntity<>(devolucionEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
