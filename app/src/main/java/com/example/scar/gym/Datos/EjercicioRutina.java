package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EjercicioRutina implements Parcelable {

    private List<String> days;
    private String nombre;

    public EjercicioRutina(String nombre, List<String> days) {
        this.nombre = nombre;
        this.days = days;
    }

    public EjercicioRutina(JSONObject o) throws JSONException {
        this.nombre = o.getString("nombre");
        this.days = new ArrayList<>();
        this.getDiasSemana(o.getJSONArray("diasSemana"));
    }

    private void getDiasSemana(JSONArray diasSemana) throws JSONException {
        for(int i=0; i<diasSemana.length(); i++){
            this.days.add(diasSemana.getString(i));
        }
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(days);
        dest.writeString(nombre);
    }

    protected EjercicioRutina(Parcel in) {
        days = in.createStringArrayList();
        nombre = in.readString();
    }

    public static final Creator<EjercicioRutina> CREATOR = new Creator<EjercicioRutina>() {
        @Override
        public EjercicioRutina createFromParcel(Parcel in) {
            return new EjercicioRutina(in);
        }

        @Override
        public EjercicioRutina[] newArray(int size) {
            return new EjercicioRutina[size];
        }
    };
}

