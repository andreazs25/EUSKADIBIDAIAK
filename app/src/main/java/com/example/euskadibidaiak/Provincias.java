package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.euskadibidaiak.Entidades.Adaptador;
import com.example.euskadibidaiak.Entidades.Provincia;

import java.util.ArrayList;

public class Provincias extends AppCompatActivity {
ListView lista;
String[]datos={"Bizkaia","Araba","Guipuzkoa"};
int[] datosimag={R.drawable.bi,R.drawable.ar,R.drawable.gu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincias);
        lista=(ListView)findViewById(R.id.milista);

        ArrayList<Provincia> itemsCompra = obtenerItems();

        lista.setAdapter(new Adaptador(this,datos,datosimag));

    }

    private ArrayList<Provincia> obtenerItems() {
        ArrayList<Provincia> items = new ArrayList<Provincia>();

        items.add(new Provincia( 1, "Tuberculo",
                "drawable/bi"));
        items.add(new Provincia(2, "Fruta",
                "drawable/ar"));
        items.add(new Provincia(3,  "Verdura",
                "drawable/gu"));

        return items;
}}