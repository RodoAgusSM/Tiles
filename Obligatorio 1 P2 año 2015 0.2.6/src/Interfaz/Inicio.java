package Interfaz;

import Dominio.Sistema;

public class Inicio {

    public static void main(String[] args) {
        Sistema miSistema = new Sistema();
        Interfaz laInterfaz = new Interfaz(miSistema);
        laInterfaz.menuPrincipal();
    }

}
