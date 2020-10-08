package Dominio;

public class Ficha {

    private int fila;
    private int columna;
    private String letra;

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

    public Ficha() {
        this.setFila(0);
        this.setColumna(0);
        this.setLetra("");
    }

    public Ficha(int unaFila, int unaColumna, boolean unColor, String unaLetra) {
        this.setFila(unaFila);
        this.setColumna(unaColumna);
        this.setLetra(unaLetra);
    }

    @Override
    public String toString() {
        String r;
        if (letra.contains("B")) {
            r = "B";
        } else {
            r = "R";
        }
        return r;
    }
}
