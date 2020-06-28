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

public class GuipuzkoaLugares extends AppCompatActivity {
    public TextView guipuzkoa;
    public TextView donosti;
    public RatingBar valoD;
    public TextView flish;
    public RatingBar valoF;
    public Button guardar;
    public Button volver;
    public boolean insertado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guipuzkoa_lugares);
        guipuzkoa=findViewById(R.id.guipu);
        donosti=findViewById(R.id.sanse);
        valoD=findViewById(R.id.valod);
        flish=findViewById(R.id.zu);
        valoF=findViewById(R.id.valoF);
        String guipu=guipuzkoa.getText().toString();
        String donostis=donosti.getText().toString();
        Integer valoDo=valoD.getNumStars();
        String flishz=flish.getText().toString();
        Integer valoFi=valoF.getNumStars();
        volver=findViewById(R.id.volve);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarActividad();
            }
        });
        //valoramos el lugar y guardamos la valoracion asi como los datos
        valoD.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                String pUsuario = pref.getString("username", "");
                insert(guipu,donostis, Float.toString(v),pUsuario);

                Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
            }
        });
        //valoramos el lugar y guardamos la valoracion asi como los datos
        valoF.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                String pUsuario = pref.getString("username", "");
                insert(guipu,flishz,Float.toString(v),pUsuario);
                Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
            }
        });

    }
    //valoramos el lugar y guardamos la valoracion asi como los datos
    public void insert(String pP,String pL,String pValo,String emailUsuario){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdUsuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String sentencia = " ('"+pP+"','"+pL+"','"+pValo+"','"+emailUsuario+"')";
        try {
            db.execSQL("INSERT INTO Lugares (Provincia,Lugar,valoracion,emailUsuario) VALUES "+sentencia);
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