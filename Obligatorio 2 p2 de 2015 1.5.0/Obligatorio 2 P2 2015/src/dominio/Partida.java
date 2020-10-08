package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Partida implements Serializable {

    private Tablero tablero;
    private Jugador jGanador;
    private Jugador jPerdedor;
    private ArrayList<Jugador> listaJugadores;
    private Jugador jugador1;
    private Jugador jugador2;
    private int cantidadFichasInv;
    private boolean turno;
    private Jugador jugadorJugando;
    private Jugador jugadorEsperando;

    public Partida(Jugador jugador1, Jugador jugador2, int cantidadFichasInv) {
        this.jugador1 = jugador1;
        this.jugador1.setFicha(new Ficha("B"));
        this.jugador2 = jugador2;
        this.jugador2.setFicha(new Ficha("R"));
        this.cantidadFichasInv = cantidadFichasInv;
        turno = true;
    }

    public Partida() {
        Jugador unJ = new Jugador();
        Tablero unT = new Tablero();
        this.setJugador1(jugador1);
        this.setJugador2(jugador2);
        this.setTablero(unT);
        this.setJGanador(unJ);
        this.setJPerdedor(unJ);
        this.setTurno(turno);
        this.listaJugadores = new ArrayList<>();
    }

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

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador unJugador1) {
        this.jugador1 = unJugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador unJugador2) {
        this.jugador2 = unJugador2;
    }

    public Jugador jugadorEnJugando() {
        if (turno) {
            return jugador1;
        } else {
            return jugador2;
        }
    }

    public Jugador jugadorEnEspera() {
        if (turno) {
            return jugador1;
        } else {
            return jugador2;
        }
    }

    public int getCantFichasInv() {
        return cantidadFichasInv;
    }

    public void setCantFichasInv(int unaCantidadFichasInv) {
        this.cantidadFichasInv = unaCantidadFichasInv;
    }

    public boolean getTurno() {
        return turno;
    }

    public void setTurno(boolean unTurno) {
        this.turno = unTurno;
    }

    public Jugador getJugadorJugando() {
        return jugadorJugando;
    }

    public void setJugadorJugando(Jugador unJugJugando) {
        this.jugadorJugando = unJugJugando;
    }

    public Jugador getJugadorEsperando() {
        return jugadorEsperando;
    }

    public void setJugadorEsperando(Jugador unJugEsperando) {
        this.jugadorEsperando = unJugEsperando;
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
        int numeroFila = coordenadas.charAt(0) - 64;
        return numeroFila;
    }

    public static int conversorColumna(String coordenadas) {
        int numeroColumna;
        if (coordenadas.length() == 1) {
            numeroColumna = coordenadas.charAt(0) - 48;
        } else {
            try {
                numeroColumna = Integer.parseInt(coordenadas);
            } catch (NumberFormatException ex) {
                numeroColumna = -1;
            }
        }
        return numeroColumna;
    }

    private boolean esFichaInv(int fila, int columna) {
        return (this.tablero.getTableroFichas()[fila][columna].getLetra().equals("r")
                || this.tablero.getTableroFichas()[fila][columna].getLetra().equals("b"));
    }

    private boolean validarVertical(int fila, int columna, int fichasInv, int cantFichasInv) {
        int cantFichas = 1;
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
        return ((cantFichas >= 4) && (cantFichasInv >= fichasInv));
    }

    private boolean validarHorizontal(int fila, int columna, int fichasInv, int cantFichasInv) {
        int cantFichas = 1;
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
        return (cantFichas >= 4) && (cantFichasInv >= fichasInv);
    }

    private boolean validarDiagonalIzqDer(int fila, int columna, int fichasInv, int cantFichasInv) {
        int cantFichas = 1;
        int i = fila + 1;
        int j = columna + 1;
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
        return (cantFichas >= 4) && (cantFichasInv >= fichasInv);
    }

    private boolean validarDiagonalDerIzq(int fila, int columna, int fichasInv, int cantFichasInv) {
        int cantFichas = 1;
        int i = fila - 1;
        int j = columna + 1;
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
        return (cantFichas >= 4) && (cantFichasInv >= fichasInv);
    }

    public boolean partidaGanada(int fila, int columna, int fichasInv) {
        int cantFichasInv = 0;
        if (esFichaInv(fila, columna)) {
            cantFichasInv = 1;
        }
        return (validarVertical(fila, columna, fichasInv, cantFichasInv))
                || (validarHorizontal(fila, columna, fichasInv, cantFichasInv))
                || (validarDiagonalDerIzq(fila, columna, fichasInv, cantFichasInv))
                || (validarDiagonalIzqDer(fila, columna, fichasInv, cantFichasInv));
    }

    public boolean fichaCoincideConJugador(Ficha unaFicha) {
        boolean ok = false;
        if (getTurno()) {
            if (unaFicha.getLetra().equals("B") || unaFicha.getLetra().equals("b")) {
                ok = true;
            }
        } else {

            if (unaFicha.getLetra().equals("R") || unaFicha.getLetra().equals("r")) {
                ok = true;
            }

        }
        return ok;
    }

    public boolean moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws Exception {
        boolean ok = false;
        Ficha fichaAux = getTablero().getTableroFichas()[filaOrigen][columnaOrigen];
        if (jugadorEnJugando().getFicha().getLetra().toUpperCase().equals(fichaAux.getLetra().toUpperCase())) {
            getTablero().moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino, fichaAux);
            ok = true;
            setTurno(!getTurno());
        }
        return ok;
    }

    public boolean saltarFicha(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) throws Exception {
        boolean ok = false;
        if (getTablero().saltarFicha(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino, jugadorEnJugando())) {
            setTurno(!getTurno());
            ok = true;
        } else {
            ok = false;
        }
        return ok;
    }

    public boolean moverBaldosa(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, int numeroFilaOFicha, int numeroColumnaOFicha, Jugador jugadorJugando) throws Exception {
        boolean ok = true;
        if (getTablero().moverBaldosa(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino, numeroFilaOFicha, numeroColumnaOFicha, jugadorEnJugando())) {
            ok = true;
            setTurno(!getTurno());
        } else {
            throw new Exception("Imposible mover la badosa");
        }
        return ok;
    }

    public void cambiarIconoNormal(ImageIcon icono) {
        Ficha ficha = new Ficha("R");
        if (turno) {
            ficha = new Ficha("B");
        }
        tablero.cambiarFichasNormales(icono, ficha);
    }

    public void cambiarIconoInvertido(ImageIcon icono) {
        Ficha ficha = new Ficha("r");
        if (turno) {
            ficha = new Ficha("b");
        }
        tablero.cambiarFichasInvertidas(icono, ficha);
    }
}
