package org.example.service;

import org.example.model.Producto;

public interface ITiendaService {
    void agregarProducto(Producto producto, int stock);
    String listarInventario();
    String comprarProducto(String nombre, int cantidad);
    String obtenerEstadisticas();
    String buscarProducto(String nombre);
    String obtenerTicketFinal();
}
