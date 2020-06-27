package com.example.euskadibidaiak.Entidades;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorUsuario {
    private ArrayList<Usuario> lu;
    private static  GestorUsuario miGestorUsuario;



    private GestorUsuario() {
        this.lu = new ArrayList<Usuario>();
    }


    public static GestorUsuario getSingletonInstance() {
        if (miGestorUsuario == null){
            miGestorUsuario = new GestorUsuario();
        }
        else{
            System.out.println("No se puede crear el objeto "+   " porque ya existe un objeto de la clase GestorUsuario");
        }

        return miGestorUsuario;
    }

    public ArrayList<Usuario> getLu() {
        return lu;
    }


}


