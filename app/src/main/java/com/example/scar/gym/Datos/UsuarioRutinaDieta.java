package com.example.scar.gym.Datos;

import android.os.Parcel;
import android.os.Parcelable;

public class UsuarioRutinaDieta implements Parcelable {

    private int id_usuario;
    private int id_rutina;
    private int id_dieta;

    public UsuarioRutinaDieta(){}


    public UsuarioRutinaDieta(int id){
        this.id_usuario=id;
        this.id_dieta=-1;
        this.id_rutina=-1;
    }

    public UsuarioRutinaDieta(int usuario, int rutina, int dieta) {
        this.id_usuario = usuario;
        this.id_rutina = rutina;
        this.id_dieta = dieta;
    }

    protected UsuarioRutinaDieta(Parcel in) {
        id_usuario = in.readInt();
        id_rutina = in.readInt();
        id_dieta = in.readInt();
    }

    public static final Creator<UsuarioRutinaDieta> CREATOR = new Creator<UsuarioRutinaDieta>() {
        @Override
        public UsuarioRutinaDieta createFromParcel(Parcel in) {
            return new UsuarioRutinaDieta(in);
        }

        @Override
        public UsuarioRutinaDieta[] newArray(int size) {
            return new UsuarioRutinaDieta[size];
        }
    };

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
    }

    public int getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(int id_dieta) {
        this.id_dieta = id_dieta;
    }


    @Override
    public String toString() {
        return "UsuarioRutinaDieta{" +
                "id_usuario=" + id_usuario +
                ", id_rutina=" + id_rutina +
                ", id_dieta=" + id_dieta +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_usuario);
        dest.writeInt(id_rutina);
        dest.writeInt(id_dieta);
    }
}
