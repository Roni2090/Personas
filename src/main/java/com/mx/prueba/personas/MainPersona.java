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
    List<personas> personasInsertadas = new ArrayList<personas>();
    List<personas> persona = new ArrayList<personas>();
    List<personas> cambiosPersona = new ArrayList<personas>();
    String[] datos = null;
    String opcion = null;
    int id_persona = 0;
    Boolean borrado = false;
    String apellidoMaterno = null;
    String campoCambio = null;
    String cambio = null;

    @Autowired
    InsertaTabla tabla;

    public void inicio() throws IOException {

        Scanner entrada = new Scanner(System.in);

        File datosPersonas = new File(Constantes.archivo);

        try (BufferedReader lectura = new BufferedReader(new FileReader(datosPersonas))) {
            while ((registro = lectura.readLine()) != null) {

                datos = GeneraArray(registro);

                personas persona = new personas();
                persona.setNombre(datos[0]);
                persona.setApellidoPaterno(datos[1]);
                apellidoMaterno = datos[2];
                int tamanoMAterno = apellidoMaterno.trim().length();
                System.out.println("el valor de apellido materno es " + apellidoMaterno + " " + tamanoMAterno);
                if (tamanoMAterno == 0) {
                    String noApellido = "no tiene apellido";
                    persona.setApellidoMaterno(noApellido);
                } else {
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

            for (personas registro : alta) {
                tabla.InsertaPersona(registro);
            }

        }

        System.out.println("a que personas deseas buscar ");

        id_persona = entrada.nextInt();

        persona = tabla.Obtienepersona(id_persona);
        log.info(" el valor de personas es  " + persona);
        personasInsertadas.addAll(persona);

        for (personas per : personasInsertadas) {
            id_persona = per.getIdPersona();
            System.out.println("el apellido paterno de " + per.getNombre() + " es " + per.getApellidoPaterno());
            System.out.println("el apellido materno de " + per.getNombre() + " es " + per.getApellidoMaterno());
            System.out.println("la naciondalidad de " + per.getNombre() + " es " + per.getNacionalidad());
            System.out.println("el sexo  de " + per.getNombre() + " es " + per.getSexo());
            System.out.println("el estado civil de " + per.getNombre() + " es" + per.getEstadoCivil());
            System.out.println("la calle de " + per.getNombre() + " es " + per.getCalle());
            System.out.println("el numero de la casa de  " + per.getNombre() + " es " + per.getNumeroCasa());
            System.out.println("el municipio donde vive " + per.getNombre() + " es " + per.getMunicipio());
            System.out.println("la edad de  " + per.getNombre() + " es " + per.getEdad());

        }

        Scanner entrada2 = new Scanner(System.in);
        System.out.println("deseas borrar a esta persona de la tabla  ");
        opcion = entrada2.nextLine();

        if (opcion.equalsIgnoreCase("si")) {

            borrado = true;

        } else {
            System.out.println("desea borrar a otra persona ");
            opcion = entrada2.nextLine();
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("que persona deseas borrar ");
                id_persona = entrada2.nextInt();
                borrado = true;
            } else {
                borrado = false;
            }

        }

        if (borrado) {
            tabla.BorradoPersona(id_persona);
        }

        Scanner entrada3 = new Scanner(System.in);
        System.out.println("deseas modificar a una persona ");
        opcion = entrada3.nextLine();

        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("que persona deseas modificar  ");
            id_persona = entrada3.nextInt();
            cambiosPersona = tabla.Obtienepersona(id_persona);

            for (personas datos : cambiosPersona) {

                System.out.println("el apellido paterno de " + datos.getNombre() + " es " + datos.getApellidoPaterno());
                System.out.println("el apellido materno de " + datos.getNombre() + " es " + datos.getApellidoMaterno());
                System.out.println("la naciondalidad de " + datos.getNombre() + " es " + datos.getNacionalidad());
                System.out.println("el sexo  de " + datos.getNombre() + " es " + datos.getSexo());
                System.out.println("el estado civil de " + datos.getNombre() + " es" + datos.getEstadoCivil());
                System.out.println("la calle de " + datos.getNombre() + " es " + datos.getCalle());
                System.out.println("el numero de la casa de  " + datos.getNombre() + " es " + datos.getNumeroCasa());
                System.out.println("el municipio donde vive " + datos.getNombre() + " es " + datos.getMunicipio());
                System.out.println("la edad de  " + datos.getNombre() + " es " + datos.getEdad());

            }
            System.out.println("que campo deseas cambiar ");
            opcion = entrada3.nextLine();
            System.out.println("cambio a realizar ");
            cambio = entrada3.nextLine();
            modificacionPersona(opcion, cambiosPersona, cambio);

        }

        entrada.close();
        entrada2.close();
        entrada3.close();

    }

    public String[] GeneraArray(String registro) {

        String[] registros = registro.split(";");

        return registros;

    }

    public void modificacionPersona(String opcion, List<personas> persona, String cambio) {

        for (personas registro : persona) {

            switch (opcion) {
                case "nombre":
                    if (!cambio.equalsIgnoreCase(registro.getNombre())) {
                        registro.setNombre(cambio);
                    }
                    break;
                case "apellido paterno":
                    if (!cambio.equalsIgnoreCase(registro.getApellidoPaterno())) {
                        registro.setApellidoPaterno(cambio);
                    }
                    break;
                case "apellido materno":
                    if (!cambio.equalsIgnoreCase(registro.getApellidoMaterno())) {
                        registro.setApellidoMaterno(cambio);
                    }
                    break;
                case "nacionalidad":
                    if (!cambio.equalsIgnoreCase(registro.getNacionalidad())) {
                        registro.setNacionalidad(cambio);
                    }
                    break;
                case "sexo":
                    if (!cambio.equalsIgnoreCase(registro.getSexo())){
                        registro.setSexo(cambio);
                    }
                    break;
                case "estado civil":
                    if(!cambio.equalsIgnoreCase(registro.getEstadoCivil())){
                       registro.setEstadoCivil(cambio);
                    }
                    break;
                case "calle" :
                    if(!cambio.equalsIgnoreCase(registro.getCalle())){
                        registro.setNumeroCasa(cambio);
                    }    
                    break;
                case "numero casa":
                    if(!cambio.equalsIgnoreCase(registro.getNumeroCasa())){
                        registro.setNumeroCasa(cambio);
                    } 
                    break;   
                case "municipio":
                    if(!cambio.equalsIgnoreCase(registro.getMunicipio())){
                        registro.setMunicipio(cambio);
                    }    
                    break;
                case "edad" :
                     int cambioNumerico = Integer.valueOf(cambio);
                    if( cambioNumerico != registro.getEdad()){
                        registro.setEdad(cambioNumerico);
                    }    
                


            }
        }
    }

}
