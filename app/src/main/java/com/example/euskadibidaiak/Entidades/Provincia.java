package com.example.euskadibidaiak.Entidades;

public class Provincia {
    public int imagen;
    protected String rutaImagen;
    public String nombreP;

    public Provincia(int imagen, String nombreP) {
        this.imagen = imagen;
        this.nombreP = nombreP;
    }

    public Provincia(int imagen, String rutaImagen, String nombreP) {
        this.imagen = imagen;
        this.rutaImagen = rutaImagen;
        this.nombreP = nombreP;
    }

    public int getImagen() {
        return imagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
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
