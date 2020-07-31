package com.example.petagramv2.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.petagramv2.R;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public ArrayList<Mascota> obtenerFavoritos(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFavoritas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE,"Pepe");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE,"Coco");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE,"Rambo");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro3);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE,"Teo");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro4);

        db.insertarMascota(contentValues);


        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE,"Tito");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro5);

        db.insertarMascota(contentValues);

    }

    public static void insertarMascotaFavorita(BaseDatos db, Mascota mascota){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS_NOMBRE,mascota.getNombre());
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS_FOTO, mascota.getFoto());
        db.insertarMascotaFavorita(contentValues);
    }

    public void addFavorito(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        insertarMascotaFavorita(db,mascota);
    }

    public void addRating(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_RATING_MASCOTAS_RATING, 1);
        db.insertarRatingMascota(contentValues);
    }

    public int obtenerRating(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerRatingMascota(mascota);
    }
}
