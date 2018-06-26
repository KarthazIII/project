/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponent.project.model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MSLV0
 */

@Entity
@Table(name = "Medio_de_pago")
public class MediopagoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idMediopago;
    private String nombreMediopago;
    private String detalle;
    

    public int getIdMediopago() {
        return idMediopago;
    }

    public void setIdMediopago(int idMediopago) {
        this.idMediopago = idMediopago;
    }

    public String getNombreMediopago() {
        return nombreMediopago;
    }

    public void setNombreMediopago(String nombreMediopago) {
        this.nombreMediopago = nombreMediopago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    public MediopagoModel() {
    }

    public MediopagoModel(String nombreMediopago, String detalle) {
        this.nombreMediopago = nombreMediopago;
        this.detalle = detalle;
    }

    private MediopagoModel(int idMediopago, String nombreMediopago, String detalle) {
        this.idMediopago = idMediopago;
        this.nombreMediopago = nombreMediopago;
        this.detalle = detalle;
    }
    
    
}
