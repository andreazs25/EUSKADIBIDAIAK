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
                Utilidades.CAMPO_NACIONALIDAD + " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
            db.execSQL("DROP TABLE IF EXISTS Usuario");
            onCreate(db);
    }
}
