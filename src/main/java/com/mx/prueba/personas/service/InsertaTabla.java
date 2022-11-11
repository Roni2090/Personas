package com.mx.prueba.personas.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.prueba.personas.entity.personas;
import com.mx.prueba.personas.util.Constantes;

@Repository
public class InsertaTabla {

    private JdbcTemplate plantilla;

    public InsertaTabla(JdbcTemplate plantilla){

        this.plantilla = plantilla; 
    }


@Transactional
public void InsertaPersona(personas persona){

    String sqlPersona = Constantes.InsertaPersonas();

    plantilla.update(sqlPersona,
    persona.getNombre(),
    persona.getApellidoPaterno(),
    persona.getApellidoMaterno(),
    persona.getNacionalidad(),
    persona.getSexo(),
    persona.getEstadoCivil(),
    persona.getCalle(),
    persona.getNumeroCasa(),
    persona.getMunicipio(),
    persona.getEdad());
    
}



    
}
