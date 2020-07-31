package com.example.petagramv2.db;

public class ConstanteBaseDatos {

    public static final String DATABASE_NAME = "contactos";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTAS = "mascota";
    public static final String TABLE_MASCOTAS_ID = "id";
    public static final String TABLE_MASCOTAS_NOMBRE = "nombre";
    public static final String TABLE_MASCOTAS_FOTO = "foto";

    public static final String TABLE_RATING_MASCOTAS = "mascota_rating";
    public static final String TABLE_RATING_MASCOTAS_ID = "id";
    public static final String TABLE_RATING_MASCOTAS_ID_MASCOTA = "id_contacto";
    public static final String TABLE_RATING_MASCOTAS_RATING = "rating";

    public static final String TABLE_MASCOTAS_FAVORITAS = "mascota_favoritos";
    public static final String TABLE_MASCOTAS_FAVORITAS_ID = "id";
    public static final String TABLE_MASCOTAS_FAVORITAS_NOMBRE = "nombre";
    public static final String TABLE_MASCOTAS_FAVORITAS_FOTO = "foto";

}
