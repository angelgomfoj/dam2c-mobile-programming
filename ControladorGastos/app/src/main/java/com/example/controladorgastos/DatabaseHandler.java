package com.example.controladorgastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.controladorgastos.modelo.Gasto;
import com.example.controladorgastos.modelo.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, "gastos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTablaGasto = "CREATE TABLE gasto(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CATEGORIA TEXT," + "DESCRIPCION TEXT," + "IMPORTE DOUBLE," + "FECHA INTEGER)";

        String crearTablaUsuario = "CREATE TABLE usuario(USERNAME TEXT PRIMARY KEY," + "LIMITEGASTOS DOUBLE)";
        db.execSQL(crearTablaGasto);
        db.execSQL(crearTablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropGasto = "DROP TABLE IF EXISTS gasto";
        String dropUsuario = "DROP TABLE IF EXISTS usuario";
        db.execSQL(dropGasto);
        db.execSQL(dropUsuario);
        onCreate(db);
    }

    public boolean addGasto(String categoria, String descripcion, double importe, long fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CATEGORIA", categoria);
        contentValues.put("DESCRIPCION", descripcion);
        contentValues.put("IMPORTE", importe);
        contentValues.put("FECHA", fecha);

        long result = db.insert("gasto", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addUsuario(String username, double limiteGastos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("LIMITEGASTOS", limiteGastos);

        long result = db.insert("usuario", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateGasto(int id, String categoria, String descripcion, double importe, long fecha) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CATEGORIA", categoria);
        contentValues.put("DESCRIPCION", descripcion);
        contentValues.put("IMPORTE", importe);
        contentValues.put("FECHA", fecha);

        long result = database.update("gasto", contentValues, "id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateUsuario(String username, double limiteGastos) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("LIMITEGASTOS", limiteGastos);

        long result = database.update("usuario", contentValues, "username=?", new String[]{username});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Gasto> getAllGastos() {
        List<Gasto> listaGastos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor gastos = db.rawQuery("SELECT * FROM gasto", null);

        if (gastos.getCount() == 0) {

        } else {
            if (listaGastos == null) {
                listaGastos = new ArrayList<>();
            }

            while (gastos.moveToNext()) {
                listaGastos.add(new Gasto(gastos.getInt(0), gastos.getString(1), gastos.getString(2), gastos.getDouble(3), new Date(gastos.getLong(4))));
            }
        }

        return listaGastos;
    }

    public Usuario getUsuario() {
        Usuario u = new Usuario();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor usuario = db.rawQuery("SELECT * FROM usuario", null);


        u = (new Usuario(usuario.getString(0), usuario.getDouble(1)));

        return u;
    }


}
