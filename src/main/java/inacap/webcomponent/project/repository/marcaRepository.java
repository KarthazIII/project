/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponent.project.repository;

import org.springframework.data.repository.CrudRepository;
import inacap.webcomponent.project.model.MarcaModel;

/**
 *
 * @author MSLV0
 */
public interface marcaRepository extends CrudRepository<MarcaModel, Integer> {
    
}
