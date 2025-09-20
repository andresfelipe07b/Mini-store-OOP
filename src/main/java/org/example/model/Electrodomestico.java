package org.example.model;

public class Electrodomestico extends Producto {

    public Electrodomestico(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getDescripcion() {
        return "Electrodoméstico: " + getNombre();
    }
}
