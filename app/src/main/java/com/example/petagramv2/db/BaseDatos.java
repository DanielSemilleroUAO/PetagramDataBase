package com.example.petagramv2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petagramv2.MainActivity;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstanteBaseDatos.DATABASE_NAME, null, ConstanteBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota = "CREATE TABLE "+ConstanteBaseDatos.TABLE_MASCOTAS+"("
                +ConstanteBaseDatos.TABLE_MASCOTAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE+" TEXT, "
                +ConstanteBaseDatos.TABLE_MASCOTAS_FOTO+" INTEGER"
                +")";

        String queryCrearTablaRatingMascota = "CREATE TABLE "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS+"("
                +ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA+" INTEGER, "
                +ConstanteBaseDatos.TABLE_RATING_MASCOTAS_RATING+" INTERGER, "
                +"FOREIGN KEY ("+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA+") "
                +"REFERENCES "+ConstanteBaseDatos.TABLE_MASCOTAS+"("+ConstanteBaseDatos.TABLE_MASCOTAS_ID+")"
                +")";

        String queryCrearTablaMascotaFavoritos = "CREATE TABLE "+ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS+"("
                +ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS_NOMBRE+" TEXT, "
                +ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS_FOTO+" INTEGER"
                +")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaRatingMascota);
        db.execSQL(queryCrearTablaMascotaFavoritos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstanteBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstanteBaseDatos.TABLE_RATING_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_MASCOTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryRating = "SELECT COUNT("+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_RATING
                    +") as rating FROM "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS
                    +" WHERE "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA+"="+mascotaActual.getId();

            Cursor registroRating = db.rawQuery(queryRating, null);
            if(registroRating.moveToNext()){
                mascotaActual.setRating(registroRating.getInt(0));
            }else{
                mascotaActual.setRating(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        obtenerMascotasFavoritas();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryRating = "SELECT COUNT("+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_RATING
                    +") as rating FROM "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS
                    +" WHERE "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA+"="+mascotaActual.getId();

            Cursor registroRating = db.rawQuery(queryRating, null);
            if(registroRating.moveToNext()){
                mascotaActual.setRating(registroRating.getInt(0));
            }else{
                mascotaActual.setRating(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        MainActivity.btnFavoritos.setText(String.valueOf(mascotas.size()));
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarMascotaFavorita(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_MASCOTAS_FAVORITAS,null, contentValues);
        db.close();
    }

    public void insertarRatingMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_RATING_MASCOTAS, null, contentValues);
        db.close();
    }

    public int obtenerRatingMascota(Mascota mascota){
        int rating = 0;

        String query = "SELECT COUNT("+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_RATING
                +") as rating FROM "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS
                +" WHERE "+ConstanteBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA+"="+mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            rating = registros.getInt(0);
        }

        db.close();

        return rating;
    }

}
