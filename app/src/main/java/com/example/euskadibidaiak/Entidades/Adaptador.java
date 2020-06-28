package com.example.euskadibidaiak.Entidades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.euskadibidaiak.AlavaLugares;
import com.example.euskadibidaiak.BizkaiaLugares;
import com.example.euskadibidaiak.GuipuzkoaLugares;
import com.example.euskadibidaiak.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    String[] datos;
    int[] datosImag;


    public Adaptador(Context contexto, String[] datos, int[] imagenes) {

        this.contexto = contexto;
        this.datos = datos;
        this.datosImag = imagenes;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return datosImag.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup parent) {
    final View vista=inflater.inflate(R.layout.elementolista,null);
    TextView nombre=(TextView)vista.findViewById(R.id.tBizkaia);
    ImageView imagen=(ImageView)vista.findViewById(R.id.imageBizkaia);
    nombre.setText(datos[i]);
    imagen.setImageResource(datosImag[i]);
    imagen.setTag(i);
    imagen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(imagen.getTag().equals(0)) {
                Intent intent = new Intent(contexto, BizkaiaLugares.class);
                intent.putExtra("IMG", datosImag[(Integer) view.getTag()]);
                contexto.startActivity(intent);
            }else if(imagen.getTag().equals(1)){
                Intent intent = new Intent(contexto, AlavaLugares.class);
                intent.putExtra("IMG", datosImag[(Integer) view.getTag()]);
                contexto.startActivity(intent);
            }else{
                Intent intent = new Intent(contexto, GuipuzkoaLugares.class);
                intent.putExtra("IMG", datosImag[(Integer) view.getTag()]);
                contexto.startActivity(intent);
            }
        }
    });

   return vista;
    }



}