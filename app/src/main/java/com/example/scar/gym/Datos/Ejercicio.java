package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Ejercicio implements Parcelable {

    private String nombre;
    private String imagen;
    private String modo;
    private String principiante;
    private String intermedio;
    private String avanzado;
    private String dificultad;
    private String descripcion;


    public Ejercicio(String nombre, String imagen, String modo, String principiante, String intermedio, String avanzado, String dificultad, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.modo = modo;
        this.principiante = principiante;
        this.intermedio = intermedio;
        this.avanzado = avanzado;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
    }

    public Ejercicio(String nombre){
        this.nombre = nombre;
    }

    public Ejercicio(String nombre, String imagen){
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Ejercicio(JSONObject json) throws JSONException {
        this.nombre = json.getString("nombre");
        this.imagen = json.getString("imagen");
        this.modo = json.getString("modo");
        this.principiante = json.getString("principiante");
        this.intermedio = json.getString("intermedio");
        this.avanzado = json.getString("avanzado");
        this.descripcion = json.getString("descripcion");
        this.dificultad = "principiante";
    }

    public Ejercicio(JSONObject json, String dificultad) throws JSONException {
        this(json);
        this.dificultad = dificultad;
    }

    protected Ejercicio(Parcel in) {
        nombre = in.readString();
        imagen = in.readString();
        modo = in.readString();
        principiante = in.readString();
        intermedio = in.readString();
        avanzado = in.readString();
        dificultad = in.readString();
        descripcion = in.readString();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getPrincipiante() {
        return principiante;
    }

    public void setPrincipiante(String principiante) {
        this.principiante = principiante;
    }

    public String getIntermedio() {
        return intermedio;
    }

    public void setIntermedio(String intermedio) {
        this.intermedio = intermedio;
    }

    public String getAvanzado() {
        return avanzado;
    }

    public void setAvanzado(String avanzado) {
        this.avanzado = avanzado;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContador(){
        if(this.dificultad.equals("principiante"))
            return this.principiante;
        if(this.dificultad.equals("intermedio"))
            return this.intermedio;
        if(this.dificultad.equals("avanzado"))
            return this.avanzado;
        return this.principiante;
    }

    public String getContadorTxt(){
        if(this.modo.equals("repeticiones"))
            return "x" + this.getContador();
        else
            return mostrarTiempo();
    }

    private String mostrarTiempo() {
        int seg = Integer.parseInt(this.getContador());
        int min = 0;
        while(seg > 60){
            seg -= 60;
            min++;
        }
        String txtMin, txtSeg;
        if(min < 10)
            txtMin = "0" + min;
        else
            txtMin = "00";
        if(seg < 10)
            txtSeg = "0" + seg;
        else
            txtSeg = Integer.toString(seg);
        return txtMin + ":" + txtSeg;
    }

    public static final Creator<Ejercicio> CREATOR = new Creator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(Parcel in) {
            return new Ejercicio(in);
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(imagen);
        dest.writeString(modo);
        dest.writeString(principiante);
        dest.writeString(intermedio);
        dest.writeString(avanzado);
        dest.writeString(dificultad);
        dest.writeString(descripcion);
    }
}

