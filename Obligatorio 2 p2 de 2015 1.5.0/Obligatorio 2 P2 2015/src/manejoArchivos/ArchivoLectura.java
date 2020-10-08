package manejoArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoLectura {

    private String linea;
    private BufferedReader in;

    public ArchivoLectura(String unNombre) throws FileNotFoundException {
        linea = "";
        in = new BufferedReader(new FileReader(unNombre));

    }

    public boolean hayMasLineas() {
        try {
            linea = in.readLine();
        } catch (Exception e) {
            linea = null;
        }
        return linea != null;
    }

    public String linea() {
        return linea;
    }

    public void cerrar() throws IOException {
        in.close();

    }
}
