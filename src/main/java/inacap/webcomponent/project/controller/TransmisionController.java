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
import inacap.webcomponent.project.model.TransmisionModel;
import inacap.webcomponent.project.repository.transmisionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/transmision")
public class TransmisionController {
    
   
    @Autowired
    private transmisionRepository transmisionRepository;
    
    @GetMapping()
    public Iterable<TransmisionModel> listarTodos() {
        return transmisionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <TransmisionModel> MuestraTransmision (@PathVariable String id) {
        
        Optional<TransmisionModel> transmisionOptional = transmisionRepository.findById(Integer.parseInt(id));
        
        if (transmisionOptional.isPresent()) {
            TransmisionModel transmisionEncontrado = transmisionOptional.get();
            return new ResponseEntity<>(transmisionEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TransmisionModel> editaTransmision (@PathVariable String id, @RequestBody TransmisionModel transmisionEditar) {
        
        Optional<TransmisionModel> transmisionOptional = transmisionRepository.findById(Integer.parseInt(id));
        
        if (transmisionOptional.isPresent()) {
            TransmisionModel transmisionEncontrado = transmisionOptional.get();
            
            transmisionEditar.setIdTransmision(transmisionEncontrado.getIdTransmision());
            
            transmisionRepository.save(transmisionEditar);
            
            return new ResponseEntity<>(transmisionEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TransmisionModel nuevoTransmision) {
        
        nuevoTransmision= transmisionRepository.save(nuevoTransmision);
        
        Optional<TransmisionModel> transmisionOptional = transmisionRepository.findById(nuevoTransmision.getIdTransmision());
        
        if (transmisionOptional.isPresent()) {
            TransmisionModel transmisionEncontrado = transmisionOptional.get();
            return new ResponseEntity<>(transmisionEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<TransmisionModel> transmisionOptional = transmisionRepository.findById(Integer.parseInt(id));
        
        if (transmisionOptional.isPresent()) {
            TransmisionModel transmisionEncontrado = transmisionOptional.get();
            transmisionRepository.deleteById(transmisionEncontrado.getIdTransmision());
            return new ResponseEntity<>(transmisionEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
}
