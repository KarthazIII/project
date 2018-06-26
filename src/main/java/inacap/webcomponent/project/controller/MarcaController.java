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
import inacap.webcomponent.project.model.MarcaModel;
import inacap.webcomponent.project.repository.marcaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    private marcaRepository marcaRepository;
    
    @GetMapping()
    public Iterable<MarcaModel> listarTodos() {
        return marcaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <MarcaModel> MuestraMarca(@PathVariable String id) {
        
        Optional<MarcaModel> marcaOptional = marcaRepository.findById(Integer.parseInt(id));
        
        if (marcaOptional.isPresent()) {
           MarcaModel marcaEncontrado = marcaOptional.get();
            return new ResponseEntity<>(marcaEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MarcaModel> editaMarca(@PathVariable String id, @RequestBody MarcaModel marcaEditar) {
        
        Optional<MarcaModel> marcaOptional = marcaRepository.findById(Integer.parseInt(id));
        
        if (marcaOptional.isPresent()) {
            MarcaModel marcaEncontrado = marcaOptional.get();
            
            marcaEditar.setIdMarca(marcaEncontrado.getIdMarca());
            
            marcaRepository.save(marcaEditar);
            
            return new ResponseEntity<>(marcaEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MarcaModel nuevoMarca) {
        
        nuevoMarca= marcaRepository.save(nuevoMarca);
        
        Optional<MarcaModel> marcaOptional = marcaRepository.findById(nuevoMarca.getIdMarca());
        
        if (marcaOptional.isPresent()) {
            MarcaModel marcaEncontrado = marcaOptional.get();
            return new ResponseEntity<>(marcaEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<MarcaModel> marcaOptional = marcaRepository.findById(Integer.parseInt(id));
        
        if (marcaOptional.isPresent()) {
            MarcaModel marcaEncontrado = marcaOptional.get();
            marcaRepository.deleteById(marcaEncontrado.getIdMarca());
            return new ResponseEntity<>(marcaEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
    
}
