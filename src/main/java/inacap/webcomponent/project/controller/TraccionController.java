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
import inacap.webcomponent.project.model.TraccionModel;
import inacap.webcomponent.project.repository.traccionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/traccion")
public class TraccionController {
    
    @Autowired
    private traccionRepository traccionRepository;
    
    @GetMapping()
    public Iterable<TraccionModel> listarTodos() {
        return traccionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <TraccionModel> MuestraTraccion (@PathVariable String id) {
        
        Optional<TraccionModel> traccionOptional = traccionRepository.findById(Integer.parseInt(id));
        
        if (traccionOptional.isPresent()) {
            TraccionModel traccionEncontrado = traccionOptional.get();
            return new ResponseEntity<>(traccionEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TraccionModel> editaTraccion(@PathVariable String id, @RequestBody TraccionModel traccionEditar) {
        
        Optional<TraccionModel> traccionOptional = traccionRepository.findById(Integer.parseInt(id));
        
        if (traccionOptional.isPresent()) {
            TraccionModel traccionEncontrado = traccionOptional.get();
            
            traccionEditar.setIdTraccion(traccionEncontrado.getIdTraccion());
            
            traccionRepository.save(traccionEditar);
            
            return new ResponseEntity<>(traccionEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TraccionModel nuevoTraccion) {
        
        nuevoTraccion= traccionRepository.save(nuevoTraccion);
        
        Optional<TraccionModel> traccionOptional = traccionRepository.findById(nuevoTraccion.getIdTraccion());
        
        if (traccionOptional.isPresent()) {
            TraccionModel traccionEncontrado = traccionOptional.get();
            return new ResponseEntity<>(traccionEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<TraccionModel> traccionOptional = traccionRepository.findById(Integer.parseInt(id));
        
        if (traccionOptional.isPresent()) {
            TraccionModel traccionEncontrado = traccionOptional.get();
            traccionRepository.deleteById(traccionEncontrado.getIdTraccion());
            return new ResponseEntity<>(traccionEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
