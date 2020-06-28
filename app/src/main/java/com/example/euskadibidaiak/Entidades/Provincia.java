package com.example.euskadibidaiak.Entidades;

public class Provincia {
    public int imagen;
    public String nombreP;

    public Provincia(int imagen, String nombreP) {
        this.imagen = imagen;
        this.nombreP = nombreP;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }
}
