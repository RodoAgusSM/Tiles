package dominio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

public class Sistema implements Serializable {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;
    private ArrayList<Tablero> listaTableros;
    private Tablero precargar;

    public Sistema() {
        listaJugadores = new ArrayList<Jugador>();
        listaPartidas = new ArrayList<Partida>();
        listaTableros = new ArrayList<Tablero>();
        precargar = new Tablero();
        precargar.precargaAzar();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void agregarJugador(Jugador unJugador) {
        listaJugadores.add(unJugador);
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void agregarPartida(Partida unaPartida) {
        unaPartida.setTablero(precargar.copia());
        listaPartidas.add(unaPartida);
    }

    public ArrayList<Tablero> getListaTableros() {
        return listaTableros;
    }

    public void agregarTablero(Tablero unTablero) {
        listaTableros.add(unTablero);
    }

    public Tablero getPrecarga() {
        return precargar;
    }

    public Partida partidaActual() {
        return listaPartidas.get(listaPartidas.size() - 1);
    }

    //registro un jugador
    public boolean registrarJugador(String unNombre, int unaEdad, String unAlias) throws Exception {
        boolean ok = false;
        Jugador unJugador = new Jugador(unNombre, unaEdad, unAlias, 0, 0, 0, 0, false);
        if (!aliasUnico(unAlias)) {
            this.listaJugadores.add(unJugador);
            ok = true;
        }
        return ok;
    }

    //verifico que el alias sea unico
    public boolean aliasUnico(String unAlias) {
        boolean ok = false;
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            if (this.listaJugadores.get(i).getAlias().equals(unAlias)) {
                ok = true;
            }
        }
        return ok;
    }

    //creo una lista auxiliar y meto todos los jugadores y los ordeno por nombre
    public ArrayList<Jugador> listaOrdenada() {
        ArrayList<Jugador> lista = new ArrayList<Jugador>();
        for (int i = 0; i < this.getListaJugadores().size(); i++) {
            Jugador unJ = this.getListaJugadores().get(i);
            lista.add(unJ);
        }
        Collections.sort(lista);
        return lista;
    }

    //paso la lista ordenada por parametros y muestro los datos de los jugadores en pantalla
    public void mostrarDatos(ArrayList<Jugador> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Jugador unJugador = lista.get(i);
            System.out.println("El jugador " + unJugador.getAlias()
                    + " jugo un total de " + unJugador.getPartidasJugadas() + " veces"
                    + " y gano un total de " + unJugador.getPartidasGanadas() + " partidas");
        }
    }

    public void precargarFija() {
        precargar.precargaFija();
    }

    public void precargaAzar() {
        precargar.precargaAzar();
    }

    public boolean agregarFichaPrecargaMano(String fila, String columna, String color) {
        return precargar.agregarFicha(Partida.conversorFila(fila), Partida.conversorColumna(columna), color);
    }

    public boolean moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws Exception {
        return partidaActual().moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
    }

    public boolean meterFicha(int numeroF, int numeroC, int numeroFila, int numeroColumna, Jugador jugadorJugando) {
        boolean ok = false;
        if (partidaActual().getTablero().coordenadaValida(numeroFila, numeroColumna)) {
            if (partidaActual().getTablero().hayBaldosa(numeroFila, numeroColumna)) {
                if (partidaActual().getTablero().hayFicha(numeroF, numeroC)) {
                    if (!partidaActual().getTablero().hayFicha(numeroFila, numeroColumna)) {
                        if (jugadorJugando.getFicha().getLetra().equals(partidaActual().getTablero().getTableroFichas()[numeroF][numeroC].getLetra().toUpperCase())) {
                            ok = true;
                        }
                    }
                }
            }
        }
        return ok;
    }

    public boolean hayFichaEnElMedio(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) {
        return partidaActual().getTablero().hayFichaEnElMedio(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino);
    }

    public boolean saltoOk(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) {
        return partidaActual().getTablero().saltoOk(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino);
    }

    public boolean saltaFicha(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) throws Exception {
        return partidaActual().saltarFicha(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino);
    }

    public boolean validarFichaConBaldosa(int filaD, int columnaD, int filaFO, int columnaFO, Jugador jugadorJugando) {
        return partidaActual().getTablero().validarFichaConBaldosa(filaD, columnaD, filaFO, columnaFO, jugadorJugando);
    }

    public boolean baldosaConDosAlLado(int numeroFilaOrigen, int numeroColumnaOrigen) {
        return partidaActual().getTablero().baldosaConDosAlLado(numeroFilaOrigen, numeroColumnaOrigen);
    }

    public boolean nuevaUbicacionBaldosa(int numeroFilaDestino, int numeroColumnaDestino) {
        return partidaActual().getTablero().nuevaUbicacionBaldosa(numeroFilaDestino, numeroColumnaDestino);
    }

    public boolean moverBaldosa(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, int numeroFilaOFicha, int numeroColumnaOFicha, Jugador jugadorJugando) throws Exception {
        return partidaActual().moverBaldosa(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino, numeroFilaOFicha, numeroColumnaOFicha, jugadorJugando);
    }

    public boolean validarDimensionesMatriz(int dimension) {
        boolean esValida;
        switch (dimension) {
            case 1:
                esValida = true;
                break;
            case 2:
                esValida = true;
                break;
            case 3:
                esValida = true;
                break;
            default:
                esValida = false;
        }
        return esValida;
    }

    public boolean fichaCoincideConJugador(Ficha unaFicha) {
        return partidaActual().fichaCoincideConJugador(unaFicha);
    }

    public Sistema obtenerSistema() {
        Sistema sis;
        try {
            File aqui = new File("").getAbsoluteFile();
            String direccionEntera = aqui.getPath() + "\\obligatorio2";
            BufferedInputStream inBuffer = new BufferedInputStream(new FileInputStream(direccionEntera));
            ObjectInputStream stream = new ObjectInputStream(inBuffer);
            sis = (Sistema) stream.readObject();
            stream.close();
        } catch (IOException | ClassNotFoundException err) {
            sis = new Sistema();
        }
        return sis;
    }

    public void guardarSistema(Sistema sis) throws FileNotFoundException, IOException {
        try {
            File aqui = new File("").getAbsoluteFile();
            String direccionEntera = aqui.getPath() + "\\obligatorio2";
            BufferedOutputStream outBuffer = new BufferedOutputStream(new FileOutputStream(direccionEntera));
            ObjectOutputStream stream = new ObjectOutputStream(outBuffer);
            stream.writeObject(sis);
            stream.flush();
            stream.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void reiniciar() {
        partidaActual().setTurno(true);
        partidaActual().setTablero(precargar.copia());
    }

    public void cambiarIconoNormal(ImageIcon icono) {
        partidaActual().cambiarIconoNormal(icono);
    }

    public void cambiarIconoInvertido(ImageIcon icono) {
        partidaActual().cambiarIconoInvertido(icono);
    }

    public void modificarTableroPrecarga(Tablero tablero) {
        precargar = tablero;
    }
}
