package Dominio;

import java.util.Random;

public class Tablero {

    private Ficha[][] tableroFichas;
    private boolean[][] tableroBaldosas;

    public Ficha[][] getTableroFichas() {
        return tableroFichas;
    }

    public void setTableroFichas(Ficha[][] tableroFichas) {
        this.tableroFichas = tableroFichas;
    }

    public boolean[][] getTableroBaldosas() {
        return tableroBaldosas;
    }

    public void setTableroBaldosas(boolean[][] tableroBaldosas) {
        this.tableroBaldosas = tableroBaldosas;
    }

    public Tablero(Ficha[][] tabF, boolean[][] tabB) {
        this.setTableroFichas(tabF);
        this.setTableroBaldosas(tabB);
    }

    public Tablero() {
        Ficha[][] tFichas = new Ficha[12][13];
        this.setTableroFichas(tFichas);
        boolean[][] tBaldosas = new boolean[12][13];
        this.setTableroBaldosas(tBaldosas);
        for (int i = 4; i < 8; i++) {
            for (int j = 4; j < 9; j++) {
                this.getTableroBaldosas()[i][j] = (true);
            }
        }
    }

    public boolean coordenadaValida(int filaDestino, int columnaDestino) {
        boolean ok = false;
        if ((filaDestino >= 0 && filaDestino <= 11) && (columnaDestino >= 0 && columnaDestino <= 12)) {
            ok = true;
        }
        return ok;
    }

    public boolean hayBaldosa(int numeroFila, int numeroColumna) {
        return this.getTableroBaldosas()[numeroFila][numeroColumna];
    }

    public boolean hayBaldosaArriba(int numeroFila, int numeroColumna) {
        if (numeroFila == 0) {
            return false;
        } else {
            return this.getTableroBaldosas()[numeroFila - 1][numeroColumna];
        }
    }

    public boolean hayBaldosaIzquierda(int numeroFila, int numeroColumna) {
        if (numeroColumna == 0) {
            return false;
        } else {
            return this.getTableroBaldosas()[numeroFila][numeroColumna - 1];
        }
    }

    public boolean hayBaldosaArribaIzquierda(int numeroFila, int numeroColumna) {
        if (numeroFila == 0 || numeroColumna == 0) {
            return false;
        } else {
            boolean[][] mat = this.getTableroBaldosas();
            return mat[numeroFila - 1][numeroColumna - 1];
        }
    }

    public boolean hayFicha(int numeroFila, int numeroColumna) {
        return (this.getTableroFichas()[numeroFila][numeroColumna] != null);
    }

    public void precargaFija() {
        this.getTableroFichas()[4][4] = new Ficha(4, 4, true, "B");
        this.getTableroFichas()[4][6] = new Ficha(4, 6, true, "B");
        this.getTableroFichas()[5][7] = new Ficha(5, 7, true, "B");
        this.getTableroFichas()[6][6] = new Ficha(6, 6, true, "B");
        this.getTableroFichas()[6][8] = new Ficha(6, 8, true, "B");
        this.getTableroFichas()[7][4] = new Ficha(7, 4, true, "B");
        this.getTableroFichas()[4][5] = new Ficha(4, 5, false, "R");
        this.getTableroFichas()[5][4] = new Ficha(5, 4, false, "R");
        this.getTableroFichas()[5][6] = new Ficha(5, 6, false, "R");
        this.getTableroFichas()[6][4] = new Ficha(6, 4, false, "R");
        this.getTableroFichas()[6][7] = new Ficha(6, 7, false, "R");
        this.getTableroFichas()[7][5] = new Ficha(7, 5, false, "R");
    }

    public void precargaAzar() {
        int i = 1;
        Random rnd = new Random();
        while (i < 7) {
            int fila = (int) (rnd.nextDouble() * 4 + 4);
            int columna = (int) (rnd.nextDouble() * 5 + 4);
            if (tableroFichas[fila][columna] == null) {
                tableroFichas[fila][columna] = new Ficha(fila, columna, true, "B");
                i++;
            }
        }
        while (i < 13) {
            int fila = (int) (rnd.nextDouble() * 4 + 4);
            int columna = (int) (rnd.nextDouble() * 5 + 4);
            if (tableroFichas[fila][columna] == null) {
                tableroFichas[fila][columna] = new Ficha(fila, columna, false, "R");
                i++;
            }
        }
    }

    public boolean agregarFicha(int fila, int columna, String color) {
        boolean ok = false;
        if (this.coordenadaValida(fila, columna)) {
            if (hayBaldosa(fila, columna) && !hayFicha(fila, columna)) {
                this.getTableroFichas()[fila][columna] = new Ficha(fila, columna, true, color);
                ok = true;
            }
        }
        return ok;
    }

    public String getFicha(int f, int c) {
        return tableroFichas[f][c].getLetra();
    }
}
