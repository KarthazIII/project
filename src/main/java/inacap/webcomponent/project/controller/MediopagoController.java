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
import inacap.webcomponent.project.model.MediopagoModel;
import inacap.webcomponent.project.repository.MediopagoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/pagos")
public class MediopagoController {
    
    @Autowired
    private MediopagoRepository mediopagoRepository;
    
    @GetMapping()
    public Iterable<MediopagoModel> listarTodos() {
        return mediopagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <MediopagoModel> MuestraMediopago (@PathVariable String id) {
        
        Optional<MediopagoModel> mediopagoOptional = mediopagoRepository.findById(Integer.parseInt(id));
        
        if (mediopagoOptional.isPresent()) {
            MediopagoModel mediopagoEncontrado = mediopagoOptional.get();
            return new ResponseEntity<>(mediopagoEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MediopagoModel> editaMediopago (@PathVariable String id, @RequestBody MediopagoModel mediopagoEditar) {
        
        Optional<MediopagoModel> mediopagoOptional = mediopagoRepository.findById(Integer.parseInt(id));
        
        if (mediopagoOptional.isPresent()) {
            MediopagoModel mediopagoEncontrado = mediopagoOptional.get();
            
            mediopagoEditar.setIdMediopago(mediopagoEncontrado.getIdMediopago());
            
            mediopagoRepository.save(mediopagoEditar);
            
            return new ResponseEntity<>(mediopagoEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MediopagoModel nuevoMediopago) {
        
        nuevoMediopago= mediopagoRepository.save(nuevoMediopago);
        
        Optional<MediopagoModel> mediopagoOptional = mediopagoRepository.findById(nuevoMediopago.getIdMediopago());
        
        if (mediopagoOptional.isPresent()) {
            MediopagoModel mediopagoEncontrado = mediopagoOptional.get();
            return new ResponseEntity<>(mediopagoEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<MediopagoModel> mediopagoOptional = mediopagoRepository.findById(Integer.parseInt(id));
        
        if (mediopagoOptional.isPresent()) {
            MediopagoModel mediopagoEncontrado = mediopagoOptional.get();
            mediopagoRepository.deleteById(mediopagoEncontrado.getIdMediopago());
            return new ResponseEntity<>(mediopagoEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
    
}
