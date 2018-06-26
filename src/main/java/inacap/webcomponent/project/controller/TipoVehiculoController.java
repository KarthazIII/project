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
import inacap.webcomponent.project.model.TipoVehiculoModel;
import inacap.webcomponent.project.model.VehiculoModel;
import org.springframework.http.HttpStatus;
import inacap.webcomponent.project.repository.TipoVehiculoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author MSLV0
 */
@RestController
@RequestMapping("/TipoVehiculo")
public class TipoVehiculoController {
    
    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;
    
    @GetMapping()
    public Iterable<TipoVehiculoModel> list() {
        return tipoVehiculoRepository.findAll();
    }
    
    
   @GetMapping("/{id}")
    public ResponseEntity <TipoVehiculoModel> MuestraTipoVehiculos (@PathVariable String id) {
        
        Optional<TipoVehiculoModel> tvOptional = tipoVehiculoRepository.findById(Integer.parseInt(id));
        
        if (tvOptional.isPresent()) {
            TipoVehiculoModel tvEncontrado = tvOptional.get();
            return new ResponseEntity<>(tvEncontrado, HttpStatus.FOUND);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculoModel> editaTipoVehiculo (@PathVariable String id, @RequestBody TipoVehiculoModel TipovehiculoEditar) {
        
        Optional<TipoVehiculoModel> tvOptional = tipoVehiculoRepository.findById(Integer.parseInt(id));
        
        if (tvOptional.isPresent()) {
            TipoVehiculoModel tvEncontrado = tvOptional.get();
            
            TipovehiculoEditar.setIdTipoVehiculo(tvEncontrado.getIdTipoVehiculo());
            
            tipoVehiculoRepository.save(TipovehiculoEditar);
            
            return new ResponseEntity<>(TipovehiculoEditar, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TipoVehiculoModel nuevoTipoVehiculo) {
        
        nuevoTipoVehiculo= tipoVehiculoRepository.save(nuevoTipoVehiculo);
        
        Optional<TipoVehiculoModel> tvOptional = tipoVehiculoRepository.findById(nuevoTipoVehiculo.getIdTipoVehiculo());
        
        if (tvOptional.isPresent()) {
            TipoVehiculoModel tvEncontrado = tvOptional.get();
            return new ResponseEntity<>(tvEncontrado, HttpStatus.CREATED);
         
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE); 
        }
   
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
        Optional<TipoVehiculoModel> tvOptional = tipoVehiculoRepository.findById(Integer.parseInt(id));
        
        if (tvOptional.isPresent()) {
            TipoVehiculoModel tvEncontrado = tvOptional.get();
            tipoVehiculoRepository.deleteById(tvEncontrado.getIdTipoVehiculo());
            return new ResponseEntity<>(tvEncontrado, HttpStatus.OK);
            
            
        }else{
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
    
}
