package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;

public class Tablero implements Serializable {

    private Ficha[][] tableroFichas;
    private boolean[][] tableroBaldosas;
    private String nombre;
    private Date fecha;

    public Tablero(Ficha[][] tabF, boolean[][] tabB) {
        this.setTableroFichas(tabF);
        this.setTableroBaldosas(tabB);
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tablero() {
        limpiar();
    }

    public void limpiar() {
        Ficha[][] tFichas = new Ficha[13][14];
        this.setTableroFichas(tFichas);
        boolean[][] tBaldosas = new boolean[13][14];
        this.setTableroBaldosas(tBaldosas);
        for (int i = 5; i < 9; i++) {
            for (int j = 5; j < 10; j++) {
                this.getTableroBaldosas()[i][j] = (true);
            }
        }
    }

    public boolean coordenadaValida(int filaDestino, int columnaDestino) {
        boolean ok = false;
        if ((filaDestino >= 0 && filaDestino <= 12) && (columnaDestino >= 0 && columnaDestino <= 13)) {
            ok = true;
        }
        return ok;
    }

    public boolean hayBaldosa(int numeroFila, int numeroColumna) {
        return this.getTableroBaldosas()[numeroFila][numeroColumna];
    }

    public boolean hayFicha(int numeroFila, int numeroColumna) {
        return (this.getTableroFichas()[numeroFila][numeroColumna] != null);
    }

    public String getFicha(int f, int c) {
        return tableroFichas[f][c].getLetra();
    }

    public void precargaFija() {
        limpiar();
        this.getTableroFichas()[5][5] = new Ficha(5, 5, "B");
        this.getTableroFichas()[5][7] = new Ficha(5, 7, "B");
        this.getTableroFichas()[6][8] = new Ficha(6, 8, "B");
        this.getTableroFichas()[7][7] = new Ficha(7, 7, "B");
        this.getTableroFichas()[7][9] = new Ficha(7, 9, "B");
        this.getTableroFichas()[8][5] = new Ficha(8, 5, "B");
        this.getTableroFichas()[5][6] = new Ficha(5, 6, "R");
        this.getTableroFichas()[6][5] = new Ficha(6, 5, "R");
        this.getTableroFichas()[6][7] = new Ficha(6, 7, "R");
        this.getTableroFichas()[7][5] = new Ficha(7, 5, "R");
        this.getTableroFichas()[7][8] = new Ficha(7, 8, "R");
        this.getTableroFichas()[8][6] = new Ficha(8, 6, "R");
    }

    public void precargaAzar() {
        int i = 1;
        Random rnd = new Random();
        limpiar();
        while (i < 7) {
            int fila = (int) (rnd.nextDouble() * 4 + 5);
            int columna = (int) (rnd.nextDouble() * 5 + 5);
            if (tableroFichas[fila][columna] == null) {
                tableroFichas[fila][columna] = new Ficha(fila, columna, "B");
                i++;
            }
        }
        while (i < 13) {
            int fila = (int) (rnd.nextDouble() * 4 + 5);
            int columna = (int) (rnd.nextDouble() * 5 + 5);
            if (tableroFichas[fila][columna] == null) {
                tableroFichas[fila][columna] = new Ficha(fila, columna, "R");
                i++;
            }
        }
    }

    public boolean agregarFicha(int fila, int columna, String color) {
        boolean ok = false;
        if (this.coordenadaValida(fila, columna)) {
            if (hayBaldosa(fila, columna) && !hayFicha(fila, columna)) {
                this.getTableroFichas()[fila][columna] = new Ficha(fila, columna, color);
                ok = true;
            }
        }
        return ok;
    }

    public boolean validarMovimientoFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        boolean ok = false;
        if ((Math.abs(filaOrigen - filaDestino) == 1 && Math.abs(columnaOrigen - columnaDestino) == 1) || (Math.abs(filaOrigen - filaDestino) == 0 && Math.abs(columnaOrigen - columnaDestino) == 1) || (Math.abs(filaOrigen - filaDestino) == 1 && Math.abs(columnaOrigen - columnaDestino) == 0)) {
            if (coordenadaValida(filaDestino, columnaDestino)) {
                ok = true;
            }
        }
        return ok;
    }

    public void moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Ficha fichaAux) throws Exception {
        if (hayBaldosa(filaDestino, columnaDestino)) {
            if (validarMovimientoFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                if (!hayFicha(filaDestino, columnaDestino)) {
                    getTableroFichas()[filaDestino][columnaDestino] = fichaAux;
                    getTableroFichas()[filaOrigen][columnaOrigen] = null;
                } else {
                    throw new NullPointerException("Ya hay una ficha en esa posicion");
                }
            } else {
                throw new NullPointerException("Imposible realizar ese movimiento con la ficha");
            }
        } else {
            throw new NullPointerException("No hay baldosa en la posicion de destino");
        }
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
        return hayFicha(fila, columna);
    }

    public boolean validarFichaConBaldosa(int filaD, int columnaD, int filaFO, int columnaFO, Jugador jugadorJugando) {
        boolean ok = false;
        if (coordenadaValida(filaFO, columnaFO)) {
            Ficha fichaAux = getTableroFichas()[filaFO][columnaFO];
            if (fichaAux != null && jugadorJugando.getFicha().getLetra().toUpperCase().equals(fichaAux.getLetra().toUpperCase())) {
                if (saltoOk(filaFO, columnaFO, filaD, columnaD) || validarMovimientoFicha(filaFO, columnaFO, filaD, columnaD)) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    public int Vali1(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali2(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali3(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali4(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali5(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali6(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali7(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali8(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen - 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen + 1] == true) {
            cont++;
        }
        return cont;
    }

    public int Vali9(int numeroFilaOrigen, int numeroColumnaOrigen) {
        int cont = 0;
        if (getTableroBaldosas()[numeroFilaOrigen + 1][numeroColumnaOrigen] == true) {
            cont++;
        }
        if (getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen - 1] == true) {
            cont++;
        }
        return cont;
    }

    public boolean saltoOk(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino) {
        boolean ok = false;
        if ((Math.abs(numeroFilaOrigen - numeroFilaDestino) == 2 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 2) || (Math.abs(numeroFilaOrigen - numeroFilaDestino) == 0 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 2) || (Math.abs(numeroFilaOrigen - numeroFilaDestino) == 2 && Math.abs(numeroColumnaOrigen - numeroColumnaDestino) == 0)) {
            if ((coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) && (hayFichaEnElMedio(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino))) {
                ok = true;
            }
        }
        return ok;
    }

    public boolean saltarFicha(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, Jugador jugadorJugando) throws Exception {
        boolean ok = false;
        if (coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
            if (hayBaldosa(numeroFilaDestino, numeroColumnaDestino)) {
                if (!hayFicha(numeroFilaDestino, numeroColumnaDestino)) {
                    Ficha fichaAux = getTableroFichas()[numeroFilaOrigen][numeroColumnaOrigen];
                    if (jugadorJugando.getFicha().getLetra().toUpperCase().equals(fichaAux.getLetra().toUpperCase())) {
                        if (saltoOk(numeroFilaOrigen, numeroColumnaOrigen, numeroFilaDestino, numeroColumnaDestino)) {
                            if (fichaAux.getLetra().equals("R") || fichaAux.getLetra().equals("B")) {
                                fichaAux.setLetra(fichaAux.getLetra().toLowerCase());
                            } else {
                                fichaAux.setLetra(fichaAux.getLetra().toUpperCase());
                            }
                            getTableroFichas()[numeroFilaDestino][numeroColumnaDestino] = fichaAux;
                            getTableroFichas()[numeroFilaOrigen][numeroColumnaOrigen] = null;
                            ok = true;
                        }
                    } else {
                        throw new NullPointerException("Imposible seleccionar la ficha del rival");
                    }
                } else {
                    throw new ArrayIndexOutOfBoundsException("Ya hay ficha en la posicion de destino");
                }
            } else {
                throw new NullPointerException("No hay baldosa en la posicion de destino");
            }
        } else {
            throw new NullPointerException("No hay baldosa en la posicion de partida");
        }
        return ok;
    }

    public boolean baldosaConDosAlLado(int numeroFilaOrigen, int numeroColumnaOrigen) {
        boolean ok = false;
        int cont = 0;
        if (numeroFilaOrigen != 0 && numeroColumnaOrigen != 0 && numeroFilaOrigen != 12 && numeroColumnaOrigen != 13) {
            cont = Vali1(numeroFilaOrigen, numeroColumnaOrigen);
        } else {
            if (numeroFilaOrigen == 0 && numeroColumnaOrigen != 0 && numeroColumnaOrigen != 13) {
                cont = Vali2(numeroFilaOrigen, numeroColumnaOrigen);
            } else {
                if (numeroFilaOrigen == 0 && numeroColumnaOrigen == 0) {
                    cont = Vali3(numeroFilaOrigen, numeroColumnaOrigen);
                } else {
                    if (numeroFilaOrigen != 0 && numeroFilaOrigen != 12 && numeroColumnaOrigen == 0) {
                        cont = Vali4(numeroFilaOrigen, numeroColumnaOrigen);
                    } else {
                        if (numeroFilaOrigen == 12 && numeroColumnaOrigen != 0 && numeroColumnaOrigen != 13) {
                            cont = Vali5(numeroFilaOrigen, numeroColumnaOrigen);
                        } else {
                            if (numeroFilaOrigen != 12 && numeroFilaOrigen != 0 && numeroColumnaOrigen == 13) {
                                cont = Vali6(numeroFilaOrigen, numeroColumnaOrigen);
                            } else {
                                if (numeroFilaOrigen == 12 && numeroColumnaOrigen == 13) {
                                    cont = Vali7(numeroFilaOrigen, numeroColumnaOrigen);
                                } else {
                                    if (numeroFilaOrigen == 12 && numeroColumnaOrigen == 0) {
                                        cont = Vali8(numeroFilaOrigen, numeroColumnaOrigen);
                                    } else {
                                        if (numeroFilaOrigen == 0 && numeroColumnaOrigen == 13) {
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
        if (coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
            if (numeroFilaDestino != 0 && numeroColumnaDestino != 0 && numeroFilaDestino != 12 && numeroColumnaDestino != 13) {
                cont = Vali1(numeroFilaDestino, numeroColumnaDestino);
            } else {
                if (numeroFilaDestino == 0 && numeroColumnaDestino != 0 && numeroColumnaDestino != 13) {
                    cont = Vali2(numeroFilaDestino, numeroColumnaDestino);
                } else {
                    if (numeroFilaDestino == 0 && numeroColumnaDestino == 0) {
                        cont = Vali3(numeroFilaDestino, numeroColumnaDestino);
                    } else {
                        if (numeroFilaDestino != 0 && numeroFilaDestino != 12 && numeroColumnaDestino == 0) {
                            cont = Vali4(numeroFilaDestino, numeroColumnaDestino);
                        } else {
                            if (numeroFilaDestino == 12 && numeroColumnaDestino != 0 && numeroColumnaDestino != 13) {
                                cont = Vali5(numeroFilaDestino, numeroColumnaDestino);
                            } else {
                                if (numeroFilaDestino != 12 && numeroFilaDestino != 0 && numeroColumnaDestino == 13) {
                                    cont = Vali6(numeroFilaDestino, numeroColumnaDestino);
                                } else {
                                    if (numeroFilaDestino == 12 && numeroColumnaDestino == 13) {
                                        cont = Vali7(numeroFilaDestino, numeroColumnaDestino);
                                    } else {
                                        if (numeroFilaDestino == 12 && numeroColumnaDestino == 0) {
                                            cont = Vali8(numeroFilaDestino, numeroColumnaDestino);
                                        } else {
                                            if (numeroFilaDestino == 0 && numeroColumnaDestino == 13) {
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

    public boolean moverBaldosa(int numeroFilaOrigen, int numeroColumnaOrigen, int numeroFilaDestino, int numeroColumnaDestino, int numeroFilaOFicha, int numeroColumnaOFicha, Jugador jugadorJugando) throws Exception {
        boolean ok = false;
        if (!hayFicha(numeroFilaOrigen, numeroColumnaOrigen)) {
            if (getTableroBaldosas()[numeroFilaDestino][numeroColumnaDestino] == false) {
                if (baldosaConDosAlLado(numeroFilaOrigen, numeroColumnaOrigen)) {
                    if (nuevaUbicacionBaldosa(numeroFilaDestino, numeroColumnaDestino)) {
                        if (validarFichaConBaldosa(numeroFilaDestino, numeroColumnaDestino, numeroFilaOFicha, numeroColumnaOFicha, jugadorJugando)) {
                            getTableroBaldosas()[numeroFilaDestino][numeroColumnaDestino] = true;
                            getTableroBaldosas()[numeroFilaOrigen][numeroColumnaOrigen] = false;
                            if (!hayFicha(numeroFilaDestino, numeroColumnaDestino)) {
                                if (coordenadaValida(numeroFilaDestino, numeroColumnaDestino)) {
                                    if (saltarFicha(numeroFilaOFicha, numeroColumnaOFicha, numeroFilaDestino, numeroColumnaDestino, jugadorJugando)) {
                                        ok = true;
                                    } else {
                                        Ficha fichaAux = getTableroFichas()[numeroFilaOFicha][numeroColumnaOFicha];
                                        moverFicha(numeroFilaOFicha, numeroColumnaOFicha, numeroFilaDestino, numeroColumnaDestino, fichaAux);
                                        ok = true;
                                    }
                                } else {
                                    throw new NullPointerException("Ya hay una baldosa en esa posicion");
                                }
                            } else {
                                throw new NullPointerException("La posicion elegida ya contiene ficha");
                            }
                        } else {
                            throw new NullPointerException("Imposible mover la baldosa a esa ubicacion");
                        }
                    } else {
                        throw new NullPointerException("Imposible seleccionar esa baldosa");
                    }
                } else {
                    throw new NullPointerException("Imposible mover esa baldosa");
                }
            } else {
                throw new NullPointerException("Ya hay una baldosa en esa posicion");
            }
        } else {
            throw new NullPointerException("Imposible mover una baldosa con ficha");
        }
        return ok;
    }

    public Tablero copia() {
        Tablero tableroCopiado = new Tablero();
        tableroCopiado.setNombre(this.nombre);
        tableroCopiado.setFecha(this.getFecha());
        for (int i = 5; i < 9; i++) {
            for (int j = 5; j < 10; j++) {
                if (this.hayFicha(i, j)) {
                    tableroCopiado.agregarFicha(i, j, this.getFicha(i, j).charAt(0) + "");
                }
            }
        }
        return tableroCopiado;
    }

    public void cambiarFichasNormales(ImageIcon icono, Ficha ficha) {
        int ancho = getTableroFichas().length;
        int largo = getTableroFichas()[0].length;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                if (hayFicha(i, j)) {
                    Ficha unaF = getTableroFichas()[i][j];
                    if (unaF.getLetra().toUpperCase().equals(ficha.getLetra().toUpperCase())) {
                        unaF.setIcono(icono);
                    }
                }
            }
        }
    }

    public void cambiarFichasInvertidas(ImageIcon icono, Ficha ficha) {
        int ancho = getTableroFichas().length;
        int largo = getTableroFichas()[0].length;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                if (hayFicha(i, j)) {
                    Ficha unaF = getTableroFichas()[i][j];
                    if (unaF.getLetra().toLowerCase().equals(ficha.getLetra().toLowerCase())) {
                        unaF.setIconoInvertido(icono);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Fecha: " + fecha;
    }
}
