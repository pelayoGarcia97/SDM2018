package com.example.scar.gym.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.mtp.MtpConstants;
import android.util.Log;

import com.example.scar.gym.Datos.CaracteristicasUsuario;
import com.example.scar.gym.Datos.Usuario;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UsuarioDataSource {

    private SQLiteDatabase database;
    private MyDBHelper dbHelper;

    private final String [] allUserColums = {MyDBHelper.COLUMN_ID,MyDBHelper.COLUMN_NOMBRE,MyDBHelper.COLUMN_APELLIDOS,MyDBHelper.COLUMN_EMAIL,MyDBHelper.COLUMN_CONTRASENIA};

    private final String [] allrutinadietaColums = {MyDBHelper.COLUMN_ID_USUARIO,MyDBHelper.COLUMN_ID_RUTINA,MyDBHelper.COLUMN_ID_DIETA,MyDBHelper.COLUMN_DIFICULTAD_RUTINA};

    private final String [] allHistorialColums ={MyDBHelper.COLUMN_ID_USUARIO_HISTORY,MyDBHelper.COLUMN_EDAD,MyDBHelper.COLUMN_ALTURA,MyDBHelper.COLUMN_PESO,MyDBHelper.COLUMN_FECHA};


    public UsuarioDataSource(Context context){
        dbHelper= new MyDBHelper(context,null,null,1);
    }

    public void open() throws SQLException{
        database=dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }


    public long createUser(Usuario user){
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.COLUMN_NOMBRE,user.getNombre());
        values.put(MyDBHelper.COLUMN_APELLIDOS,user.getApellidos());
        values.put(MyDBHelper.COLUMN_EMAIL,user.getEmail());
        values.put(MyDBHelper.COLUMN_CONTRASENIA,user.getContrasenia());
        long insertId= database.insert(MyDBHelper.TABLE_USUARIOS,null,values);
        return insertId;
    }


    public long createHistorialUsuario(int id, double peso,int altura,Date fecha){
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.COLUMN_ID_USUARIO_HISTORY,id);
        values.put(MyDBHelper.COLUMN_PESO,peso);
        values.put(MyDBHelper.COLUMN_ALTURA,altura);
        values.put(MyDBHelper.COLUMN_FECHA,fecha.getTime());
        long insertId= database.insert(MyDBHelper.TABLE_USUARIOS,null,values);
        return insertId;
    }


    public Usuario getUser(String email){
        Cursor cursor= database.query(MyDBHelper.TABLE_USUARIOS,allUserColums,"email="+'"'+email+'"',null,null,null,null);
        cursor.moveToFirst();
        Usuario user=new Usuario();
        while(!cursor.isAfterLast()){
            user.setId(cursor.getInt(0));
            user.setNombre(cursor.getString(1));
            user.setApellidos(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setContrasenia(cursor.getString(4));
            cursor.moveToNext();
        }
        return user;
    }

    public List<CaracteristicasUsuario> getHistorialUsuario(UsuarioRutinaDieta uRD) throws ParseException {
        List<CaracteristicasUsuario> carac = new ArrayList<>();
        Cursor cursor = database.query(MyDBHelper.TABLE_USER_HISTORY,allHistorialColums,"idusuario="+uRD.getId_usuario(),null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int edad =cursor.getInt(1);
            int altura = cursor.getInt(2);
            double peso = cursor.getDouble(3);
            String fecha = (cursor.getString(4));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(fecha);
            carac.add(new CaracteristicasUsuario(altura,peso,edad,date));
            cursor.moveToNext();
        }
        return carac;
    }

    public List<Usuario> getUsers(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Cursor cursor = database.query(MyDBHelper.TABLE_USUARIOS,allUserColums,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Usuario user = new Usuario();
            user.setNombre(cursor.getString(1));
            user.setApellidos(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setContrasenia(cursor.getString(4));
            usuarios.add(user);
            cursor.moveToNext();
        }
        return usuarios;
    }
    public int getUserId(Usuario user){
        Cursor cursor = database.query(MyDBHelper.TABLE_USUARIOS,allUserColums,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(3).equals(user.getEmail()))
                return cursor.getInt(0);
            cursor.moveToNext();
        }
        return -1 ;

    }

    public boolean checkUserExist(String email,String pass){
        List<Usuario> usersDB=getUsers();
        if(usersDB.size()!=0) {
            for (Usuario user : usersDB
                    ) {
                if (user.getEmail().equals(email) && user.getContrasenia().equals(pass))
                    return true;
            }
        }
        return false;
    }

    public boolean checkUserRutinaDietaExist(UsuarioRutinaDieta uRD){
        Cursor cursor = database.query(MyDBHelper.TABLE_RUTINADIETA,allrutinadietaColums,null,null,null,null,null);
        int idUser=uRD.getId_usuario();
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getInt(0)==idUser)
               return true;
            cursor.moveToNext();
        }
        return false;
    }

    public long createRutinaDieta(UsuarioRutinaDieta uRD){
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.COLUMN_ID_USUARIO,uRD.getId_usuario());
        values.put(MyDBHelper.COLUMN_ID_RUTINA,-1);
        values.put(MyDBHelper.COLUMN_ID_DIETA,-1);
        long insertId=database.insert(MyDBHelper.TABLE_RUTINADIETA,null,values);
        return insertId;
    }

    public long createEdadAlturaPeso(int id, int edad,double peso,int altura,Date date){
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.COLUMN_ID_USUARIO_HISTORY,id);
        values.put(MyDBHelper.COLUMN_ALTURA,altura);
        values.put(MyDBHelper.COLUMN_PESO,peso);
        values.put(MyDBHelper.COLUMN_EDAD,edad);
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
        values.put(MyDBHelper.COLUMN_FECHA,fecha);
        long insertId= database.insert(MyDBHelper.TABLE_USER_HISTORY,null,values);
        return insertId;

    }

    public void showUserRutinaDieta(){
        Cursor cursor = database.query(MyDBHelper.TABLE_RUTINADIETA,allrutinadietaColums,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            UsuarioRutinaDieta uRD = new UsuarioRutinaDieta();
            uRD.setId_usuario(cursor.getInt(0));
            uRD.setId_rutina(cursor.getInt(1));
            uRD.setId_dieta(cursor.getInt(2));
            Log.d("UserRutina",uRD.toString());
            cursor.moveToNext();
    }
}

    public void addRutinaUser(UsuarioRutinaDieta user,int idRutina,String dificultad){
        int idUser = user.getId_usuario();
        ContentValues values = new ContentValues();
        values.put("id_rutina",idRutina);
        values.put("dificultad",dificultad);
        database.update(MyDBHelper.TABLE_RUTINADIETA,values,"idusuario="+idUser,null);
    }
    public void addDietaUser(UsuarioRutinaDieta uRD,int idDieta){
        int idUser=uRD.getId_usuario();
        ContentValues values = new ContentValues();
        values.put("id_dieta",idDieta);
        database.update(MyDBHelper.TABLE_RUTINADIETA,values,"idusuario="+idUser,null);
    }

    public int getRutinaUser(UsuarioRutinaDieta uRD) {
        Cursor cursor = database.query(MyDBHelper.TABLE_RUTINADIETA, allrutinadietaColums, "idusuario=" + uRD.getId_usuario(), null, null, null, null);
        cursor.moveToFirst();
        int idRutina=-1;
        while (!cursor.isAfterLast()) {
            idRutina = cursor.getInt(1);
            cursor.moveToNext();

        }
        return idRutina;
    }

    public int getDietaUser(UsuarioRutinaDieta uRD){
        Cursor cursor = database.query(MyDBHelper.TABLE_RUTINADIETA, allrutinadietaColums, "idusuario=" + uRD.getId_usuario(), null, null, null, null);
        cursor.moveToFirst();
        int idDieta=-1;
        while (!cursor.isAfterLast()) {
            idDieta = cursor.getInt(2);
            cursor.moveToNext();

        }
        return idDieta;
    }

    public String getDificultadRutina(UsuarioRutinaDieta uRD) {
        Cursor cursor = database.query(MyDBHelper.TABLE_RUTINADIETA, allrutinadietaColums, "idusuario=" + uRD.getId_usuario(), null, null, null, null);
        cursor.moveToFirst();
        String dificultad = "";
        while (!cursor.isAfterLast()) {
            dificultad = cursor.getString(3);
            cursor.moveToNext();
        }
        return dificultad;
    }


}
