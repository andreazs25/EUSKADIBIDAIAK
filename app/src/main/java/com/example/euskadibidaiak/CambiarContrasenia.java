package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.euskadibidaiak.Entidades.ConexionSQLiteHelper;
import com.example.euskadibidaiak.Entidades.GestorUsuario;
import com.example.euskadibidaiak.Entidades.Usuario;

import java.util.Iterator;

public class CambiarContrasenia extends AppCompatActivity {
    public EditText passTextAntigua;
    public EditText passText;
    public Button cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenia);

        //Cojemos las constrasenias que ingresa el usuario
        passTextAntigua= findViewById(R.id.editTextTextPassword2);
        passText= findViewById(R.id.editTextTextPassword);
        cambiar = findViewById(R.id.button2);
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passAntigua=passTextAntigua.getText().toString();
                String pass=passText.getText().toString();
                if(passAntigua.equals(" ")||pass.equals(" ")){
                    Toast.makeText(getApplicationContext(),"por favor rellene todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    cambiarContrasenia(passAntigua,pass);
                }
            }
        });
    }

    public void cambiarContrasenia(String pPassAntigua, String pPass){
        boolean cambiado = false;
        String pUsuario;
        SharedPreferences pref;

        //Guardamos el email de la sesion
        pref = getApplicationContext().getSharedPreferences("MyPref",0);
        pUsuario = pref.getString("username", "");
        System.out.println(pUsuario);

        cambiado = cambiarPassSession(pUsuario, pPassAntigua, pPass);
        //Si hemos cambiado la pass en la Session
        if (cambiado == true) {
            //Cambiamos la pass en la BD
            cambiarPassBD(pUsuario,pPass);
        }
    }

    public boolean cambiarPassSession(String pEmail,String pPassAntigua, String pPass){
        Iterator<Usuario> iterator = GestorUsuario.getSingletonInstance().getLu().iterator();
        Usuario unUsu=null;
        boolean esta=false;

        while (iterator.hasNext() && !esta) {
            unUsu = iterator.next();
            if(unUsu.getEmail().equals(pEmail)&& unUsu.getPassword().equals(pPass)){
                //Cambiamos la pass del usuario en la Session
                iterator.remove();
                unUsu.setPassword(pPass);
                GestorUsuario.getSingletonInstance().getLu().add(unUsu);
                //Ponemos a true el chivato
                esta=true;
            }
        }
        return esta;
    }

    //insertamos a los usuarios nuevos
    public void cambiarPassBD(String pUsuario,String pPass){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdUsuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        try {
            db.execSQL("UPDATE Usuario SET clave ='"+ pPass +"' WHERE email = '"+ pUsuario +"'");
            Toast.makeText(getApplicationContext(),"Contraseña modificada corretamente.",Toast.LENGTH_SHORT).show();
        }catch(Exception e) {
            Toast.makeText(getApplicationContext(),"Algo ha fallado al cambiar la contraseña.",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        db.close();
    }
}