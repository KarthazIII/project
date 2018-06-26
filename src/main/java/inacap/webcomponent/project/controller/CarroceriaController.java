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
import inacap.webcomponent.project.model.CarroceriaModel;
import inacap.webcomponent.project.repository.carroceriaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/carroceria")
public class CarroceriaController {
    
    @Autowired
    private carroceriaRepository carroceriaRepository;
    
    @GetMapping()
    public Iterable<CarroceriaModel> listarTodos() {
        return carroceriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <CarroceriaModel> MuestraCarroceria (@PathVariable String id) {
        
        Optional<CarroceriaModel> carroceriaOptional = carroceriaRepository.findById(Integer.parseInt(id));
        
        if (carroceriaOptional.isPresent()) {
            CarroceriaModel carroceriaEncontrado = carroceriaOptional.get();
            return new ResponseEntity<>(carroceriaEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarroceriaModel> editaCarroceria (@PathVariable String id, @RequestBody CarroceriaModel carroceriaEditar) {
        
        Optional<CarroceriaModel> carroceriaOptional = carroceriaRepository.findById(Integer.parseInt(id));
        
        if (carroceriaOptional.isPresent()) {
            CarroceriaModel carroceriaEncontrado = carroceriaOptional.get();
            
            carroceriaEditar.setIdCarroceria(carroceriaEncontrado.getIdCarroceria());
            
            carroceriaRepository.save(carroceriaEditar);
            
            return new ResponseEntity<>(carroceriaEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody CarroceriaModel nuevoCarroceria) {
        
        nuevoCarroceria= carroceriaRepository.save(nuevoCarroceria);
        
        Optional<CarroceriaModel> carroceriaOptional = carroceriaRepository.findById(nuevoCarroceria.getIdCarroceria());
        
        if (carroceriaOptional.isPresent()) {
            CarroceriaModel carroceriaEncontrado = carroceriaOptional.get();
            return new ResponseEntity<>(carroceriaEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<CarroceriaModel> carroceriaOptional = carroceriaRepository.findById(Integer.parseInt(id));
        
        if (carroceriaOptional.isPresent()) {
            CarroceriaModel carroceriaEncontrado = carroceriaOptional.get();
            carroceriaRepository.deleteById(carroceriaEncontrado.getIdCarroceria());
            return new ResponseEntity<>(carroceriaEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
