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
import inacap.webcomponent.project.model.TipopersonaModel;
import inacap.webcomponent.project.repository.tipopersonaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/tipopersona")
public class TipopersonaController {
    
    @Autowired
    private tipopersonaRepository tipopersonaRepository;
    
    @GetMapping()
    public Iterable<TipopersonaModel> listarTodos() {
        return tipopersonaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <TipopersonaModel> MuestraTipopersona (@PathVariable String id) {
        
        Optional<TipopersonaModel> tipopersonaOptional = tipopersonaRepository.findById(Integer.parseInt(id));
        
        if (tipopersonaOptional.isPresent()) {
            TipopersonaModel tipopersonaEncontrado = tipopersonaOptional.get();
            return new ResponseEntity<>(tipopersonaEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipopersonaModel> editaTipopersona (@PathVariable String id, @RequestBody TipopersonaModel tipopersonaEditar) {
        
        Optional<TipopersonaModel> tipopersonaOptional = tipopersonaRepository.findById(Integer.parseInt(id));
        
        if (tipopersonaOptional.isPresent()) {
            TipopersonaModel tipopersonaEncontrado = tipopersonaOptional.get();
            
            tipopersonaEditar.setIdTipopersona(tipopersonaEncontrado.getIdTipopersona());
            
            tipopersonaRepository.save(tipopersonaEditar);
            
            return new ResponseEntity<>(tipopersonaEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TipopersonaModel nuevoTipopersona) {
        
        nuevoTipopersona= tipopersonaRepository.save(nuevoTipopersona);
        
        Optional<TipopersonaModel> tipopersonaOptional = tipopersonaRepository.findById(nuevoTipopersona.getIdTipopersona());
        
        if (tipopersonaOptional.isPresent()) {
            TipopersonaModel tipopersonaEncontrado = tipopersonaOptional.get();
            return new ResponseEntity<>(tipopersonaEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<TipopersonaModel> tipopersonaOptional = tipopersonaRepository.findById(Integer.parseInt(id));
        
        if (tipopersonaOptional.isPresent()) {
            TipopersonaModel tipopersonaEncontrado = tipopersonaOptional.get();
            tipopersonaRepository.deleteById(tipopersonaEncontrado.getIdTipopersona());
            return new ResponseEntity<>(tipopersonaEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
