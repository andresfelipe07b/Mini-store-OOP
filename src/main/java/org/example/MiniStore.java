package org.example;

import org.example.service.ITiendaService;
import org.example.service.TiendaServiceImpl;
import org.example.view.MenuPrincipal;

public class MiniStore {
    public static void main(String[] args) {
        ITiendaService tiendaService = new TiendaServiceImpl();
        MenuPrincipal menu = new MenuPrincipal(tiendaService);
        menu.mostrarMenu();
    }
}
