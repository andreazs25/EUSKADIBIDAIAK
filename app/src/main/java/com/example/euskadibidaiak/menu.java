package com.example.euskadibidaiak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
Button clave;
Button Provincias;
Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        clave=findViewById(R.id.bclave);
        Provincias=findViewById(R.id.bPro);
        volver=findViewById(R.id.buttonvo);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarActividadl();
            }
        });
      clave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finalizarActividadC();
          }
      });

        Provincias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarActividadP();
            }
        });
    }
    private void finalizarActividadC() {
        //Lanzar actividad pantalla cambiar contraseña
        Intent intent = new Intent(this, CambiarContrasenia.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }
    private void finalizarActividadP() {
        //Lanzar actividad pantalla provincias
        Intent intent = new Intent(this, Provincias.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }
    private void finalizarActividadl() {
        //Lanzar actividad pantalla login
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        //Finalizar actividad
        finish();
    }
}