/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Sistema;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Rodo
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     Sistema miSistema = new Sistema();
        miSistema = miSistema.obtenerSistema();
        MenuPrincipal laInterfaz = new MenuPrincipal(miSistema);
        laInterfaz.setVisible(true);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = laInterfaz.getSize();
        laInterfaz.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
    }
    
}
