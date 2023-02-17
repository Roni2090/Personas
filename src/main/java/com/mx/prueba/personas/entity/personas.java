package com.mx.prueba.personas.entity;

import org.springframework.stereotype.Component;

@Component
public class personas {

    int idPersonas         = 0;
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

    public int getIdPersona(){
        return idPersonas;
    }

    public void setPersonas(int idPersonas){
        this.idPersonas  = idPersonas;
        
    }

    public String getNombre(){
        return nombre;
    }
    public  void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getApellidoPaterno(){
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno){
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno(){
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno){
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getNacionalidad(){
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;

    }
    public String getSexo(){
        return sexo;
    }
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    public String getEstadoCivil(){
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil){
        this.estadoCivil = estadoCivil;
    }
    public String getCalle(){
        return calle;
    }
    public void setCalle(String calle){
        this.calle = calle;
    }
    public String getNumeroCasa(){
        return numeroCasa;
    }
    public void setNumeroCasa(String numeroCasa){
        this.numeroCasa = numeroCasa;

    }
    public String getMunicipio(){
        return municipio;
    }
    public void setMunicipio(String municipio){
        this.municipio = municipio;
    }
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
 }
