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
import inacap.webcomponent.project.model.ModeloModel;
import inacap.webcomponent.project.repository.modeloRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/modelo")
public class ModeloController {
    
    @Autowired
    private modeloRepository modeloRepository;
    
    @GetMapping()
    public Iterable<ModeloModel> listarTodos() {
        return modeloRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <ModeloModel> MuestraModelo (@PathVariable String id) {
        
        Optional<ModeloModel> modeloOptional = modeloRepository.findById(Integer.parseInt(id));
        
        if (modeloOptional.isPresent()) {
            ModeloModel modeloEncontrado = modeloOptional.get();
            return new ResponseEntity<>(modeloEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ModeloModel> editaModelo(@PathVariable String id, @RequestBody ModeloModel modeloEditar) {
        
        Optional<ModeloModel> modeloOptional = modeloRepository.findById(Integer.parseInt(id));
        
        if (modeloOptional.isPresent()) {
            ModeloModel modeloEncontrado = modeloOptional.get();
            
            modeloEditar.setIdModelo(modeloEncontrado.getIdModelo());
            
            modeloRepository.save(modeloEditar);
            
            return new ResponseEntity<>(modeloEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody ModeloModel nuevoModelo) {
        
        nuevoModelo= modeloRepository.save(nuevoModelo);
        
        Optional<ModeloModel> modeloOptional = modeloRepository.findById(nuevoModelo.getIdModelo());
        
        if (modeloOptional.isPresent()) {
            ModeloModel modeloEncontrado = modeloOptional.get();
            return new ResponseEntity<>(modeloEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<ModeloModel> modeloOptional = modeloRepository.findById(Integer.parseInt(id));
        
        if (modeloOptional.isPresent()) {
            ModeloModel modeloEncontrado = modeloOptional.get();
            modeloRepository.deleteById(modeloEncontrado.getIdModelo());
            return new ResponseEntity<>(modeloEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
