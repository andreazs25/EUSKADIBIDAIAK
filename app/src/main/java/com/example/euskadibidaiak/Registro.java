package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.euskadibidaiak.Entidades.ConexionSQLiteHelper;
import com.example.euskadibidaiak.Entidades.GestorUsuario;
import com.example.euskadibidaiak.Entidades.Usuario;
import com.example.euskadibidaiak.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Registro extends AppCompatActivity {
  public  EditText Email;
    public  EditText Pass;
    public EditText Nacionalidad;
   public Button registar;
    public Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

       Email= findViewById(R.id.emailR);
       Pass=findViewById(R.id.claveRegistro);
        Nacionalidad=findViewById(R.id.nacioRe);
        registar=findViewById(R.id.buttonregi);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString();
                String pass=Pass.getText().toString();
                String Naci=Nacionalidad.getText().toString();
                if(email.equals(" ")||pass.equals(" ")||Naci.equals(" ")){
                    Toast.makeText(getApplicationContext(),"por favor rellene todos los campos",Toast.LENGTH_SHORT).show();
                }else if(!Gestor.getGestor().esEmail(email)){
                    Toast.makeText(getApplicationContext(),"el email debe estar en un formato valido email@,com",Toast.LENGTH_SHORT).show();
                }else if( comprobarEmail(email,pass)) {

                    Toast.makeText(getApplicationContext(), "este email ya esta registrado", Toast.LENGTH_SHORT).show();
                }else{
                    registar(email,pass,Naci);
                  finalizarActividad();
                }

            }
        });
        volver=findViewById(R.id.buttonvolver);
        volver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finalizarActividad();
    }
});
    }

    public void registar(String email,String clave,String nacionalidad){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd Usuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        /**
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_EMAIL,email);
        values.put(Utilidades.CAMPO_PASS,pas);
        values.put(Utilidades.CAMPO_NACIONALIDAD,nacionalidad);
        Long emailResul=db.insert(Utilidades.TABLA_USUARIO,null,values);
        Toast.makeText(getApplicationContext(),"email registro:"+emailResul,Toast.LENGTH_SHORT).show();
        db.close();
**/
        db.execSQL("INSERT INTO Usuario (email,clave,nacionalidad) VALUES (email,clave,nacionalidad) ");
        Toast.makeText(getApplicationContext(),"email registro hecho",Toast.LENGTH_SHORT).show();
        db.close();
    }
    private void finalizarActividad() {
        //Lanzar actividad pantalla provincias
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }
    public void cargarD() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd Usuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        //hacemos una query porque queremos devolver un cursor
        String selectQuery = "SELECT  * FROM " + Utilidades.TABLA_USUARIO;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String email=cursor.getString(0);
            System.out.println(email);
            String pas=cursor.getString(1);
            System.out.println(pas);
            String nacionalidad=cursor.getString(2);
            System.out.println(nacionalidad);
            GestorUsuario.getSingletonInstance().getLu().add(new Usuario(email,pas,nacionalidad));
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

    }

    public boolean comprobarEmail(String pEmail,String pPass){
        cargarD();
        Iterator<Usuario> iterator = GestorUsuario.getSingletonInstance().getLu().iterator();
        Usuario unUsu=null;
        boolean esta=false;

        while (iterator.hasNext() && !esta) {
            unUsu = iterator.next();
            if(unUsu.getEmail().equals(pEmail)&& unUsu.getPassword().equals(pPass)){
                esta=true;
            }

        }
        if(esta){
            return esta;
        }else{
            return esta;
        }

    }


}