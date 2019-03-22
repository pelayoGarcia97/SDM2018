package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Usuario implements Parcelable {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenia;
   private List<CaracteristicasUsuario> caracteristicas;



    public Usuario(){}



    public Usuario(String nombre, String apellidos, String email, String contrasenia){
        this.apellidos=apellidos;
        this.contrasenia=contrasenia;
        this.nombre=nombre;
        this.email=email;
    }



    public Usuario(String email, String contrasenia){
        this.email=email;

        this.contrasenia=contrasenia;
    }

    protected Usuario(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        contrasenia = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public List<CaracteristicasUsuario> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicasUsuario> caracteristicas) {
        this.caracteristicas = caracteristicas;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(email);
        dest.writeString(contrasenia);


    }
}
