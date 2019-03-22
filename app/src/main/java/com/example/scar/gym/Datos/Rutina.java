package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rutina implements Parcelable {

    private int id;
    private String nombre;
    private String imagen;
    private List<DiaSemana> principiante;
    private List<DiaSemana> intermedio;
    private List<DiaSemana> avanzado;
    private String resumen;
    private String descripcion;


    public Rutina(int id, String nombre, String resumen, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Rutina(JSONObject json) throws JSONException {
        this.id = json.getInt("id");
        this.nombre = json.getString("nombre");
        this.resumen = json.getString("resumen");
        this.descripcion = json.getString("descripcion");
        this.imagen = json.getString("imagen");
        this.cargarEjerciciosRutina(json);

    }

    protected Rutina(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        imagen = in.readString();
        principiante = in.createTypedArrayList(DiaSemana.CREATOR);
        intermedio = in.createTypedArrayList(DiaSemana.CREATOR);
        avanzado = in.createTypedArrayList(DiaSemana.CREATOR);
        resumen = in.readString();
        descripcion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(imagen);
        dest.writeTypedList(principiante);
        dest.writeTypedList(intermedio);
        dest.writeTypedList(avanzado);
        dest.writeString(resumen);
        dest.writeString(descripcion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rutina> CREATOR = new Creator<Rutina>() {
        @Override
        public Rutina createFromParcel(Parcel in) {
            return new Rutina(in);
        }

        @Override
        public Rutina[] newArray(int size) {
            return new Rutina[size];
        }
    };

    private void cargarEjerciciosRutina(JSONObject json) throws JSONException {
        this.principiante = new ArrayList<>();
        JSONArray princ = json.getJSONObject("principiante").getJSONArray("diasSemana");
        for(int i=0; i<princ.length(); i++){
            this.principiante.add(new DiaSemana(princ.getJSONObject(i)));
        }

        this.intermedio = new ArrayList<>();
        JSONArray inter = json.getJSONObject("intermedio").getJSONArray("diasSemana");
        for(int i=0; i<inter.length(); i++){
            this.intermedio.add(new DiaSemana(inter.getJSONObject(i)));
        }

        this.avanzado = new ArrayList<>();
        JSONArray avanz = json.getJSONObject("avanzado").getJSONArray("diasSemana");
        for(int i=0; i<avanz.length(); i++){
            this.avanzado.add(new DiaSemana(avanz.getJSONObject(i)));
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

    public List<DiaSemana> getPrincipiante() {
        return principiante;
    }

    public void setPrincipiante(List<DiaSemana> principiante) {
        this.principiante = principiante;
    }

    public List<DiaSemana> getIntermedio() {
        return intermedio;
    }

    public void setIntermedio(List<DiaSemana> intermedio) {
        this.intermedio = intermedio;
    }

    public List<DiaSemana> getAvanzado() {
        return avanzado;
    }

    public void setAvanzado(List<DiaSemana> avanzado) {
        this.avanzado = avanzado;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DiaSemana> getEjPorDificultad(String dif) {
        if(dif.equals("principiante"))
            return getPrincipiante();
        else if(dif.equals("intermedio"))
            return getIntermedio();
        else
            return getAvanzado();
    }
}