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
@Table(name = "Tipo_Persona")
public class TipopersonaModel {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idTipopersona;
    private String nombreTipopersona;
    private String detalle;

    public int getIdTipopersona() {
        return idTipopersona;
    }

    public void setIdTipopersona(int idTipopersona) {
        this.idTipopersona = idTipopersona;
    }

    public String getNombreTipopersona() {
        return nombreTipopersona;
    }

    public void setNombreTipopersona(String nombreTipopersona) {
        this.nombreTipopersona = nombreTipopersona;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public TipopersonaModel() {
    }

    public TipopersonaModel(String nombreTipopersona, String detalle) {
        this.nombreTipopersona = nombreTipopersona;
        this.detalle = detalle;
    }

    private TipopersonaModel(int idTipopersona, String nombreTipopersona, String detalle) {
        this.idTipopersona = idTipopersona;
        this.nombreTipopersona = nombreTipopersona;
        this.detalle = detalle;
    }
    
   
}
