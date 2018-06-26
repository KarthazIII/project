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
import inacap.webcomponent.project.model.VersionModel;
import org.springframework.http.HttpStatus;
import inacap.webcomponent.project.repository.versionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/version")
public class VersionController {
    
    @Autowired
    private versionRepository versionRepository;
    
    @GetMapping()
    public Iterable<VersionModel> listarTodos() {
        return versionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <VersionModel> MuestraVersion (@PathVariable String id) {
        
        Optional<VersionModel> versionOptional = versionRepository.findById(Integer.parseInt(id));
        
        if (versionOptional.isPresent()) {
            VersionModel versionEncontrado = versionOptional.get();
            return new ResponseEntity<>(versionEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VersionModel> editaVersion(@PathVariable String id, @RequestBody VersionModel versionEditar) {
        
        Optional<VersionModel> versionOptional = versionRepository.findById(Integer.parseInt(id));
        
        if (versionOptional.isPresent()) {
            VersionModel versionEncontrado = versionOptional.get();
            
            versionEditar.setIdVersion(versionEncontrado.getIdVersion());
            
            versionRepository.save(versionEditar);
            
            return new ResponseEntity<>(versionEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody VersionModel nuevoVersion) {
        
        nuevoVersion= versionRepository.save(nuevoVersion);
        
        Optional<VersionModel> versionOptional = versionRepository.findById(nuevoVersion.getIdVersion());
        
        if (versionOptional.isPresent()) {
            VersionModel versionEncontrado = versionOptional.get();
            return new ResponseEntity<>(versionEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<VersionModel> versionOptional = versionRepository.findById(Integer.parseInt(id));
        
        if (versionOptional.isPresent()) {
            VersionModel versionEncontrado = versionOptional.get();
            versionRepository.deleteById(versionEncontrado.getIdVersion());
            return new ResponseEntity<>(versionEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
