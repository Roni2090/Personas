package com.mx.prueba.personas.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.prueba.personas.entity.personas;
import com.mx.prueba.personas.util.Constantes;



@Repository
public class InsertaTabla {

    private static final Logger log = LoggerFactory.getLogger(InsertaTabla.class);

    private JdbcTemplate plantilla;

    public InsertaTabla(JdbcTemplate plantilla){

        this.plantilla = plantilla; 
    }


@Transactional
public void InsertaPersona(personas persona){

    String sqlPersona = Constantes.InsertaPersonas();

    try {

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
    }catch(Exception e ){
        log.info("Error al insertar datos en la tabla de personas  " + e);
    }
    
}

@SuppressWarnings("deprecation")
public List<personas> Obtienepersona(int id_persona){

    String sqlObtienePersonas = Constantes.ObtienePersonas();
    List<personas>persona = new ArrayList<>();
    
    try{

         persona =  plantilla.query(sqlObtienePersonas, new Object[] {id_persona}, (rs, rowNum) -> { 
        
        personas  per = new personas();
        per.setPersonas(rs.getInt("id_persona"));
        per.setNombre(rs.getString("nombre"));
        per.setApellidoPaterno(rs.getString("ApellPaterno"));
        per.setApellidoMaterno(rs.getString("apellMaterno"));
        per.setNacionalidad(rs.getString("nacionalidad"));
        per.setSexo(rs.getString("sexo"));
        per.setEstadoCivil(rs.getString("estadoCivil"));
        per.setCalle(rs.getString("calle"));
        per.setNumeroCasa(rs.getString("numeroCasa"));
        per.setMunicipio(rs.getString("municipio"));
        per.setEdad(rs.getInt("edad"));
        return per;
         });

   
           

    }catch(Exception e ){
        log.error("error al obtener a la personas con id " + id_persona + " " + e);
    }
    


    return persona;
}


public void BorradoPersona(int id_persona){

    String  queryBorrar = Constantes.BorrarPersona();

    try {
        plantilla.update(queryBorrar , id_persona);
    }catch(Exception e ){
        log.info("Error al borrar  a la persona con id " + id_persona  + " " + e );
    }

        

}





    
}
