package Dominio;

import java.util.ArrayList;

public class Partida {

    private Tablero tablero;
    private Jugador jGanador;
    private Jugador jPerdedor;
    private ArrayList<Jugador> listaJugadores;

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero unTablero) {
        tablero = unTablero;
    }

    public Jugador getJGanador() {
        return jGanador;
    }

    public void setJGanador(Jugador unJGanador) {
        jGanador = unJGanador;
    }

    public Jugador getJPerdedor() {
        return jPerdedor;
    }

    public void setJPerdedor(Jugador unJPerdedor) {
        this.jPerdedor = unJPerdedor;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public Partida() {
        Jugador unJ = new Jugador();
        Tablero unT = new Tablero();
        this.setTablero(unT);
        this.setJGanador(unJ);
        this.setJPerdedor(unJ);
        this.listaJugadores = new ArrayList<>();
    }

    public Partida(Tablero unTablero, Jugador unJGanador, Jugador unJPerdedor, ArrayList<Jugador> listaJugadores) {
        this.setTablero(unTablero);
        this.setJGanador(unJGanador);
        this.setJPerdedor(unJPerdedor);
        listaJugadores = new ArrayList<>();
    }

    public void precargarFija() {
        tablero.precargaFija();
    }

    public void precargaAzar() {
        tablero.precargaAzar();
    }

    public void partidasJugadas(Jugador unJ1, Jugador unJ2) {
        unJ1.setPartidasJugadas(unJ1.getPartidasJugadas() + 1);
        unJ2.setPartidasJugadas(unJ2.getPartidasJugadas() + 1);
    }

    public void partidasGanadas(Jugador unJPerdedor, Jugador unJGanador) {
        this.setJPerdedor(unJPerdedor);
        this.setJGanador(unJGanador);
        unJPerdedor.setPartidasPerdidas(unJPerdedor.getPartidasPerdidas() + 1);
        unJGanador.setPartidasGanadas(unJGanador.getPartidasGanadas() + 1);
    }

    public void partidasEmpatadas(Jugador unJ1, Jugador unJ2) {
        unJ1.setPartidasEmpatadas(unJ1.getPartidasEmpatadas() + 1);
        unJ1.setPartidasEmpatadas(unJ2.getPartidasEmpatadas() + 1);
    }

    public static int conversorFila(String coordenadas) {
        int numeroFila = coordenadas.charAt(0) - 65;
        return numeroFila;
    }

    public static int conversorColumna(String coordenadas) {
        int numeroColumna;
        if (coordenadas.length() == 1) {
            numeroColumna = coordenadas.charAt(0) - 49;
        } else {
            try {
                numeroColumna = Integer.parseInt(coordenadas);
            } catch (NumberFormatException ex) {
                numeroColumna = -1;
            }
        }
        return numeroColumna;
    }

    public int ingresoJuego(String accion) {
        int seRealiza = 0;
        String h1 = accion.toUpperCase();
        String h2 = h1.substring(0, 1);
        if (h2.equals("M") || h2.equals("S") || h2.equals("B")) {
            String h = h1.substring(0, 2);
            if (h.equals("M ") && (accion.length() >= 5 && accion.length() <= 8)) {
                seRealiza = 1;
            } else {
                if (h.equals("S ") && (accion.length() >= 5 && accion.length() <= 8)) {
                    seRealiza = 2;
                } else {
                    if (h.equals("B ") && (accion.length() > 7 && accion.length() < 12)) {
                        seRealiza = 3;
                    }
                }
            }
        } else {
            if (h2.equals("X") || h2.equals("E")) {
                String h = h1.substring(0, 1);
                if (h.equals("X") && accion.length() == 1) {
                    seRealiza = 4;
                } else {
                    if (h.equals("E") && accion.length() == 1) {
                        seRealiza = 5;
                    }
                }
            } else {
                seRealiza = 6;
            }
        }
        return seRealiza;
    }

    public boolean partidaGanada(int fila, int columna, int fichasInv) {
        int cantFichas = 1;
        int cantFichasInv = 0;
        boolean esInv = false;
        if (this.tablero.getTableroFichas()[fila][columna].getLetra().equals("r")
                || this.tablero.getTableroFichas()[fila][columna].getLetra().equals("b")) {
            esInv = true;
        }
        int i = fila + 1;
        while ((i < 12) && (this.tablero.hayFicha(i, columna))
                && this.tablero.getTableroFichas()[i][columna].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {

            if (this.tablero.getTableroFichas()[i][columna].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][columna].getLetra().equals("b")) {
                cantFichasInv++;
            }
            cantFichas++;
            i++;
        }
        i = fila - 1;
        while ((i >= 0) && (this.tablero.hayFicha(i, columna))
                && this.tablero.getTableroFichas()[i][columna].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {

            if (this.tablero.getTableroFichas()[i][columna].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][columna].getLetra().equals("b")) {
                cantFichasInv++;
            }
            cantFichas++;
            i--;
        }
        if ((cantFichas >= 4) && (cantFichasInv >= fichasInv)) {
            return true;
        }
        cantFichas = 1;
        if (esInv) {
            cantFichasInv = 1;
        } else {
            cantFichasInv = 0;
        }
        int j = columna + 1;
        while ((j < 13) && (this.tablero.hayFicha(fila, j))
                && this.tablero.getTableroFichas()[fila][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {

            if (this.tablero.getTableroFichas()[fila][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[fila][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            cantFichas++;
            j++;
        }
        j = columna - 1;
        while ((j >= 0) && (this.tablero.hayFicha(fila, j))
                && this.tablero.getTableroFichas()[fila][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {

            if (this.tablero.getTableroFichas()[fila][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[fila][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            cantFichas++;
            j--;
        }
        if ((cantFichas >= 4) && (cantFichasInv >= fichasInv)) {
            return true;
        }
        cantFichas = 1;
        if (esInv) {
            cantFichasInv = 1;
        } else {
            cantFichasInv = 0;
        }
        i = fila + 1;
        j = columna + 1;
        while ((j < 13) && (i < 12) && (this.tablero.hayFicha(i, j))
                && this.tablero.getTableroFichas()[i][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {
            cantFichas++;

            if (this.tablero.getTableroFichas()[i][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            j++;
            i++;
        }
        j = columna - 1;
        i = fila - 1;
        while ((j >= 0) && (i >= 0) && (this.tablero.hayFicha(i, j))
                && this.tablero.getTableroFichas()[i][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {
            cantFichas++;

            if (this.tablero.getTableroFichas()[i][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            j--;
            i--;
        }
        if ((cantFichas >= 4) && (cantFichasInv >= fichasInv)) {
            return true;
        }

        cantFichas = 1;
        if (esInv) {
            cantFichasInv = 1;
        } else {
            cantFichasInv = 0;
        }
        i = fila - 1;
        j = columna + 1;
        while ((j < 13) && (i >= 0) && (this.tablero.hayFicha(i, j))
                && this.tablero.getTableroFichas()[i][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {
            cantFichas++;

            if (this.tablero.getTableroFichas()[i][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            j++;
            i--;
        }
        j = columna - 1;
        i = fila + 1;
        while ((j >= 0) && (i < 12) && (this.tablero.hayFicha(i, j))
                && this.tablero.getTableroFichas()[i][j].getLetra().toUpperCase().equals(this.tablero.getTableroFichas()[fila][columna].getLetra().toUpperCase())) {
            cantFichas++;

            if (this.tablero.getTableroFichas()[i][j].getLetra().equals("r")
                    || this.tablero.getTableroFichas()[i][j].getLetra().equals("b")) {
                cantFichasInv++;
            }
            j--;
            i++;
        }
        return ((cantFichas >= 4) && (cantFichasInv >= fichasInv));
    }
}
