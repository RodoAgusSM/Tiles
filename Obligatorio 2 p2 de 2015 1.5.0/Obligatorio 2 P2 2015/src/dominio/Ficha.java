package dominio;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Ficha implements Serializable {

    private int fila;
    private int columna;
    private String letra;
    private Color c;
    private ImageIcon icono;
    private ImageIcon iconoInvertido;

    public Ficha() {
        this.setFila(0);
        this.setColumna(0);
        this.setLetra("");
    }

    public Ficha(String unaLetra) {
        this.setLetra(unaLetra);
    }

    public Ficha(int unaFila, int unaColumna, String unaLetra) {
        this.setFila(unaFila);
        this.setColumna(unaColumna);
        this.setLetra(unaLetra);
    }

    public ImageIcon getIconoInvertido() {
        return iconoInvertido;
    }

    public void setIconoInvertido(ImageIcon iconoInvertido) {
        this.iconoInvertido = iconoInvertido;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color unC) {
        this.c = unC;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int unaFila) {
        fila = unaFila;
    }

    public void setColumna(int unaColumna) {
        this.columna = unaColumna;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String unaLetra) {
        this.letra = unaLetra;
    }

    @Override
    public String toString() {
        String r = "";
        if (letra.contains("B")) {
            r = "B";
        } else {
            if (letra.contains("R")) {
                r = "R";
            } else {
                if (letra.contains("b")) {
                    r = "b";
                } else {
                    if (letra.contains("r")) {
                        r = "r";
                    }
                }
            }
        }
        return r;
    }

    public Icon getIconoActual() {
        Icon ico;
        if (letra.contains("B") || letra.contains("R")) {
            ico = icono;
        } else {
            ico = iconoInvertido;
        }
        return ico;
    }
}
