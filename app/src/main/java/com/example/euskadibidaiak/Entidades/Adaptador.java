package com.example.euskadibidaiak.Entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.euskadibidaiak.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Provincia> listItem;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listItem.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.activity_provincias,null);
        ImageView imf=(ImageView) view.findViewById(R.id.imageBizkaia);
       // TextView titulo=(TextView) view.findViewById();

        return view;
    }

    public Adaptador(Context context, ArrayList<Provincia> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

}
