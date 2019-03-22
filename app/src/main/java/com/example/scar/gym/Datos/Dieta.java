package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dieta implements Parcelable{

    private int id;
    private String nombre;
    private String imagen;
    private String descripcion;
    private List<DiaDieta> diasSemana;

    public Dieta(JSONObject json) throws JSONException {
        this.id = json.getInt("id");
        this.nombre = json.getString("nombre");
        this.descripcion = json.getString("descripcion");
        this.imagen = json.getString("imagen");
        this.cargarDiasDieta(json);
    }

    protected Dieta(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        imagen = in.readString();
        diasSemana = in.createTypedArrayList(DiaDieta.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(imagen);
        dest.writeTypedList(diasSemana);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dieta> CREATOR = new Creator<Dieta>() {
        @Override
        public Dieta createFromParcel(Parcel in) {
            return new Dieta(in);
        }

        @Override
        public Dieta[] newArray(int size) {
            return new Dieta[size];
        }
    };

    private void cargarDiasDieta(JSONObject json) throws JSONException {
        this.diasSemana = new ArrayList<>();
        JSONArray dias = json.getJSONArray("diasSemana");
        for(int i=0; i<dias.length(); i++){
            this.diasSemana.add(new DiaDieta(dias.getJSONObject(i)));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DiaDieta> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaDieta> diasSemana) {
        this.diasSemana = diasSemana;
    }
}
