package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiaDieta implements Parcelable {

    private String nombre;
    private List<String> desayuno;
    private List<String> almuerzo;
    private List<String> comida;
    private List<String> merienda;
    private List<String> cena;

    public DiaDieta(JSONObject json) throws JSONException {
        this.nombre = json.getString("dia");
        desayuno = cargarListaPlatos(json, "desayuno");
        almuerzo = cargarListaPlatos(json, "mediamañana");
        comida = cargarListaPlatos(json, "comida");
        merienda = cargarListaPlatos(json, "merienda");
        cena = cargarListaPlatos(json, "cena");
    }

    protected DiaDieta(Parcel in) {
        nombre = in.readString();
        desayuno = in.createStringArrayList();
        almuerzo = in.createStringArrayList();
        comida = in.createStringArrayList();
        merienda = in.createStringArrayList();
        cena = in.createStringArrayList();
    }

    public static final Creator<DiaDieta> CREATOR = new Creator<DiaDieta>() {
        @Override
        public DiaDieta createFromParcel(Parcel in) {
            return new DiaDieta(in);
        }

        @Override
        public DiaDieta[] newArray(int size) {
            return new DiaDieta[size];
        }
    };

    private List<String> cargarListaPlatos(JSONObject json, String y) throws JSONException {
        ArrayList<String> lista = new ArrayList<>();
        JSONArray array = json.getJSONArray(y);
        for(int i=0; i<array.length(); i++){
            lista.add(array.getString(i));
        }
        return lista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeStringList(desayuno);
        dest.writeStringList(almuerzo);
        dest.writeStringList(comida);
        dest.writeStringList(merienda);
        dest.writeStringList(cena);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesayuno() {
        String res="";
        for(String s: this.desayuno){
            res+=s+"\n";
        }
        return res;
    }

    public void setDesayuno(List<String> desayuno) {
        this.desayuno = desayuno;
    }

    public String getAlmuerzo() {
        String res="";
        for(String s: this.almuerzo){
            res+=s+"\n";
        }
        return res;
    }

    public void setAlmuerzo(List<String> almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getComida() {
        String res="";
        for(String s: this.comida){
            res+=s+"\n";
        }
        return res;
    }

    public void setComida(List<String> comida) {
        this.comida = comida;
    }

    public String getMerienda() {
        String res="";
        for(String s: this.merienda){
            res+=s+"\n";
        }
        return res;
    }

    public void setMerienda(List<String> merienda) {
        this.merienda = merienda;
    }

    public String getCena() {
        String res="";
        for(String s: this.cena){
            res+=s+"\n";
        }
        return res;
    }

    public void setCena(List<String> cena) {
        this.cena = cena;
    }
}
