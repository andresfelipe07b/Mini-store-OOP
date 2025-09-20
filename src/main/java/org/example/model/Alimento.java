package org.example.model;

public class Alimento extends Producto {

    public Alimento(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getDescripcion() {
        return "Alimento: " + getNombre();
    }
}
