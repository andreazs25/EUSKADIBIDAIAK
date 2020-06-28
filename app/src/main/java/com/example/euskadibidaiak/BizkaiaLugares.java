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

public class BizkaiaLugares extends AppCompatActivity {
public TextView bizkaia;
public TextView sanJuan;
public RatingBar valoS;
public TextView bilbaoo;
public RatingBar valoB;

public Button volver;
public boolean insertado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizkaia_lugares);
        bizkaia=findViewById(R.id.bizkaia);
        sanJuan=findViewById(R.id.gazte);
        valoS=findViewById(R.id.valoSan);
        bilbaoo=findViewById(R.id.bi);
        valoB=findViewById(R.id.valoB);
        String biz=bizkaia.getText().toString();
        String san=sanJuan.getText().toString();
        Integer valosa=valoS.getNumStars();
        String bilboo=bilbaoo.getText().toString();
        Integer valoBi=valoB.getNumStars();
        volver=findViewById(R.id.volver);


volver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finalizarActividad();
    }
});
//valoramos el lugar y guardamos la valoracion asi como los datos
valoS.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String pUsuario = pref.getString("username", "");
        insert(biz,san, Float.toString(v),pUsuario);

        Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
    }
});
//valoramos el lugar y guardamos la valoracion asi como los datos
valoB.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String pUsuario = pref.getString("username", "");
        insert(biz,bilboo,Float.toString(v),pUsuario);
        Toast.makeText(getApplicationContext(),"has calificado con:"+v+" ",Toast.LENGTH_SHORT).show();
    }
});

    }
//insertamos los datos de los lugares junto son sus valoraciones
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