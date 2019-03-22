package com.example.scar.gym.Datos;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Datos implements Parcelable {

    private Context context;

    private List<Rutina> rutinas;
    private List<Ejercicio> ejercicios;
    private List<Dieta> dietas;

    public Datos(Context context){
        this.context = context;
        cargarRutinas();
        cargarEjercicios();
        cargarDietas();
    }


    protected Datos(Parcel in) {
        rutinas = in.createTypedArrayList(Rutina.CREATOR);
        ejercicios = in.createTypedArrayList(Ejercicio.CREATOR);
        dietas = in.createTypedArrayList(Dieta.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(rutinas);
        dest.writeTypedList(ejercicios);
        dest.writeTypedList(dietas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Datos> CREATOR = new Creator<Datos>() {
        @Override
        public Datos createFromParcel(Parcel in) {
            return new Datos(in);
        }

        @Override
        public Datos[] newArray(int size) {
            return new Datos[size];
        }
    };

    public Ejercicio buscarEjercicio(String nombre){
        for(Ejercicio ej: ejercicios){
            if(ej.getNombre().equals(nombre))
                return ej;
        }
        return null;
    }

    public void cargarRutinas() {
        String cadena = null;
        try {
            InputStream inputStream = context.getAssets().open("rutinas.json");
            int sizeOfJSONFile = inputStream.available();
            byte[] bytes = new byte[sizeOfJSONFile];
            inputStream.read(bytes);
            inputStream.close();
            cadena = new String(bytes, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String json = cadena;
        rutinas = new ArrayList<>();
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            JSONArray json_array = object.optJSONArray("rutinas");
            for (int i = 0; i < json_array.length(); i++) {
                rutinas.add(new Rutina(json_array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cargarEjercicios() {
        String cadena = null;
        try {
            InputStream inputStream = context.getAssets().open("ejercicios.json");
            int sizeOfJSONFile = inputStream.available();
            byte[] bytes = new byte[sizeOfJSONFile];
            inputStream.read(bytes);
            inputStream.close();
            cadena = new String(bytes, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String json = cadena;
        ejercicios = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray json_array = object.optJSONArray("ejercicios");
            for (int i = 0; i < json_array.length(); i++) {
                ejercicios.add(new Ejercicio(json_array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cargarDietas() {
        String cadena = null;
        try {
            InputStream inputStream = context.getAssets().open("dietas.json");
            int sizeOfJSONFile = inputStream.available();
            byte[] bytes = new byte[sizeOfJSONFile];
            inputStream.read(bytes);
            inputStream.close();
            cadena = new String(bytes, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String json = cadena;
        dietas = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray json_array = object.optJSONArray("dietas");
            for (int i = 0; i < json_array.length(); i++) {
                dietas.add(new Dieta(json_array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cambiarDificultad(String nuevaDificultad) {
        for(Ejercicio ej: ejercicios){
            ej.setDificultad(nuevaDificultad);
        }
    }

    public Rutina getRutinaById(int id){
        for(Rutina r: rutinas){
            if(r.getId() == id)
                return r;
        }
        return null;
    }

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<Dieta> getDietas() { return dietas; }

    public void setDietas(List<Dieta> dietas) { this.dietas = dietas; }

    public DiaDieta getDietaByIdAndDay(int idDieta, String dia) {
        Dieta aux = null;
        for(Dieta d: dietas){
            if(d.getId() == idDieta){
                aux = d;
                break;
            }
        }
        for(DiaDieta dd: aux.getDiasSemana()){
            if(dd.getNombre().equals(dia))
                return dd;
        }
        return null;
    }
}
