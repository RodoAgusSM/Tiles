package Interfaz;

import java.util.*;
import Dominio.*;

public class Interfaz {

    private Sistema sistema;

    public Sistema getSistema() {
        return sistema;
    }

    public void setModelo(Sistema unSistema) {
        this.sistema = unSistema;
    }

    public Interfaz(Sistema unSistema) {
        this.setModelo(unSistema);
    }

    //valido los int con un valor
    public int ingresarNroUnVal(String unTexto, String unError, int valorMin) {
        Scanner in = new Scanner(System.in);
        boolean ok = false;
        int nroUnVal = 0;
        System.out.print(unTexto);
        while (!ok) {
            try {
                nroUnVal = in.nextInt();
                if (nroUnVal < valorMin) {
                    System.out.println(unError);
                } else {
                    ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error, reingrese por favor :");
                nroUnVal = 0;
                in.next();
            }
        }
        return nroUnVal;
    }

    //valido los int con dos valores
    public int ingresarNroDosVal(String unTexto, String unError, int valorMin, int valorMax) {
        Scanner in = new Scanner(System.in);
        int nroDosVal = 0;
        boolean ok = false;
        System.out.print(unTexto);
        while (!ok) {
            try {
                nroDosVal = in.nextInt();
                if (nroDosVal < valorMin || nroDosVal > valorMax) {
                    System.out.println(unError);

                } else {
                    ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error, reingrese por favor: ");
                nroDosVal = 0;
                in.next();
            }
        }
        return nroDosVal;
    }

    //valido los strings
    public String ingresarTexto(String mensaje) {
        Scanner in = new Scanner(System.in);
        String texto = "";
        System.out.print(mensaje);
        texto = in.nextLine();
        while (texto.trim().equals("")) {
            System.out.print("Ingrese un texto sin espacios vacíos: ");
            texto = in.nextLine();
        }
        return texto;
    }

    public String ingresarTextoJuego(String mensaje) {
        Scanner in = new Scanner(System.in);
        String texto = "";
        System.out.print(mensaje);
        texto = in.nextLine();
        while (texto.trim().equals("")) {
            System.out.print("Ingrese un texto sin espacios vacíos: ");
            texto = in.nextLine();
        }
        while (!texto.equals(texto.toUpperCase())) {
            System.out.print("\nINGRESE LA JUGADA EN MAYUSCULA: ");
            texto = in.nextLine();
        }
        return texto;
    }

    public String error() {
        String texto = "\nERROR REINGRESE, POR FAVOR";
        return texto;
    }

    //selecciono un jugador para jugar perfeccion
    public Jugador seleccionJugador(Partida unaP) {
        System.out.println("Elija un jugador: ");
        for (int i = 0; i < this.getSistema().getListaJugadores().size(); i++) {
            System.out.println("\n" + (i + 1) + ")" + "-" + this.getSistema().getListaJugadores().get(i));
        }
        int selectPlayer = this.ingresarNroDosVal("\n" + "Seleccione un jugador para jugar BALDOSAS: ", "ERROR, NO EXISTE EL JUGADOR SELECCIONADO: ", 1, this.getSistema().getListaJugadores().size());
        Jugador unJ = this.getSistema().getListaJugadores().get(selectPlayer - 1);
        return unJ;
    }

    public int jugadorEnUso(Partida unaPartida, Jugador unJugador) {
        int cont = 0;
        for (int i = 0; i < unaPartida.getListaJugadores().size(); i++) {
            if (unaPartida.getListaJugadores().get(i).equals(unJugador)) {
                cont++;
            }
        }
        return cont;
    }

    public boolean controlPedirNumeros(int indice1, int indice2, String coordenadas) {
        boolean ok = false;
        if ((coordenadas.substring(indice1, indice2).contains("0") || coordenadas.substring(indice1, indice2).contains("1"))) {
            ok = true;
        } else {
            if ((coordenadas.substring(indice1, indice2).contains("2") || coordenadas.substring(indice1, indice2).contains("3"))) {
                ok = true;
            } else {
                if ((coordenadas.substring(indice1, indice2).contains("4") || coordenadas.substring(indice1, indice2).contains("5"))) {
                    ok = true;
                } else {
                    if ((coordenadas.substring(indice1, indice2).contains("6") || coordenadas.substring(indice1, indice2).contains("7"))) {
                        ok = true;
                    } else {
                        if ((coordenadas.substring(indice1, indice2).contains("8") || coordenadas.substring(indice1, indice2).contains("9"))) {
                            ok = true;
                        } else {
                        }
                    }
                }
            }
        }
        return ok;
    }

    public String pedirFilaO(String coodenadas) {
        String coodenadaFila = coodenadas.substring(2, 3);
        return coodenadaFila.toUpperCase();
    }

    public String pedirColumnaO(String coordenadas) {
        String coordenadaColumna = "";
        if (controlPedirNumeros(4, 5, coordenadas)) {
            coordenadaColumna = coordenadas.substring(3, 5);
        } else {
            coordenadaColumna = coordenadas.substring(3, 4);
        }
        return coordenadaColumna.toUpperCase();
    }

    public String pedirColumnaDMS(String coordenadas) {
        String coordenada = "";
        if (this.pedirColumnaO(coordenadas).length() == 2) {
            coordenada = coordenadas.substring(6);
        } else {
            if (this.pedirColumnaO(coordenadas).length() == 1) {
                coordenada = coordenadas.substring(5);
            }
        }
        return coordenada;
    }

    public String pedirFilaD(String coordenadas) {
        String coordenadaFila = "";
        if (this.pedirColumnaO(coordenadas).length() == 2) {
            coordenadaFila = coordenadas.substring(5, 6);
        } else {
            coordenadaFila = coordenadas.substring(4, 5);
        }
        return coordenadaFila.toUpperCase();
    }

    public String pedirColumnaD(String coordenadas) {
        String coordenadaColumna = "";
        if (this.pedirColumnaO(coordenadas).length() == 2) {
            if (controlPedirNumeros(7, 8, coordenadas)) {
                coordenadaColumna = coordenadas.substring(6, 8);
            } else {
                coordenadaColumna = coordenadas.substring(6, 7);
            }
        } else {
            if (this.pedirColumnaO(coordenadas).length() == 1) {
                if (controlPedirNumeros(6, 7, coordenadas)) {
                    coordenadaColumna = coordenadas.substring(5, 7);
                } else {
                    coordenadaColumna = coordenadas.substring(5, 6);
                }
            }
        }
        return coordenadaColumna.toUpperCase();
    }

    public String pedirFilaPrecargaMano(String coordenadas) {
        String coordenadaFila = coordenadas.substring(0, 1);
        return coordenadaFila.toUpperCase();
    }

    public String pedirColumnaPrecargaMano(String coordenadas) {
        String coordenadaColumna = "";
        if (coordenadas.length() == 3) {
            coordenadaColumna = coordenadas.substring(1, 3);
        } else {
            if (coordenadas.length() == 2) {
                coordenadaColumna = coordenadas.substring(1, 2);
            }
        }
        return coordenadaColumna.toUpperCase();
    }

    public String pedirFilaOFicha(String coordenadas) {
        String coordenadaFilaOF = "";
        if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 2) {
            coordenadaFilaOF = coordenadas.substring(8, 9);
        } else {
            if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 1) {
                coordenadaFilaOF = coordenadas.substring(7, 8);
            } else {
                if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 2) {
                    coordenadaFilaOF = coordenadas.substring(7, 8);
                } else {
                    if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 1) {
                        coordenadaFilaOF = coordenadas.substring(6, 7);
                    }
                }
            }
        }
        return coordenadaFilaOF;
    }

    public String pedirColumnaOFicha(String coordenadas) {
        String coordenadaColumnaOF = "";
        if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 2 && coordenadas.length() == 11) {
            coordenadaColumnaOF = coordenadas.substring(9, 11);
        } else {
            if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 2 && coordenadas.length() == 10) {
                coordenadaColumnaOF = coordenadas.substring(9, 10);
            } else {
                if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 1 && coordenadas.length() == 10) {
                    coordenadaColumnaOF = coordenadas.substring(8, 10);
                } else {
                    if (this.pedirColumnaO(coordenadas).length() == 2 && this.pedirColumnaD(coordenadas).length() == 1 && coordenadas.length() == 9) {
                        coordenadaColumnaOF = coordenadas.substring(8, 9);
                    } else {
                        if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 2 && coordenadas.length() == 10) {
                            coordenadaColumnaOF = coordenadas.substring(8, 10);
                        } else {
                            if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 2 && coordenadas.length() == 9) {
                                coordenadaColumnaOF = coordenadas.substring(8, 9);
                            } else {
                                if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 1 && coordenadas.length() == 9) {
                                    coordenadaColumnaOF = coordenadas.substring(7, 9);
                                } else {
                                    if (this.pedirColumnaO(coordenadas).length() == 1 && this.pedirColumnaD(coordenadas).length() == 1 && coordenadas.length() == 8) {
                                        coordenadaColumnaOF = coordenadas.substring(7, 8);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return coordenadaColumnaOF;
    }

    public String pedirCoordenadas2() {
        String coordenadas = this.ingresarTexto("Ingrese las coordenadas de la ficha: ");
        while (coordenadas.length() < 2 || coordenadas.length() > 3) {
            System.out.println("ERROR, LAS COORDENADAS DEBEN SER DEL FORMATO -XY-");
            coordenadas = this.ingresarTexto("Ingrese las coordenadas de la ficha: ");
        }
        return coordenadas;
    }

    public String pedirCoordenadas() {
        String coordenadas = this.ingresarTexto("Ingrese las coordenadas de la ficha: ");
        while (coordenadas.length() < 5 || coordenadas.length() > 11) {
            System.out.println("ERROR, LAS COORDENADAS DEBEN SER DEL FORMATO -J XYXY-");
            coordenadas = this.ingresarTexto("REINGRESE LA JUGADA: ");
        }
        return coordenadas;
    }

    public String pedirCoordenadasPrecargaMano() {
        String coordenadas = this.ingresarTexto("Ingrese las coordenadas de la ficha: ");
        while (coordenadas.length() < 1 && coordenadas.length() > 3) {
            System.out.println("ERROR, LAS COORDENADAS DEBEN SER DEL FORMATO -XY-");
            coordenadas = this.ingresarTexto("Ingrese las coordenadas de la ficha: ");
        }
        return coordenadas;
    }

    public void imprimirMatriz(Tablero tablero, char caracter) {
        imprimirCabezal();
        int fila = tablero.getTableroBaldosas().length;
        System.out.println("");
        for (int f = 0; f < fila; f++) {
            System.out.print("  ");
            arriba(tablero, caracter, f);
            medio(tablero, f);
        }
        System.out.print("  ");
        ultima(tablero, caracter);
    }

    private void imprimirCabezal() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        System.out.println();
        int c = 1;
        int h = 0;
        System.out.print("   ");
        for (int n = 0; n < 13; n++) {
            if (n < 9) {
                System.out.print(ANSI_BLACK + c + ANSI_RESET);
                c++;
            } else {
                System.out.print(ANSI_BLACK + "1" + ANSI_RESET);
            }
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("   ");
        for (int n = 0; n < 13; n++) {
            if (n < 9) {
                System.out.print("  ");
                c++;
            } else {
                System.out.print(ANSI_BLACK + h + " " + ANSI_RESET);
                h++;
            }
        }
    }

    private void arriba(Tablero tablero, char caracter, int f) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_PURPLE = "\u001B[35m";
        int columna = tablero.getTableroBaldosas()[0].length;
        for (int c = 0; c < columna; c++) {
            if (tablero.hayBaldosa(f, c)) {
                System.out.print(ANSI_PURPLE + "+-" + ANSI_RESET);
            } else {
                if (tablero.hayBaldosaArriba(f, c)) {//Controlo que la fila de arriba tiene baldosa
                    System.out.print(ANSI_PURPLE + "+-" + ANSI_RESET);
                } else {
                    if (tablero.hayBaldosaIzquierda(f, c)) {//Ultima columna de la baldosa
                        System.out.print(ANSI_PURPLE + "+ " + ANSI_RESET);
                    } else {
                        if (tablero.hayBaldosaArribaIzquierda(f, c)) {//Controlo si izquierda de arriba tiene baldosa
                            System.out.print(ANSI_PURPLE + "+ " + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_GREEN + caracter + ANSI_RESET + " ");
                        }
                    }
                }
            }
        }
        if (tablero.hayBaldosa(f, columna - 1)) {
            System.out.print(ANSI_PURPLE + "+" + ANSI_RESET);
        } else {
            if (tablero.hayBaldosaArriba(f, columna - 1)) {
                System.out.print(ANSI_PURPLE + "+" + ANSI_RESET);
            } else {
                System.out.print(ANSI_GREEN + caracter + ANSI_RESET);
            }
        }
        System.out.println("");
    }

    private void medio(Tablero tablero, int f) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        int columna = tablero.getTableroBaldosas()[0].length;
        int letra = f + 65;
        System.out.print((char) letra + " ");
        for (int c = 0; c < columna; c++) {
            if (tablero.hayBaldosa(f, c)) {
                if (cuatroEspaciosEntrebaldosa(tablero, f, c)) {
                    System.out.print(ANSI_PURPLE + " |" + ANSI_RESET);
                } else {
                    if (tresEspaciosEntrebaldosa(tablero, f, c)) {
                        System.out.print(ANSI_PURPLE + " |" + ANSI_RESET);
                    } else {
                        if (dosEspaciosEntrebaldosa(tablero, f, c)) {
                            System.out.print(ANSI_PURPLE + " |" + ANSI_RESET);
                        } else {
                            if (unEspacioEntrebaldosa(tablero, f, c)) {
                                System.out.print(ANSI_PURPLE + " |" + ANSI_RESET);
                            } else {
                                System.out.print(ANSI_PURPLE + "|" + ANSI_RESET);
                            }
                        }
                    }
                }
                if (tablero.hayFicha(f, c)) {//Se pone fichas
                    if (tablero.getFicha(f, c).toUpperCase().equals("R")) {
                        System.out.print(ANSI_RED + tablero.getFicha(f, c) + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_BLUE + tablero.getFicha(f, c) + ANSI_RESET);
                    }
                } else {
                    System.out.print(" ");
                }
            } else {
                if (tablero.hayBaldosaIzquierda(f, c)) {//Ultima columna de la baldosa 
                    System.out.print(ANSI_PURPLE + "|" + ANSI_RESET);
                } else {
                    System.out.print("  ");
                }
            }
        }
        if (tablero.hayBaldosa(f, columna - 1)) {
            System.out.print(ANSI_PURPLE + "| " + ANSI_RESET);
        } else {
            System.out.print("  ");
        }
        System.out.println("");
    }

    private void ultima(Tablero tablero, char caracter) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_PURPLE = "\u001B[35m";
        int columna = tablero.getTableroBaldosas()[0].length;
        for (int c = 0; c < columna; c++) {
            if (tablero.hayBaldosa(11, c)) {
                System.out.print(ANSI_PURPLE + "+-" + ANSI_RESET);
            } else {
                if (tablero.hayBaldosaIzquierda(11, c)) {//Ultima columna de la baldosa
                    System.out.print(ANSI_PURPLE + "+ " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_GREEN + caracter + ANSI_RESET + " ");
                }
            }
        }
        if (tablero.hayBaldosa(11, 12)) {
            System.out.print(ANSI_PURPLE + "+ " + ANSI_RESET);
        } else {
            System.out.print(ANSI_GREEN + caracter + ANSI_RESET + " ");
            System.out.println("");
        }
    }

    public boolean unEspacioEntrebaldosa(Tablero tablero, int f, int c) {
        boolean ok = false;
        if (c > 1) {
            if (!tablero.hayBaldosaIzquierda(f, c)) {
                if (tablero.hayBaldosaIzquierda(f, c - 1)) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    public boolean dosEspaciosEntrebaldosa(Tablero tablero, int f, int c) {
        boolean ok = false;
        if (c > 2) {
            if (!tablero.hayBaldosaIzquierda(f, c)) {
                if (!tablero.hayBaldosaIzquierda(f, c - 1)) {
                    if (tablero.hayBaldosaIzquierda(f, c - 2)) {
                        ok = true;
                    }
                }
            }
        }
        return ok;
    }

    public boolean tresEspaciosEntrebaldosa(Tablero tablero, int f, int c) {
        boolean ok = false;
        if (c > 3) {
            if (!tablero.hayBaldosaIzquierda(f, c)) {
                if (!tablero.hayBaldosaIzquierda(f, c - 1)) {
                    if (!tablero.hayBaldosaIzquierda(f, c - 2)) {
                        if (tablero.hayBaldosaIzquierda(f, c - 3)) {
                            ok = true;
                        }
                    }
                }
            }
        }
        return ok;
    }

    public boolean cuatroEspaciosEntrebaldosa(Tablero tablero, int f, int c) {
        boolean ok = false;
        if (c > 4) {
            if (!tablero.hayBaldosaIzquierda(f, c)) {
                if (!tablero.hayBaldosaIzquierda(f, c - 1)) {
                    if (!tablero.hayBaldosaIzquierda(f, c - 2)) {
                        if (!tablero.hayBaldosaIzquierda(f, c - 3)) {
                            if (tablero.hayBaldosaIzquierda(f, c - 4)) {
                                ok = true;
                            }
                        }
                    }
                }
            }
        }
        return ok;
    }

    public Partida jugarBaldosas(Sistema unS) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        boolean turno = true;
        boolean seTerminoElJuego = false;
        boolean soloAlInicio = true;
        int numeroJugadorJugando;
        int numeroJugadorEsperando;
        boolean resultados[];
        Jugador jugadorJugando;
        Jugador jugadorEsperando;
        if (unS.getListaJugadores().size() >= 2) {
            System.out.println(ANSI_BLUE + "\n-----------------------------------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_RED + "    INICIO DE PARTIDA DE BALDOSAS" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.print("Jugador 1, usted usara las fichas BLANCAS (B)\n");
            Jugador unJ1 = this.seleccionJugador(unS.partidaActual());
            unS.partidaActual().getListaJugadores().add(unJ1);
            System.out.println(ANSI_BLUE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.println("Jugador 2, usted usara las fichas ROJAS (R)");
            Jugador unJ2 = this.seleccionJugador(unS.partidaActual());
            int jugadorEnUso = this.jugadorEnUso(unS.partidaActual(), unJ2);
            while (jugadorEnUso != 0) {
                System.out.println("\n" + "EL JUGADOR SELECCIONADO YA ESTA EN USO, SELECCIONE OTRO POR FAVOR: ");
                unJ2 = this.seleccionJugador(unS.partidaActual());
                jugadorEnUso = this.jugadorEnUso(unS.partidaActual(), unJ2);
            }
            unS.partidaActual().getListaJugadores().add(unJ2);
            unS.partidaActual().partidasJugadas(unJ1, unJ2);
            boolean seleccionTablero;
            System.out.println(ANSI_BLUE + "-----------------------------------------------------------------" + ANSI_RESET);
            int seleccionTableroJ = this.ingresarNroDosVal("1-Sin bordes, 2-Con bordes" + "\nSeleccione el tablero a jugar: ", "ERROR SELECCIONE UN TABLERO EXISTENTE", 1, 2);
            System.out.println(ANSI_BLUE + "-----------------------------------------------------------------" + ANSI_RESET);
            int cantFichasInvertidas = this.ingresarNroDosVal("Ingrese el numero de fichas invertidas necesarias para ganar(1 a 6): ", "ERROR INGRESE UN NUMERO ENTRE 1 Y 6", 1, 6);
            System.out.println(ANSI_BLUE + "-----------------------------------------------------------------" + ANSI_RESET);
            if (seleccionTableroJ == 1) {
                seleccionTablero = true;
            } else {
                seleccionTablero = false;
            }
            while (!seTerminoElJuego) {
                String movimiento = "";
                if (turno) {
                    jugadorJugando = unJ1;
                    unJ1.getFicha().setLetra("B");
                    jugadorEsperando = unJ2;
                    numeroJugadorJugando = 0;
                    numeroJugadorEsperando = 1;

                } else {
                    jugadorJugando = unJ2;
                    unJ2.getFicha().setLetra("R");
                    jugadorEsperando = unJ1;
                    numeroJugadorJugando = 1;
                    numeroJugadorEsperando = 0;

                }
                if (seleccionTablero && soloAlInicio) {
                    this.imprimirMatriz(sistema.partidaActual().getTablero(), ' ');
                } else {
                    if (!seleccionTablero && soloAlInicio) {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), 'o');
                    }
                }
                soloAlInicio = false;
                System.out.println("\nEs el turno de " + unS.partidaActual().getListaJugadores().get(numeroJugadorJugando).getAlias() + "(Fichas " + jugadorJugando.getFicha().getLetra() + ")" + "\n");
                System.out.println(ANSI_GREEN + "==================================" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Jugadas posibles:               " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Mover ficha: formato M XYXY     " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Saltar ficha: formato S XYXY    " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Mover baldosa: formato B XYXYXY " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Abandonar: formato X            " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_PURPLE + "\n|" + ANSI_RESET + "Solicitar empate: formato E     " + ANSI_PURPLE + "|" + ANSI_RESET
                        + ANSI_GREEN + "\n==================================" + ANSI_RESET);
                movimiento = this.ingresarTextoJuego("\nIngrese la jugada: ");
                resultados = this.jugadaPosible(unS, turno, movimiento, jugadorJugando, jugadorEsperando, numeroJugadorJugando, numeroJugadorEsperando, cantFichasInvertidas);
                while (resultados[6]) {
                    movimiento = this.ingresarTexto(ANSI_RED + "\nREINGRESE LA JUGADA: " + ANSI_RESET);
                    resultados = this.jugadaPosible(unS, turno, movimiento, jugadorJugando, jugadorEsperando, numeroJugadorJugando, numeroJugadorEsperando, cantFichasInvertidas);
                }
                if (resultados[1]) {
                    if (seleccionTablero) {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), ' ');
                    } else {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), 'o');
                    }
                }
                if (resultados[3]) {
                    if (seleccionTablero) {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), ' ');
                    } else {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), 'o');
                    }
                }
                if (resultados[4]) {
                    System.out.println("\n" + ANSI_RED + "¡¡¡GRACIAS POR JUGAR BALDOSAS!!!" + ANSI_RESET);
                    seTerminoElJuego = true;
                }
                if (resultados[2]) {
                    if (seleccionTablero) {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), ' ');
                    } else {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), 'o');
                    }
                }
                if (resultados[5]) {
                    System.out.println(ANSI_RED + "\n¡¡¡GRACIAS POR JUGAR BALDOSAS!!!" + ANSI_RESET);
                    seTerminoElJuego = true;
                }
                if (resultados[7]) {
                    System.out.println(ANSI_BLUE + "\n¡¡¡FELICIDADES " + jugadorJugando.getAlias() + " ERES EL GANADOR!!!" + ANSI_RESET);
                    System.out.println(ANSI_RED + "\n¡¡¡GRACIAS POR JUGAR BALDOSAS!!!" + ANSI_RESET);
                    seTerminoElJuego = true;
                }
                if (!resultados[1] && !resultados[2] && !resultados[3] && !resultados[4] && !resultados[5]) {
                    System.out.println(ANSI_RED + "\n¡ERROR MOVIMIENTO INVÁLIDA, SE MANTIENE EL TURNO DEL MISMO JUGADOR, VUELVA A INGRESAR LA JUGADA!" + ANSI_RESET);
                    resultados[6] = true;
                } else {
                    if (turno && (resultados[1] || resultados[2] || resultados[3])) {
                        turno = false;
                    } else {
                        if (!turno && (resultados[1] || resultados[2] || resultados[3])) {
                            turno = true;
                        }
                    }
                }
            }

        } else {
            System.out.println("\nIMPOSIBLE JUGAR BALDOSAS, NO SE ALCANZAN LA CANTIDAD MINIMA DE JUGADORES");
        }
        return unS.partidaActual();
    }

    public boolean[] jugadaPosible(Sistema unS, boolean colorJugador, String jugadas, Jugador jugadorJugando, Jugador jugadorEsperando, int jugando, int esperando, int cantFInvertidas) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String jugada = "";
        try {
            jugada = jugadas.toUpperCase();
            while ((jugada.length() != 1 && !jugada.substring(0, 1).equals("X") && !jugada.substring(0, 1).equals("E") && jugada.length() != 1) && (jugada.length() < 6 || jugada.length() > 12 && !jugada.substring(0, 2).equals("M ") && !jugada.substring(0, 2).equals("S ") && !jugada.substring(0, 2).equals("B "))) {
                jugada = this.ingresarTextoJuego(ANSI_RED + "\nREINGRESE LA JUGADA: " + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            jugada = this.ingresarTextoJuego(ANSI_RED + "\nREINGRESE LA JUGADA: " + ANSI_RESET);
        }
        int movimiento = unS.partidaActual().ingresoJuego(jugada);
        boolean resultados[] = new boolean[8];
        int contE = 0;
        switch (movimiento) {
            case 1:
                String coodenadaFilaO = this.pedirFilaO(jugada);
                String coodenadaColumnaO = this.pedirColumnaO(jugada);
                String coodenadaFilaD = this.pedirFilaD(jugada);
                String coodenadaColumnaD = this.pedirColumnaDMS(jugada);
                int filaO = unS.partidaActual().conversorFila(coodenadaFilaO);
                int columnaO = unS.partidaActual().conversorColumna(coodenadaColumnaO);
                if (columnaO > 9) {
                    columnaO--;
                }
                int filaD = unS.partidaActual().conversorFila(coodenadaFilaD);
                int columnaD = unS.partidaActual().conversorColumna(coodenadaColumnaD);
                if (columnaD != -1) {
                    if (columnaD > 9) {
                        columnaD--;
                    }
                    if (unS.partidaActual().getTablero().coordenadaValida(filaO, columnaO)) {
                        if (unS.partidaActual().getTablero().coordenadaValida(filaD, columnaD)) {
                            contE++;
                            if (sistema.meterFicha(filaO, columnaO, filaD, columnaD, jugadorJugando)) {
                                contE++;
                                if (unS.validarMovimientoFicha(filaO, columnaO, filaD, columnaD)) {
                                    contE++;
                                }
                            }
                        }
                    }
                    if (contE >= 3) {
                        unS.moverFicha(filaO, columnaO, filaD, columnaD, jugadorJugando);
                        resultados[1] = true;
                        boolean seTerminoElJuego = sistema.partidaActual().partidaGanada(filaD, columnaD, cantFInvertidas);
                        if (seTerminoElJuego) {
                            unS.partidaActual().partidasGanadas(jugadorEsperando, jugadorJugando);
                            resultados[7] = true;
                        }
                    } else {
                        resultados[6] = true;
                        System.out.println(ANSI_RED + "\n¡ERROR MOVIMIENTO INVÁLIDO, VUELVA A INGRESAR UNA JUGADA!" + ANSI_RESET);
                    }
                } else {
                    resultados[6] = true;
                    System.out.println(ANSI_RED + "\n¡ERROR MOVIMIENTO INVÁLIDO, VUELVA A INGRESAR UNA JUGADA!" + ANSI_RESET);
                }

                break;

            case 2:
                coodenadaFilaO = this.pedirFilaO(jugada);
                coodenadaColumnaO = this.pedirColumnaO(jugada);
                coodenadaFilaD = this.pedirFilaD(jugada);
                coodenadaColumnaD = this.pedirColumnaDMS(jugada);
                filaO = unS.partidaActual().conversorFila(coodenadaFilaO);
                columnaO = unS.partidaActual().conversorColumna(coodenadaColumnaO);
                if (columnaO > 9) {
                    columnaO--;
                }
                filaD = unS.partidaActual().conversorFila(coodenadaFilaD);
                columnaD = unS.partidaActual().conversorColumna(coodenadaColumnaD);
                if (columnaD > 9) {
                    columnaD--;
                }
                if (unS.partidaActual().getTablero().coordenadaValida(filaO, columnaO)) {
                    if (unS.partidaActual().getTablero().coordenadaValida(filaD, columnaD)) {
                        if (unS.partidaActual().getTablero().hayBaldosa(filaO, columnaO)) {
                            if (unS.saltaFicha(filaO, columnaO, filaD, columnaD, jugadorJugando)) {
                                resultados[2] = true;
                                boolean seTerminoElJuego = sistema.partidaActual().partidaGanada(filaD, columnaD, cantFInvertidas);
                                if (seTerminoElJuego) {
                                    unS.partidaActual().partidasGanadas(jugadorEsperando, jugadorJugando);
                                    resultados[7] = true;
                                }
                            }
                        }
                    }
                } else {
                    resultados[6] = true;
                    System.out.println(ANSI_RED + "\n¡ERROR MOVIMIENTO INVÁLIDO, VUELVA A INGRESAR UNA JUGADA!" + ANSI_RESET);
                }
                break;
            case 3:
                coodenadaFilaO = this.pedirFilaO(jugada);
                coodenadaColumnaO = this.pedirColumnaO(jugada);
                coodenadaFilaD = this.pedirFilaD(jugada);
                coodenadaColumnaD = this.pedirColumnaD(jugada);
                String coordenadaFilaOF = this.pedirFilaOFicha(jugada);
                String coordenadaColumnaOF = this.pedirColumnaOFicha(jugada);
                filaO = unS.partidaActual().conversorFila(coodenadaFilaO);
                columnaO = unS.partidaActual().conversorColumna(coodenadaColumnaO);
                if (columnaO > 9) {
                    columnaO--;
                }
                filaD = unS.partidaActual().conversorFila(coodenadaFilaD);
                columnaD = unS.partidaActual().conversorColumna(coodenadaColumnaD);
                if (columnaD > 9) {
                    columnaD--;
                }
                int filaOF = unS.partidaActual().conversorFila(coordenadaFilaOF);
                int columnaOf = unS.partidaActual().conversorColumna(coordenadaColumnaOF);
                if (columnaOf > 9) {
                    columnaOf--;
                }
                if (unS.partidaActual().getTablero().coordenadaValida(filaD, columnaD)) {
                    if (unS.validarFichaConBaldosa(filaD, columnaD, filaOF, columnaOf, jugadorJugando)) {
                        contE++;
                        if (unS.moverBaldosa(filaO, columnaO, filaD, columnaD, filaOF, columnaOf, jugadorJugando)) {
                            contE++;
                        }
                    }
                }
                if (contE >= 2) {
                    resultados[3] = true;
                    boolean seTerminoElJuego = sistema.partidaActual().partidaGanada(filaD, columnaD, cantFInvertidas);
                    if (seTerminoElJuego) {
                        unS.partidaActual().partidasGanadas(jugadorEsperando, jugadorJugando);
                        resultados[7] = true;
                    }
                } else {
                    resultados[6] = true;
                    System.out.println(ANSI_RED + "\n¡ERROR MOVIMIENTO INVÁLIDO, VUELVA A INGRESAR UNA JUGADA!" + ANSI_RESET);
                }

                break;
            case 4:
                System.out.println("\n" + ANSI_RED + "Jugador: " + unS.partidaActual().getListaJugadores().get(jugando).getAlias() + " ha solicitado terminar la pertida" + ANSI_RESET);
                System.out.println("\n" + ANSI_RED + "Jugador: " + unS.partidaActual().getListaJugadores().get(esperando).getAlias() + " es el ganador" + ANSI_RESET);
                unS.partidaActual().partidasGanadas(jugadorJugando, jugadorEsperando);
                resultados[4] = true;
                break;
            case 5:
                String decision = this.ingresarTextoJuego("\nEl jugador " + unS.partidaActual().getListaJugadores().get(jugando).getAlias() + " ha solicitado el empate, desea aceptarlo (S/N)?: ");
                while (!decision.equals("S") && !decision.equals("N")) {
                    decision = this.ingresarTexto("\nIngrese una respuesta válida (s/n): ");
                }
                if (decision.equals("S")) {
                    unS.partidaActual().partidasEmpatadas(jugadorJugando, jugadorEsperando);
                    System.out.println("\n" + ANSI_RED + "EL EMPATE PROPUESTO POR EL JUGADOR " + unS.partidaActual().getListaJugadores().get(jugando).getAlias() + " FUE ACEPTADO POR EL JUGADOR " + unS.partidaActual().getListaJugadores().get(esperando).getAlias() + ANSI_RESET);
                    System.out.println(ANSI_RED + "\nNO HUBO GANADORES EN ESTA PARTIDA" + ANSI_RESET);
                    resultados[5] = true;
                } else {
                    if (decision.equals("N")) {
                        System.out.println(ANSI_RED + "\nSE CONTINUA LA PARTIDA, EL JUGADOR " + jugadorEsperando.getAlias() + " NO HA ACEPTADO EL EMPATE" + ANSI_RESET);
                        resultados[6] = true;
                    }
                }
                break;
            case 6:
                resultados[6] = true;
                break;
        }
        return resultados;
    }

    public void menuPrincipal() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        boolean sePrecargo = false;
        int opcion = 0;
        Partida partida;
        sistema = new Sistema();
        Tablero t = new Tablero();
        System.out.println("\n" + ANSI_BLUE + "           =========================" + ANSI_RESET
                + ANSI_PURPLE + "\n           |" + ANSI_RED + " BIENVENIDOS A BALDOSAS" + ANSI_RESET + ANSI_PURPLE + "|" + ANSI_RESET
                + ANSI_BLUE + "\n           =========================" + ANSI_RESET);
        while (opcion != 5) {
            System.out.println(ANSI_GREEN + "\n=================================================" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + ANSI_RED + "          MENU PRINCIPAL DE BALDOSAS          " + ANSI_RESET + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "1-Registro jugador                            " + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "2-Precarga                                    " + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "3-Jugar Baldosas                              " + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "4-Ranking                                     " + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "5-Fin                                         " + ANSI_PURPLE + " |" + ANSI_RESET
                    + ANSI_GREEN + "\n=================================================" + ANSI_RESET);
            boolean aliasRepetido;
            opcion = this.ingresarNroDosVal("\nPor favor ingrese una opción del menú: ", "ERROR, OPCIÓN INVÁLIDA", 1, 5);
            switch (opcion) {
                case 1:
                    System.out.println(ANSI_BLUE + "\n----------------------------------------------" + ANSI_RESET
                            + "\n" + ANSI_RED + "              REGISTRO DE JUDADOR" + ANSI_RESET
                            + ANSI_BLUE + "\n----------------------------------------------" + ANSI_RESET);
                    Ficha unaFicha = new Ficha();
                    //ingreso y guardo en el sistema los datos de un jugador
                    String nombre = this.ingresarTexto("Ingrese un nombre del jugador: ");
                    int edad = this.ingresarNroDosVal("Ingrese la edad del jugador(1 a 100): ", "ERROR DEBE INGRESAR UNA EDAD VALIDA", 1, 100);
                    String alias = this.ingresarTexto("Ingrese un alias: ");
                    aliasRepetido = this.getSistema().aliasUnico(alias);
                    while (aliasRepetido) {
                        System.out.println("ERROR EL ALIAS YA ESTA EN USO, POR FAVOR INGRESE OTRO");
                        alias = this.ingresarTexto("Ingrese un alias : ");
                        aliasRepetido = this.getSistema().aliasUnico(alias);
                    }
                    System.out.println(ANSI_BLUE + "----------------------------------------------" + ANSI_RESET);
                    sistema.registrarJugador(unaFicha, nombre, edad, alias);
                    break;
                case 2:
                    t = new Tablero();
                    partida = new Partida();
                    sistema.agregarPartida(partida);
                    sistema.partidaActual().setTablero(t);
                    this.subMenuPrecarga();
                    sePrecargo = true;
                    break;
                case 3:
                    if (!sePrecargo) {
                        t = new Tablero();
                        partida = new Partida();
                        sistema.agregarPartida(partida);
                        sistema.partidaActual().setTablero(t);
                        sistema.precargaAzar();
                        this.jugarBaldosas(sistema);
                    } else {
                        if (sePrecargo) {
                            this.jugarBaldosas(sistema);
                            sePrecargo = false;
                        }
                    }
                    break;
                case 4:
                    if (sistema.listaOrdenada().isEmpty()) {
                        System.out.println("\nIMPOSIBLE MOSTRAR EL RANKING DE JUGADORES, NO SE HA INGRESADO NINGUNO EN EL SISTEMA");
                    } else {
                        System.out.println(ANSI_BLUE + "\n---------------------------------------------------------------------------" + ANSI_RESET
                                + "\n" + ANSI_RED + "                       RANKING DE JUGADORES" + ANSI_RESET
                                + ANSI_BLUE + "\n---------------------------------------------------------------------------" + ANSI_RESET);
                        sistema.listaOrdenada();
                        sistema.mostrarDatos(sistema.listaOrdenada());
                        System.out.println(ANSI_BLUE + "---------------------------------------------------------------------------" + ANSI_RESET);
                    }
                    break;

                case 5:
                    String confirmacion = this.ingresarTexto("ESTA SEGURO QUE DESEA SALIR? (s/n): ");
                    while (!confirmacion.equals("n") && !confirmacion.equals("s")) {
                        confirmacion = this.ingresarTexto("Ingrese una respuesta válida (s/n): ");
                    }
                    if (confirmacion.equals("n")) {
                        opcion = 0;
                    }
                    break;
            }
        }
    }

    public void subMenuPrecarga() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        int opcion = 0;
        while (opcion != 4) {
            System.out.println(ANSI_GREEN + "\n===========================" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + ANSI_RED + "  SELECCION DE PRECARGA  " + ANSI_PURPLE + "|" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "1-Precarga al azar       " + ANSI_PURPLE + "|" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "2-Precarga a mano        " + ANSI_PURPLE + "|" + ANSI_RESET
                    + ANSI_PURPLE + "\n|" + ANSI_RESET + "3-Precarga fija          " + ANSI_PURPLE + "|" + ANSI_RESET
                    + ANSI_GREEN + "\n===========================" + ANSI_RESET);
            opcion = this.ingresarNroDosVal("\nPor favor ingrese una opción del menú: ", "ERROR, OPCIÓN INVALIDA", 1, 4);
            switch (opcion) {
                case 1:
                    sistema.precargaAzar();
                    opcion = 4;
                    break;
                case 2:
                    int i = 0;
                    while (i < 12) {
                        this.imprimirMatriz(sistema.partidaActual().getTablero(), ' ');
                        String color = "R";
                        if (i < 6) {
                            color = "B";
                        } else {
                            color = "R";
                        }
                        if (color.equals("B")) {
                            System.out.println("Ficha Blanca");
                        } else {
                            if (color.equals("R")) {
                                System.out.println("Ficha Roja");
                            }
                        }
                        String coordenada = this.pedirCoordenadasPrecargaMano();
                        while (coordenada.length() < 2 || coordenada.length() > 3) {
                            coordenada = this.pedirCoordenadasPrecargaMano();
                        }
                        String columna = pedirColumnaPrecargaMano(coordenada);
                        String fila = pedirFilaPrecargaMano(coordenada);
                        boolean sePudo = sistema.agregarFichaPrecargaMano(fila, columna, color);
                        if (sePudo) {
                            i++;
                        } else {
                            System.out.println("Coordenada no válida. Ingresela nuevamente");
                        }
                        System.out.println(ANSI_BLUE + "\n--------------------------------------------------" + ANSI_RESET);
                    }
                    if (i == 12) {
                        opcion = 4;
                    }
                    break;
                case 3:
                    sistema.precargarFija();
                    opcion = 4;
                    break;
            }
        }
    }
}
