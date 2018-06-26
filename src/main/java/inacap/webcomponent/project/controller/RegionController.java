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
import inacap.webcomponent.project.model.RegionModel;
import inacap.webcomponent.project.repository.regionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/region")
public class RegionController {
    
    @Autowired
    private regionRepository regionRepository;
    
    @GetMapping()
    public Iterable<RegionModel> listarTodos() {
        return regionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <RegionModel> MuestraRegion (@PathVariable String id) {
        
        Optional<RegionModel> regionOptional = regionRepository.findById(Integer.parseInt(id));
        
        if (regionOptional.isPresent()) {
            RegionModel regionEncontrado = regionOptional.get();
            return new ResponseEntity<>(regionEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RegionModel> editaRegion (@PathVariable String id, @RequestBody RegionModel regionEditar) {
        
        Optional<RegionModel> regionOptional = regionRepository.findById(Integer.parseInt(id));
        
        if (regionOptional.isPresent()) {
            RegionModel regionEncontrado = regionOptional.get();
            
           regionEditar.setIdRegion(regionEncontrado.getIdRegion());
            
            regionRepository.save(regionEditar);
            
            return new ResponseEntity<>(regionEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody RegionModel nuevoRegion) {
        
        nuevoRegion= regionRepository.save(nuevoRegion);
        
        Optional<RegionModel> regionOptional = regionRepository.findById(nuevoRegion.getIdRegion());
        
        if (regionOptional.isPresent()) {
            RegionModel regionEncontrado = regionOptional.get();
            return new ResponseEntity<>(regionEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<RegionModel> regionOptional = regionRepository.findById(Integer.parseInt(id));
        
        if (regionOptional.isPresent()) {
            RegionModel regionEncontrado = regionOptional.get();
            regionRepository.deleteById(regionEncontrado.getIdRegion());
            return new ResponseEntity<>(regionEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
    
}
