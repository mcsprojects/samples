package com.mcsprojects.gestorIncidencias.repositories;

import com.mcsprojects.gestorIncidencias.models.Incidencia;
import org.springframework.data.repository.CrudRepository;

public interface IncidenciaRepository extends CrudRepository<Incidencia, String> {
    @Override
    void delete(Incidencia deleted);
}
