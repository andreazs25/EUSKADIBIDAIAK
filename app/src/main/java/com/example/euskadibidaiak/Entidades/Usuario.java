package com.example.euskadibidaiak.Entidades;

public class Usuario {

    //private Integer id;
    private String Email;
    private String Password;
    private String Nacionalidad;

    public Usuario( String email, String password, String nacionalidad) {
        //this.id = id;
        Email = email;
        Password = password;
        Nacionalidad = nacionalidad;
    }
/**
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
**/
    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }
}
