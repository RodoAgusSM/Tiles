package Dominio;

import java.util.ArrayList;
import java.util.Collections;

public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;
    private Tablero precargar;

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
        unaPartida.setTablero(precargar);
        listaPartidas.add(unaPartida);
    }

    public Tablero getPrecarga() {
        return precargar;
    }

    public Sistema() {
        listaJugadores = new ArrayList<Jugador>();
        listaPartidas = new ArrayList<Partida>();
    }

    public Partida partidaActual() {
        return listaPartidas.get(listaPartidas.size() - 1);
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

    //registro un jugador
    public void registrarJugador(Ficha unaFicha, String unNombre, int unaEdad, String unAlias) {
        Jugador unJugador = new Jugador(unaFicha, unNombre, unaEdad, unAlias, 0, 0, 0);
        this.listaJugadores.add(unJugador);
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
        partidaActual().precargarFija();
    }

    public void precargaAzar() {
        partidaActual().precargaAzar();
    }

    public boolean agregarFichaPrecargaMano(String fila, String columna, String color) {
        return partidaActual().getTablero().agregarFicha(Partida.conversorFila(fila), Partida.conversorColumna(columna), color);
    }

    public void moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Jugador jugadorJugando) {
        Ficha fichaAux = partidaActual().getTablero().getTableroFichas()[filaOrigen][columnaOrigen];
        if (jugadorJugando.getFicha().getLetra().equals(fichaAux.getLetra().toUpperCase())) {
            if (partidaActual().getTablero().hayBaldosa(filaDestino, columnaDestino)) {
                if (validarMovimientoFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                    partidaActual().getTablero().getTableroFichas()[filaDestino][columnaDestino] = fichaAux;
                    partidaActual().getTablero().getTableroFichas()[filaOrigen][columnaOrigen] = null;
                }
            }
        }
    }

    public boolean validarMovimientoFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        boolean ok = false;
        if ((Math.abs(filaOrigen - filaDestino) == 1 && Math.abs(columnaOrigen - columnaDestino) == 1) || (Math.abs(filaOrigen - filaDestino) == 0 && Math.abs(columnaOrigen - columnaDestino) == 1) || (Math.abs(filaOrigen - filaDestino) == 1 && Math.abs(columnaOrigen - columnaDestino) == 0)) {
            if (partidaActual().getTablero().coordenadaValida(filaDestino, columnaDestino)) {
                ok = true;
            }
        }
        return ok;
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
        int fila;
        int columna;
        if (numeroFilaOrigen == numeroFilaDestino) {
            fila = numeroFilaOrigen;
        } else {
            fila = Math.max(numeroFilaOrigen, numeroFilaDestino) - 1;
        }
        if (numeroColumnaOrigen == numeroColumnaDestino) {
            columna = numeroColumnaOrigen;
        } else {
            columna = Math.max(numeroColumnaOrigen, numeroColumnaDestino) - 1;
        }
        return partidaActual().getTablero().hayFicha(fila, columna);
    }

    public boolean saltoOk(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) {
        boolean ok = false;
        if ((Math.abs(numeroFilaOrigen - numeroFilaDestino) == 2 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 2) || (Math.abs(numeroFilaOrigen - numeroFilaDestino) == 0 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 2) || (Math.abs(numeroFilaOrigen - numeroFilaDestino) == 2 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 0)) {
            if ((partidaActual().getTablero().coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) && (hayFichaEnElMedio(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino))) {
                ok = true;
            }
        }
        return ok;
    }

    public boolean saltaFicha(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, Jugador jugadorJugando) {
        boolean ok = false;
        if (partidaActual().getTablero().coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
            if (partidaActual().getTablero().hayBaldosa(numeroFilaDestino, numeroColumnaDestino)) {
                if (!partidaActual().getTablero().hayFicha(numeroFilaDestino, numeroColumnaDestino)) {
                    if (partidaActual().getTablero().hayFicha(numeroFilaOrigen, numeroColumnaOrigen)) {
                        Ficha fichaAux = partidaActual().getTablero().getTableroFichas()[numeroFilaOrigen][numeroColumnaOrigen];
                        if (jugadorJugando.getFicha().getLetra().equals(fichaAux.getLetra().toUpperCase())) {
                            if (saltoOk(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino)) {
                                if (fichaAux.getLetra().equals("R") || fichaAux.getLetra().equals("B")) {
                                    fichaAux.setLetra(fichaAux.getLetra().toLowerCase());
                                } else {
                                    fichaAux.setLetra(fichaAux.getLetra().toUpperCase());
                                }
                                partidaActual().getTablero().getTableroFichas()[numeroFilaDestino][numeroColumnaDestino] = fichaAux;
                                partidaActual().getTablero().getTableroFichas()[numeroFilaOrigen][numeroColumnaOrigen] = null;
                                ok = true;
                            }
                        }
                    }
                }
            }
        }
        return ok;
    }

    public boolean validarFichaConBaldosa(int filaD, int columnaD, int filaFO, int columnaFO, Jugador jugadorJugando) {
        boolean ok = false;
        if (partidaActual().getTablero().coordenadaValida(filaFO, columnaFO)) {
            Ficha fichaAux = partidaActual().getTablero().getTableroFichas()[filaFO][columnaFO];
            if (fichaAux != null && jugadorJugando.getFicha().getLetra().equals(fichaAux.getLetra().toUpperCase())) {
                if (saltoOk(filaFO, columnaFO, filaD, columnaD) || validarMovimientoFicha(filaFO, columnaFO, filaD, columnaD)) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    public int Vali1(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali2(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali3(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali4(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali5(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali6(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali7(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali8(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali9(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public boolean baldosaConDosAlLado(int numeroFilaOrigen, int numeroColumnaOrigen) {
        boolean ok = false;
        int cont = 0;
        if (numeroFilaOrigen != 0 && numeroColumnaOrigen != 0 && numeroFilaOrigen != 11 && numeroColumnaOrigen != 12) {
            cont = Vali1(numeroFilaOrigen, numeroColumnaOrigen);
        } else {
            if (numeroFilaOrigen == 0 && numeroColumnaOrigen != 0 && numeroColumnaOrigen != 12) {
                cont = Vali2(numeroFilaOrigen, numeroColumnaOrigen);
            } else {
                if (numeroFilaOrigen == 0 && numeroColumnaOrigen == 0) {
                    cont = Vali3(numeroFilaOrigen, numeroColumnaOrigen);
                } else {
                    if (numeroFilaOrigen != 0 && numeroFilaOrigen != 11 && numeroColumnaOrigen == 0) {
                        cont = Vali4(numeroFilaOrigen, numeroColumnaOrigen);
                    } else {
                        if (numeroFilaOrigen == 11 && numeroColumnaOrigen != 0 && numeroColumnaOrigen != 12) {
                            cont = Vali5(numeroFilaOrigen, numeroColumnaOrigen);
                        } else {
                            if (numeroFilaOrigen != 11 && numeroFilaOrigen != 0 && numeroColumnaOrigen == 12) {
                                cont = Vali6(numeroFilaOrigen, numeroColumnaOrigen);
                            } else {
                                if (numeroFilaOrigen == 11 && numeroColumnaOrigen == 12) {
                                    cont = Vali7(numeroFilaOrigen, numeroColumnaOrigen);
                                } else {
                                    if (numeroFilaOrigen == 11 && numeroColumnaOrigen == 0) {
                                        cont = Vali8(numeroFilaOrigen, numeroColumnaOrigen);
                                    } else {
                                        if (numeroFilaOrigen == 0 && numeroColumnaOrigen == 12) {
                                            cont = Vali9(numeroFilaOrigen, numeroColumnaOrigen);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (cont <= 2) {
            ok = true;
        }
        return ok;
    }

    public boolean nuevaUbicacionBaldosa(int numeroFilaDestino, int numeroColumnaDestino) {
        boolean ok = false;
        int cont = 0;
        if (partidaActual().getTablero().coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
            if (numeroFilaDestino != 0 && numeroColumnaDestino != 0 && numeroFilaDestino != 11 && numeroColumnaDestino != 12) {
                cont = Vali1(numeroFilaDestino, numeroColumnaDestino);
            } else {
                if (numeroFilaDestino == 0 && numeroColumnaDestino != 0 && numeroColumnaDestino != 12) {
                    cont = Vali2(numeroFilaDestino, numeroColumnaDestino);
                } else {
                    if (numeroFilaDestino == 0 && numeroColumnaDestino == 0) {
                        cont = Vali3(numeroFilaDestino, numeroColumnaDestino);
                    } else {
                        if (numeroFilaDestino != 0 && numeroFilaDestino != 11 && numeroColumnaDestino == 0) {
                            cont = Vali4(numeroFilaDestino, numeroColumnaDestino);
                        } else {
                            if (numeroFilaDestino == 11 && numeroColumnaDestino != 0 && numeroColumnaDestino != 12) {
                                cont = Vali5(numeroFilaDestino, numeroColumnaDestino);
                            } else {
                                if (numeroFilaDestino != 11 && numeroFilaDestino != 0 && numeroColumnaDestino == 12) {
                                    cont = Vali6(numeroFilaDestino, numeroColumnaDestino);
                                } else {
                                    if (numeroFilaDestino == 11 && numeroColumnaDestino == 12) {
                                        cont = Vali7(numeroFilaDestino, numeroColumnaDestino);
                                    } else {
                                        if (numeroFilaDestino == 11 && numeroColumnaDestino == 0) {
                                            cont = Vali8(numeroFilaDestino, numeroColumnaDestino);
                                        } else {
                                            if (numeroFilaDestino == 0 && numeroColumnaDestino == 12) {
                                                cont = Vali9(numeroFilaDestino, numeroColumnaDestino);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (cont >= 1) {
            ok = true;
        }
        return ok;
    }

    public boolean moverBaldosa(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, int numeroFilaOFicha, int numeroColumnaOFicha, Jugador jugadorJugando) {
        boolean ok = false;
        int largo = partidaActual().getTablero().getTableroBaldosas().length;
        int ancho = partidaActual().getTablero().getTableroBaldosas()[0].length;
        if (partidaActual().getTablero().coordenadaValida(numeroFilaOrigen, numeroColumnaOrigen)) {
            if (partidaActual().getTablero().coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
                if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen] == true) {
                    for (int i = 0; i < 10; i++) {
                        
                    }
                    if (partidaActual().getTablero().getTableroBaldosas()[numeroFilaDestino][numeroColumnaDestino] == false) {
                        if (baldosaConDosAlLado(numeroFilaOrigen, numeroColumnaOrigen)) {
                            if (nuevaUbicacionBaldosa(numeroFilaDestino, numeroColumnaDestino)) {
                                if (validarFichaConBaldosa(numeroFilaDestino, numeroColumnaDestino, numeroFilaOFicha, numeroColumnaOFicha, jugadorJugando)) {
                                    partidaActual().getTablero().getTableroBaldosas()[numeroFilaDestino][numeroColumnaDestino] = true;
                                    partidaActual().getTablero().getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen] = false;
                                    if (saltaFicha(numeroFilaOFicha, numeroColumnaOFicha, numeroFilaDestino, numeroColumnaDestino, jugadorJugando)) {
                                        ok = true;
                                    } else {
                                        moverFicha(numeroFilaOFicha, numeroColumnaOFicha, numeroFilaDestino, numeroColumnaDestino, jugadorJugando);
                                        ok = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ok;
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
}
