package com.mx.prueba.personas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mx.prueba.personas.entity.personas;
import com.mx.prueba.personas.service.InsertaTabla;
import com.mx.prueba.personas.util.Constantes;


@Component
public class MainPersona {

    String registro        = null;
    String nombre          = null;
    String apellidoPaterno = null;
    String apellidoMaterno = null;
    String nacionalidad    = null;
    String sexo            = null;
    String estadoCivil     = null;
    String calle           = null;
    String numeroCasa      = null;
    String municipio       = null;
    int edad               = 0;

    
   List<personas>alta = new ArrayList<personas>();

    @Autowired
    personas persona;
    @Autowired
    InsertaTabla tabla;


    
    public void inicio() throws  IOException{

        File datosPersonas = new File(Constantes.archivo);

        try(BufferedReader lectura  = new BufferedReader(new FileReader(datosPersonas))){
            while((registro = lectura.readLine()) != null){

                nombre = registro.substring(0,4);
                apellidoPaterno = registro.substring(5,14);

                persona.setNombre(nombre);
                persona.setApellidoPaterno(apellidoPaterno);
                System.out.println(nombre + " " + apellidoPaterno);

               alta.add(persona);
                
            }
            lectura.close();

            for(personas registro :alta){
                tabla.InsertaPersona(registro);
            }
            
            
        }

        
    }
    
}
