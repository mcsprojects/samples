package com.mcsprojects.gestorIncidencias.controllers;

import com.mcsprojects.gestorIncidencias.models.Incidencia;
import com.mcsprojects.gestorIncidencias.repositories.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
public class IncidenciaController {

    @Autowired
    IncidenciaRepository incidenciaRepo;

    @RequestMapping(method=RequestMethod.GET, value="/incidencias")
    public Iterable<Incidencia> incidencia() {
        return incidenciaRepo.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/incidencias")
    public Incidencia save(@RequestBody Incidencia incidencia) {
        incidenciaRepo.save(incidencia);

        return incidencia;
    }

    @RequestMapping(method=RequestMethod.GET, value="/incidencias/{id}")
    public Optional<Incidencia> details(@PathVariable String id) {
        return incidenciaRepo.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/incidencias/{id}")
    public Incidencia update(@PathVariable String id, @RequestBody Incidencia incidencia) {
        Optional<Incidencia> optincidencia = incidenciaRepo.findById(id);
        Incidencia i = optincidencia.get();
        if(incidencia.getProyecto() != null)
            i.setProyecto(incidencia.getProyecto());
        if(incidencia.getDescription() != null)
            i.setDescription(incidencia.getDescription());
        if(incidencia.getPrioridad() != null)
            i.setPrioridad(incidencia.getPrioridad());
        if(incidencia.getEstado() != null)
            i.setEstado(incidencia.getEstado());
        if(incidencia.getFrecuencia() != null)
            i.setFrecuencia(incidencia.getFrecuencia());         
        if(incidencia.getReporte() != null)
            i.setReporte(incidencia.getReporte());
        if(incidencia.getSolution() != null)
            i.setSolution(incidencia.getSolution());
        if(incidencia.getEvaluation() != null)
            i.setEvaluation(incidencia.getEvaluation());
        if(incidencia.getCategory() != null)
            i.setCategory(incidencia.getCategory());
        incidenciaRepo.save(i);
        return i;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/incidencias/{id}")
    public String delete(@PathVariable String id) {
        Optional<Incidencia> optincidencia = incidenciaRepo.findById(id);
        Incidencia incidencia = optincidencia.get();
        incidenciaRepo.delete(incidencia);

        return "";
    }
}
