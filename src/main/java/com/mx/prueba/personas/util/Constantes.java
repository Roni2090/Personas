package com.mx.prueba.personas.util;

public class Constantes {

    public  final static String archivo ="/home/hakkon/Documentos/prueba.txt";

    


    public static String InsertaPersonas(){

        StringBuilder  persona = new StringBuilder();

        persona.append("INSERT INTO persona(nombre,ApellPaterno,apellMaterno,nacionalidad,")
               .append("sexo,estadoCivil,calle,numeroCasa,municipio,edad) ")
               .append("VALUES(?,?,?,?,?,?,?,?,?,?)");              

        return persona.toString();
    }


    public static String ObtienePersonas(){
        
        StringBuilder obtienePersonas = new StringBuilder();

        obtienePersonas.append("SELECT * ")
                       .append("FROM persona ")
                       .append("WHERE id_persona =? "); 

        return obtienePersonas.toString();               
    }

    public  static String BorrarPersona (){ 
       
        StringBuilder queryBorrarPersonas = new StringBuilder();

        queryBorrarPersonas.append("DELETE  ")
                           .append("FROM persona ")
                           .append("WHERE id_persona=?");

     return queryBorrarPersonas.toString();                      
    }

    public static String ActualizaPersona(){
        
        StringBuilder queryActualiza = new StringBuilder();

        queryActualiza.append("UPDATE  PERSONA ")
                      .append("SET NOMBRE=? , ApellPaterno=?,")       
    }
    
}

