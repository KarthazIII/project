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
import inacap.webcomponent.project.model.PersonaModel;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import inacap.webcomponent.project.repository.personaRepository;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/Persona")
public class PersonaController {
    
   @Autowired
    private personaRepository personaRepository;
    
    @GetMapping()
    public Iterable<PersonaModel> listarTodos() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <PersonaModel> MuestraPersona (@PathVariable String id) {
        
        Optional<PersonaModel> personaOptional = personaRepository.findById(Integer.parseInt(id));
        
        if (personaOptional.isPresent()) {
            PersonaModel personaEncontrado = personaOptional.get();
            return new ResponseEntity<>(personaEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PersonaModel> editaPersona(@PathVariable String id, @RequestBody PersonaModel personaEditar) {
        
        Optional<PersonaModel> personaOptional = personaRepository.findById(Integer.parseInt(id));
        
        if (personaOptional.isPresent()) {
            PersonaModel personaEncontrado = personaOptional.get();
            
            personaEditar.setIdPersona(personaEncontrado.getIdPersona());
            
            personaRepository.save(personaEditar);
            
            return new ResponseEntity<>(personaEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody PersonaModel nuevoPersona) {
        
        nuevoPersona= personaRepository.save(nuevoPersona);
        
        Optional<PersonaModel> personaOptional = personaRepository.findById(nuevoPersona.getIdPersona());
        
        if (personaOptional.isPresent()) {
            PersonaModel personaEncontrado = personaOptional.get();
            return new ResponseEntity<>(personaEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<PersonaModel> personaOptional = personaRepository.findById(Integer.parseInt(id));
        
        if (personaOptional.isPresent()) {
            PersonaModel personaEncontrado = personaOptional.get();
            personaRepository.deleteById(personaEncontrado.getIdPersona());
            return new ResponseEntity<>(personaEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
