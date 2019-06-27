package com.mcsprojects.gestorIncidencias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidencias")
public class Incidencia {
    @Id
    String id;
    String proyecto;
    String description;
    String prioridad; 
    String estado;    
    String frecuencia;     
	String reporte;   
    String solution;       
    String evaluation;
    String category;

    public Incidencia() {
    }

    public Incidencia(String proyecto, String description, String prioridad, String estado, String frecuencia, String reporte, String solution, String evaluation, String category ) {
        this.proyecto = proyecto;
        this.description = description;
        this.prioridad = prioridad;
        this.estado = estado;        
        this.frecuencia = frecuencia;
        this.reporte = reporte;
        this.solution = solution;
        this.evaluation = evaluation;
        this.category = category;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

     public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
    
     public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
     
}
