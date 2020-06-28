package com.example.euskadibidaiak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.euskadibidaiak.Entidades.ConexionSQLiteHelper;
import com.example.euskadibidaiak.Entidades.GestorUsuario;
import com.example.euskadibidaiak.Entidades.Usuario;
import com.example.euskadibidaiak.Utilidades.Utilidades;

import java.util.Iterator;

public class Login extends AppCompatActivity {
public EditText email;
public EditText pass;
public Button login;
public Button registrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdUsuario",null,1);
        email=findViewById(R.id.emailedi);
        pass=findViewById(R.id.clavelogin);
        login=findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String pEmail=email.getText().toString();
            String pPass=pass.getText().toString();
            if(email.equals(" ")|| pass.equals(" ")){
                createSimpleDialog();
            }else if(!Gestor.getGestor().esEmail(pEmail)){
                Toast.makeText(getApplicationContext(),"el email debe estar en un formato valido email@.com",Toast.LENGTH_SHORT).show();
            } else if(!comprobarEmail(pEmail,pPass)){
                mostrarDialogPass();
            }else{
                sesion(pEmail,pPass);
                finalizarActividad();
            }
        }
    });
    registrarse=findViewById(R.id.registrarL);
        registrarse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finalizar();
        }
    });
}

    public void selectColumnas() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdUsuario",null,1);
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
        // Una vez obtenidos todos los datos y cerrado el cursor
    }

    public boolean comprobarEmail(String pEmail,String pPass){
        selectColumnas();
        Iterator<Usuario> iterator = GestorUsuario.getSingletonInstance().getLu().iterator();
        Usuario unUsu=null;
        boolean esta=false;

        while (iterator.hasNext() && !esta) {
            unUsu = iterator.next();
            if(unUsu.getEmail().equals(pEmail)&& unUsu.getPassword().equals(pPass)){
                esta=true;
            }
        }
        return esta;
    }

    private void mostrarDialogPass() {
        FragmentManager fm = getSupportFragmentManager();
        PassIncorrectaDialog alertDialog = new PassIncorrectaDialog();
        alertDialog.show(fm, "fragment");
    }

    private void finalizarActividad() {
        //Lanzar actividad
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }

    private void finalizar() {
        //Lanzar actividad
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }

    public AlertDialog createSimpleDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("AVISO")
                .setMessage("El Mensaje para el usuario Hay campos vacios,debe llenar todos los campos")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones
                                dialog.cancel();
                            }
                        });
        return builder.create();
    }

    private void sesion(String user, String pPassword) {
        //Proceso de login
        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", user);
        editor.putString("password", pPassword);
        editor.commit();
        //Notificación de bienvenida
        String welcome = "Bienvenid@! ," + user;
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }




}