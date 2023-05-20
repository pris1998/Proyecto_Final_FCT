package com.example.proyecto.activities.doctores;

import java.util.ArrayList;

public class Dieta {

    private String tipo;
   private ArrayList<Alimentos> alimento;

    public Dieta(String tipo, ArrayList<Alimentos> alimento) {
        this.tipo = tipo;
        this.alimento = alimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Alimentos> getAlimento() {
        return alimento;
    }

    public void setAlimento(ArrayList<Alimentos> alimento) {
        this.alimento = alimento;
    }
}