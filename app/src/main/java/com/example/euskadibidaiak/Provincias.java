package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.euskadibidaiak.Entidades.Adaptador;

public class Provincias extends AppCompatActivity {
ListView lista;
String[]datos={"Bizkaia","Araba","Guipuzkoa"};
int[] datosimag={R.drawable.bi,R.drawable.ar,R.drawable.gu};
Button volvemos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincias);
        lista=(ListView)findViewById(R.id.milista);
        lista.setAdapter(new Adaptador(this,datos,datosimag));
        volvemos=findViewById(R.id.bvolv);
        volvemos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarActividad();
            }
        });



}
    private void finalizarActividad() {
        //Lanzar actividad pantalla provincias
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }

}