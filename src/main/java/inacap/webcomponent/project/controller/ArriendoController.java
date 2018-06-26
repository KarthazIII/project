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
import inacap.webcomponent.project.model.ArriendoModel;
import inacap.webcomponent.project.repository.arriendoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/arriendo")
public class ArriendoController {
    
   @Autowired
    private arriendoRepository arriendoRepository;
    
    @GetMapping()
    public Iterable<ArriendoModel> listarTodos() {
        return arriendoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <ArriendoModel> MuestraArriendo (@PathVariable String id) {
        
        Optional<ArriendoModel> arriendoOptional = arriendoRepository.findById(Integer.parseInt(id));
        
        if (arriendoOptional.isPresent()) {
            ArriendoModel arriendoEncontrado = arriendoOptional.get();
            return new ResponseEntity<>(arriendoEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ArriendoModel> editaArriendo(@PathVariable String id, @RequestBody ArriendoModel arriendoEditar) {
        
        Optional<ArriendoModel> arriendoOptional = arriendoRepository.findById(Integer.parseInt(id));
        
        if (arriendoOptional.isPresent()) {
            ArriendoModel arriendoEncontrado = arriendoOptional.get();
            
           arriendoEditar.setIdArriendo(arriendoEncontrado.getIdArriendo());
            
            arriendoRepository.save(arriendoEditar);
            
            return new ResponseEntity<>(arriendoEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody ArriendoModel nuevoArriendo) {
        
        nuevoArriendo= arriendoRepository.save(nuevoArriendo);
        
        Optional<ArriendoModel> arriendoOptional = arriendoRepository.findById(nuevoArriendo.getIdArriendo());
        
        if (arriendoOptional.isPresent()) {
            ArriendoModel arriendoEncontrado = arriendoOptional.get();
            return new ResponseEntity<>(arriendoEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<ArriendoModel> arriendoOptional = arriendoRepository.findById(Integer.parseInt(id));
        
        if (arriendoOptional.isPresent()) {
            ArriendoModel arriendoEncontrado = arriendoOptional.get();
            arriendoRepository.deleteById(arriendoEncontrado.getIdArriendo());
            return new ResponseEntity<>(arriendoEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
