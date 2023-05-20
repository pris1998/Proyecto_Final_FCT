package com.example.proyecto.activities.doctores;

public class Alimentos {
    private String tipo ;
    private String name;

    public Alimentos(String tipo, String name) {
        this.tipo = tipo;
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
