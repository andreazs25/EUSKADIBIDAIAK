package com.example.euskadibidaiak.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.euskadibidaiak.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + Utilidades.TABLA_USUARIO+ " (" +
                Utilidades.CAMPO_EMAIL + " TEXT PRIMARY KEY, " +
                Utilidades.CAMPO_PASS + " TEXT NOT NULL, " +
                Utilidades.CAMPO_NACIONALIDAD + " TEXT NOT NULL);");

        db.execSQL(" CREATE TABLE " + Utilidades.TABLA_LUGARES+ " (" +
                Utilidades.CAMPO_PROVINCIA + " TEXT NOT NULL, " +
                Utilidades.CAMPO_LUGAR + " TEXT NOT NULL, " + Utilidades.CAMPO_VALORACION.toString() + " INTEGER NOT NULL, "
                +Utilidades.CAMPO_EMAIL_USUARIO+ " TEXT NOT NULL, " +
                " FOREIGN KEY (" +Utilidades.CAMPO_EMAIL_USUARIO+ ") REFERENCES "+Utilidades.TABLA_USUARIO+"("+Utilidades.CAMPO_EMAIL+"));");







    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
            db.execSQL("DROP TABLE IF EXISTS Usuario");
            onCreate(db);
    }
}
