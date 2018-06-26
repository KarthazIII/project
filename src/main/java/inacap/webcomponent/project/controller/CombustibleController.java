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
import inacap.webcomponent.project.model.CombustibleModel;
import inacap.webcomponent.project.repository.combustibleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/combustible")
public class CombustibleController {
    
    @Autowired
    private combustibleRepository combustibleRepository;
    
    @GetMapping()
    public Iterable<CombustibleModel> listarTodos() {
        return combustibleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <CombustibleModel> MuestraVehiculos (@PathVariable String id) {
        
        Optional<CombustibleModel> combustibleOptional = combustibleRepository.findById(Integer.parseInt(id));
        
        if (combustibleOptional.isPresent()) {
            CombustibleModel combustibleEncontrado = combustibleOptional.get();
            return new ResponseEntity<>(combustibleEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CombustibleModel> editaCombustible (@PathVariable String id, @RequestBody CombustibleModel combustibleEditar) {
        
        Optional<CombustibleModel> combustibleOptional = combustibleRepository.findById(Integer.parseInt(id));
        
        if (combustibleOptional.isPresent()) {
            CombustibleModel combustibleEncontrado = combustibleOptional.get();
            
            combustibleEditar.setIdCombustible(combustibleEncontrado.getIdCombustible());
            
            combustibleRepository.save(combustibleEditar);
            
            return new ResponseEntity<>(combustibleEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody CombustibleModel nuevoCombustible) {
        
        nuevoCombustible= combustibleRepository.save(nuevoCombustible);
        
        Optional<CombustibleModel> combustibleOptional = combustibleRepository.findById(nuevoCombustible.getIdCombustible());
        
        if (combustibleOptional.isPresent()) {
            CombustibleModel combustibleEncontrado = combustibleOptional.get();
            return new ResponseEntity<>(combustibleEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<CombustibleModel> combustibleOptional = combustibleRepository.findById(Integer.parseInt(id));
        
        if (combustibleOptional.isPresent()) {
            CombustibleModel combustibleEncontrado = combustibleOptional.get();
            combustibleRepository.deleteById(combustibleEncontrado.getIdCombustible());
            return new ResponseEntity<>(combustibleEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
}
