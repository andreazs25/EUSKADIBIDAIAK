package com.example.euskadibidaiak;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gestor {
    private static Gestor miGestor;


    private Gestor (){}
    public static Gestor getGestor(){
        if(miGestor==null){
            miGestor=new Gestor();

        }
        return miGestor;
    }
    public boolean esEmail(String correo) {
        // Patron de validar el email
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (correo != null) {
            Matcher matcher = pattern.matcher(correo);
            if (matcher.matches()) {
                return true;
            }

        }
        return false;
    }
}
