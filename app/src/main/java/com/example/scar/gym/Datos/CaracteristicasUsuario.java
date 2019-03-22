package com.example.scar.gym.Datos;

import java.util.Date;

public class CaracteristicasUsuario {

    private int altura;
    private double peso;
    private int edad;
    private Date fecha;

    public CaracteristicasUsuario(int altura, double peso, int edad, Date fecha) {
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
        this.fecha=fecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
