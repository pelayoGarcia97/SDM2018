package com.example.scar.gym.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="PersonalTrainer.db";
    private static final int DATABASE_VERSION=1;


    public static final String TABLE_USUARIOS="usuarios";
    public static final String COLUMN_ID ="id";
    public static final String COLUMN_NOMBRE="nombre";
    public static final String COLUMN_APELLIDOS="apellidos";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_CONTRASENIA="contrasenia";


    public static final String TABLE_RUTINADIETA="rutinadieta";
    public static final String COLUMN_ID_USUARIO="idusuario";
    public static final String COLUMN_ID_RUTINA="id_rutina";
    public static final String COLUMN_ID_DIETA="id_dieta";
    public static final String  COLUMN_DIFICULTAD_RUTINA="dificultad";



    public static final String TABLE_USER_HISTORY="historialusuario";
    public static final String COLUMN_ID_USUARIO_HISTORY="idusuario";
    public static final String COLUMN_EDAD = "edad";
    public static final String COLUMN_ALTURA="altura";
    public static final String COLUMN_PESO="peso";
    public static final String COLUMN_FECHA="fecha";

    private static final String DATABASE_CREATE_USER="create table " + TABLE_USUARIOS+"("+ COLUMN_ID+" "+
            " integer primary key autoincrement, "+
            COLUMN_NOMBRE +" "+"text not null,"
            +COLUMN_APELLIDOS+" "+"text not null,"
            +COLUMN_EMAIL +" "+"text not null,"
            +COLUMN_CONTRASENIA+" "+"text not null"
            +");";

    private static final String DATABASE_CREATE_RUTINADIETA="create table "+ TABLE_RUTINADIETA+"("
            +COLUMN_ID_USUARIO+" " +"integer primary Key autoincrement not null, "
            + COLUMN_ID_RUTINA+" "+ "integer ,"
            + COLUMN_ID_DIETA+" "+ "integer ,"
            +COLUMN_DIFICULTAD_RUTINA+" "+"text,"
            + "foreign key(idusuario) references usuarios(id),"
            +"check(dificultad='principiante' OR dificultad='avanzado' OR dificultad='intermedio')"
            +");";

    private static final String DATABASE_CREATE_USUARIO_HISTORY=" create table "+TABLE_USER_HISTORY+"("
            +COLUMN_ID_USUARIO_HISTORY +" "+"integer not null,"
            +COLUMN_PESO+" "+"decimal ,"
            +COLUMN_ALTURA+" "+"integer ,"
            +COLUMN_EDAD+" "+"integer ,"
            +COLUMN_FECHA+" "+"DATE,"
            +"foreign key(idusuario) references usuarios(id)"
            +");";

    private static final String DATABASE_DROP_USER ="drop table if exists "+ TABLE_USUARIOS;
    private static final String DATABASE_DROP_RUTINADIETA="drop table if exists "+ TABLE_RUTINADIETA;
    private static final String DATABASE_DROP_USUARIOHISTORY="drop table if exists "+ TABLE_USER_HISTORY;


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_USER);
            db.execSQL(DATABASE_CREATE_RUTINADIETA);
            db.execSQL(DATABASE_CREATE_USUARIO_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP_USER);
        db.execSQL(DATABASE_DROP_RUTINADIETA);
        db.execSQL(DATABASE_DROP_USUARIOHISTORY);
        this.onCreate(db);

    }
}
