package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiaSemana implements Parcelable {

    private String nombre;
    private List<String> ejercicios;

    public DiaSemana(JSONObject json) throws JSONException {
        this.nombre = json.getString("dia");
        this.ejercicios = new ArrayList<>();
        JSONArray array = json.getJSONArray("ejercicios");
        for(int i=0; i<array.length(); i++){
            this.ejercicios.add(array.getString(i));
        }
    }

    protected DiaSemana(Parcel in) {
        nombre = in.readString();
        ejercicios = in.createStringArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<String> ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeStringList(ejercicios);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiaSemana> CREATOR = new Creator<DiaSemana>() {
        @Override
        public DiaSemana createFromParcel(Parcel in) {
            return new DiaSemana(in);
        }

        @Override
        public DiaSemana[] newArray(int size) {
            return new DiaSemana[size];
        }
    };
}
