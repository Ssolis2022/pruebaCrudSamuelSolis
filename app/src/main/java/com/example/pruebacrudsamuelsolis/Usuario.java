package com.example.pruebacrudsamuelsolis;public class Usuario {

    int id;
    String medico;
    String nombre;
    String edad;
    String peso;
    String descripcion;
    public Usuario(){

    }

    public Usuario(String medico, String nombre, String edad, String peso, String descripcion){
        this.medico = medico;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.descripcion = descripcion;
    }
    public Usuario(int id, String medico, String nombre, String edad, String peso, String descripcion) {
        this.id = id;
        this.medico = medico;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}