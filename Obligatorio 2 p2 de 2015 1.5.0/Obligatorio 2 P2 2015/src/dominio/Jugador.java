package dominio;

import java.io.Serializable;

public class Jugador implements Serializable, Comparable<Jugador> {

    private Ficha ficha;
    private String nombre;
    private int edad;
    private String alias;
    private int partidasJugadas;
    private int partidasPerdidas;
    private int partidasGanadas;
    private int partidasEmpatadas;
    private boolean esMiTurno;

    public Jugador() {
        this.setNombre("Sin nombre");
        this.setEdad(0);
        this.setAlias("Sin alias");
        this.setPartidasJugadas(0);
        this.setPartidasPerdidas(0);
        this.setPartidasGanadas(0);
        this.setPartidasEmpatadas(0);
        this.setEsMiTurno(true);
    }

    public Jugador(String unNombre, int unaEdad, String unAlias, int unaPartidasJugadas, int unaPartidasPerdidas, int unaPartidasGanadas, int unaPartidaEmpatada, boolean unTurno) {
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
        this.setAlias(unAlias);
        this.setPartidasJugadas(unaPartidasJugadas);
        this.setPartidasPerdidas(unaPartidasPerdidas);
        this.setPartidasGanadas(unaPartidasGanadas);
        this.setPartidasEmpatadas(unaPartidaEmpatada);
        this.setPartidasEmpatadas(0);
        this.setEsMiTurno(unTurno);
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha unaFicha) {
        this.ficha = unaFicha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String unAlias) {
        this.alias = unAlias;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int unaPartidasJugadas) {
        this.partidasJugadas = unaPartidasJugadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int unaPartidasPerdidas) {
        this.partidasPerdidas = unaPartidasPerdidas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int unaPartidasGanadas) {
        this.partidasGanadas = unaPartidasGanadas;
    }

    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }

    public void setPartidasEmpatadas(int unaPartidaEmpatada) {
        this.partidasEmpatadas = unaPartidaEmpatada;
    }

    public boolean getEsMiTurno() {
        return esMiTurno;
    }

    public void setEsMiTurno(boolean unTurno) {
        esMiTurno = unTurno;
    }

    @Override
    public String toString() {
        return "\nNombre del jugador: " + this.getNombre()
                + ", \nEdad del jugador: " + this.getEdad()
                + ", \nAlias del jugador: " + this.getAlias();
    }

    @Override
    public boolean equals(Object obj) {
        Jugador unJugador = ((Jugador) obj);
        return this.getAlias().equals(unJugador.getAlias());
    }

    @Override
    public int compareTo(Jugador unJugador) {
        int retorno = unJugador.getPartidasGanadas() - this.getPartidasGanadas();
        if (retorno == 0) {
            retorno = unJugador.getAlias().compareTo(this.getAlias());
        }
        return retorno;
    }
}
