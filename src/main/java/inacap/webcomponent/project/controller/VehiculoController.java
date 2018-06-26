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
import inacap.webcomponent.project.model.VehiculoModel;
import org.springframework.http.HttpStatus;
import inacap.webcomponent.project.repository.VehiculoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/vehiculo")

public class VehiculoController {
    
    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    @GetMapping()
    public Iterable<VehiculoModel> listarTodos() {
        return vehiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <VehiculoModel> MuestraVehiculos (@PathVariable String id) {
        
        Optional<VehiculoModel> vOptional = vehiculoRepository.findById(Integer.parseInt(id));
        
        if (vOptional.isPresent()) {
            VehiculoModel vEncontrado = vOptional.get();
            return new ResponseEntity<>(vEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoModel> editaVehiculo (@PathVariable String id, @RequestBody VehiculoModel vehiculoEditar) {
        
        Optional<VehiculoModel> vOptional = vehiculoRepository.findById(Integer.parseInt(id));
        
        if (vOptional.isPresent()) {
            VehiculoModel vEncontrado = vOptional.get();
            
            vehiculoEditar.setIdVehiculo(vEncontrado.getIdVehiculo());
            
            vehiculoRepository.save(vehiculoEditar);
            
            return new ResponseEntity<>(vehiculoEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody VehiculoModel nuevoVehiculo) {
        
        nuevoVehiculo= vehiculoRepository.save(nuevoVehiculo);
        
        Optional<VehiculoModel> vOptional = vehiculoRepository.findById(nuevoVehiculo.getIdVehiculo());
        
        if (vOptional.isPresent()) {
            VehiculoModel vEncontrado = vOptional.get();
            return new ResponseEntity<>(vEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<VehiculoModel> vOptional = vehiculoRepository.findById(Integer.parseInt(id));
        
        if (vOptional.isPresent()) {
            VehiculoModel vEncontrado = vOptional.get();
            vehiculoRepository.deleteById(vEncontrado.getIdVehiculo());
            return new ResponseEntity<>(vEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
