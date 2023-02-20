package com.mx.prueba.personas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mx.prueba.personas.entity.personas;
import com.mx.prueba.personas.service.InsertaTabla;
import com.mx.prueba.personas.util.Constantes;

@Component
public class MainPersona {
    
    private static final Logger log = LoggerFactory.getLogger(MainPersona.class);

    String registro = null;
    String nombre = null;

    List<personas> alta = new ArrayList<personas>();
    List<personas>personasInsertadas = new ArrayList<personas>();
    List<personas> persona = new ArrayList<personas>();
    String[] datos = null;
    String opcion = null;
    int id_persona = 0;
    Boolean borrado = false;
    String apellidoMaterno = null;
    
    @Autowired
    InsertaTabla tabla;

    public void inicio() throws  IOException{

        Scanner entrada = new Scanner(System.in);

        File datosPersonas = new File(Constantes.archivo);

        try(BufferedReader lectura  = new BufferedReader(new FileReader(datosPersonas))){
            while((registro = lectura.readLine()) != null){


           datos = GeneraArray(registro);
        
           

 
                personas persona = new personas();
                persona.setNombre(datos[0]);
                persona.setApellidoPaterno(datos[1]);
                apellidoMaterno = datos[2];
                int tamanoMAterno = apellidoMaterno.trim().length();
                System.out.println("el valor de apellido materno es " + apellidoMaterno + " " + tamanoMAterno);
                if(tamanoMAterno == 0  ){
                    String noApellido = "no tiene apellido";
                    persona.setApellidoMaterno(noApellido);
                }else{
                persona.setApellidoMaterno(datos[2]);
                }
                persona.setNacionalidad(datos[3]);
                persona.setSexo(datos[4]);
                persona.setEstadoCivil(datos[5]);
                persona.setCalle(datos[6]);
                persona.setNumeroCasa(datos[7]);
                persona.setMunicipio(datos[8]);
                persona.setEdad(Integer.valueOf(datos[9]));
                alta.add(persona);
        
            }
            lectura.close();

            for(personas registro :alta){
            tabla.InsertaPersona(registro);
            }
            
            
        }

        System.out.println("a que personas deseas buscar ");

        id_persona = entrada.nextInt( );
      

        persona = tabla.Obtienepersona(id_persona);
        log.info(" el valor de personas es  " + persona );
        personasInsertadas.addAll(persona);
        
        for(personas per : personasInsertadas){
           id_persona = per.getIdPersona();
           System.out.println("el apellido paterno de " + per.getNombre() + " es "  + per.getApellidoPaterno());
           System.out.println("el apellido materno de "  + per.getNombre()  + " es "  + per.getApellidoMaterno());
           System.out.println("la naciondalidad de " + per.getNombre() + " es " + per.getNacionalidad());
           System.out.println("el sexo  de " + per.getNombre() + " es "  + per.getSexo() );
           System.out.println("el estado civil de " + per.getNombre() + " es"  + per.getEstadoCivil());
           System.out.println("la calle de " + per.getNombre() + " es " + per.getCalle());
           System.out.println("el numero de la casa de  " + per.getNombre() + " es " + per.getNumeroCasa());
           System.out.println("el municipio donde vive "  + per.getNombre() + " es " + per.getMunicipio());
           System.out.println("la edad de  " + per.getNombre() + " es " + per.getEdad());



         }

         Scanner entrada2 = new Scanner(System.in);
         System.out.println("deseas borrar a esta persona de la tabla  " );
         opcion = entrada2.nextLine();

         if(opcion.equalsIgnoreCase("si") ){

            borrado = true;

         }else {
            System.out.println("desea borrar a otra persona " );
            opcion = entrada2.nextLine();
            if(opcion.equalsIgnoreCase("si")){
            System.out.println("que persona deseas borrar ");
            id_persona = entrada2.nextInt();
            borrado = true; 
            }else { 
              borrado = false;
            }


         }

         if(borrado){ 
            tabla.BorradoPersona(id_persona);
         }





        
    }

    public String[] GeneraArray(String registro) {

        String[] registros = registro.split(";");

        return registros;

    }

}
