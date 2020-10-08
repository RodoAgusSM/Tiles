package manejoArchivos;

import dominio.Partida;
import dominio.Tablero;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rodo
 */
public class LevantarTablero {

    public static Tablero leerTablero(String path) throws FileNotFoundException, IOException {
        ArchivoLectura archivoLectura = new ArchivoLectura(path);
        Tablero tablero = new Tablero();
        while (archivoLectura.hayMasLineas()) {
            String linea = archivoLectura.linea();
            String partes[] = linea.split(" ");
            String color = partes[0];
            int fila = Partida.conversorFila(partes[1].charAt(0) + "");
            int columna = Integer.parseInt(partes[1].substring(1));
            tablero.agregarFicha(fila, columna, color);
        }
        return tablero;
    }
}
