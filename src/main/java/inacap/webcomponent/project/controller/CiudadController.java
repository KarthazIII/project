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
import inacap.webcomponent.project.model.CiudadModel;
import inacap.webcomponent.project.repository.ciudadRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/ciudad")
public class CiudadController {
    
    @Autowired
    private ciudadRepository ciudadRepository;
    
    @GetMapping()
    public Iterable<CiudadModel> listarTodos() {
        return ciudadRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <CiudadModel> MuestraCiudad (@PathVariable String id) {
        
        Optional<CiudadModel> ciudadOptional = ciudadRepository.findById(Integer.parseInt(id));
        
        if (ciudadOptional.isPresent()) {
            CiudadModel ciudadEncontrado = ciudadOptional.get();
            return new ResponseEntity<>(ciudadEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CiudadModel> editaCiudad(@PathVariable String id, @RequestBody CiudadModel ciudadEditar) {
        
        Optional<CiudadModel> ciudadOptional = ciudadRepository.findById(Integer.parseInt(id));
        
        if (ciudadOptional.isPresent()) {
            CiudadModel ciudadEncontrado = ciudadOptional.get();
            
            ciudadEditar.setIdCiudad(ciudadEncontrado.getIdCiudad());
            
            ciudadRepository.save(ciudadEditar);
            
            return new ResponseEntity<>(ciudadEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody CiudadModel nuevoCiudad) {
        
        nuevoCiudad= ciudadRepository.save(nuevoCiudad);
        
        Optional<CiudadModel> ciudadOptional = ciudadRepository.findById(nuevoCiudad.getIdCiudad());
        
        if (ciudadOptional.isPresent()) {
            CiudadModel ciudadEncontrado = ciudadOptional.get();
            return new ResponseEntity<>(ciudadEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<CiudadModel> ciudadOptional = ciudadRepository.findById(Integer.parseInt(id));
        
        if (ciudadOptional.isPresent()) {
            CiudadModel ciudadEncontrado = ciudadOptional.get();
            ciudadRepository.deleteById(ciudadEncontrado.getIdCiudad());
            return new ResponseEntity<>(ciudadEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
