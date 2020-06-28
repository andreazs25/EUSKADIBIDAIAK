package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.euskadibidaiak.Entidades.ConexionSQLiteHelper;

public class AlavaLugares extends AppCompatActivity {
    public TextView alava;
    public TextView vitoria;
    public RatingBar valov;
    public TextView gorbea;
    public RatingBar valoG;

    public Button volver;
    public boolean insertado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alava_lugares);
        alava=findViewById(R.id.Alava);
        vitoria=findViewById(R.id.vito);
        valov=findViewById(R.id.valoV);
        gorbea=findViewById(R.id.gorbe);
        valoG=findViewById(R.id.valoG);
        String ala=alava.getText().toString();
        String vit=vitoria.getText().toString();
        String Gorbe=gorbea.getText().toString();

        volver=findViewById(R.id.vol);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarActividad();
            }
        });//valoramos el lugar y guardamos la valoracion asi como los datos
        valov.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                String pUsuario = pref.getString("username", "");
                insert(ala,vit,Float.toString(v),pUsuario);
                Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
            }
        });
//valoramos el lugar y guardamos la valoracion asi como los datos
valoG.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String pUsuario = pref.getString("username", "");
        insert(ala,Gorbe,Float.toString(v),pUsuario);
        Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
    }
});


    }
    public void insert(String pP,String pL,String pValo,String emailUsuario){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdUsuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String sentencia = " ('"+pP+"','"+pL+"','"+pValo+"','"+emailUsuario+"')";
        try {
            db.execSQL("INSERT INTO Lugares (Provincia,Lugar,valoracion,emailUsuario) VALUES "+sentencia);
            System.out.println(sentencia);
            Toast.makeText(getApplicationContext(),"Gracias popr tu valoración!!",Toast.LENGTH_SHORT).show();
            insertado = false;
        }catch(Exception e) {
            insertado = true;
            Toast.makeText(getApplicationContext(),"ya hemos guardado tu valoración",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        db.close();

    }
    private void finalizarActividad() {
        //Lanzar actividad pantalla provincias
        Intent intent = new Intent(this, Provincias.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }





}