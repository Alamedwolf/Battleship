package com.example.ivangmg.batallanavalenclase;

/**
 * Created by inmap on 17/01/17.
 */

import android.util.Log;

import java.util.Random;

/** Representa un juento de Batalla Naval **/
public class Batalla {
    /** Número de filas del mapa */
    public static final int NUM_FILAS = 5;
    /** Número de columnas del mapa */
    public static final int NUM_COLUMNAS = 5;
    /** Indica que el barco está en la dirección horizontal */
    public static final int HORIZONTAL=0;
    /** Indica que el barco está en la dirección horizontal */
    public static final int VERTICAL=1;
    /** Sentido de la dirección positiva */
    public static final int POSITIVO =1;
    /** Sentido de la dirección positiva */
    public static final int NEGATIVO =-1;
    /** Número de barcos que se generan inicialmente **/
    public static final int NUM_BARCOS=3;
    /** Tamaño del barco */
    public static final int TAM_BARCO=3;
    /** Mapa de posiciones de los barcos. Cada posición si hay en barco. En caso contrario,
     * se asume que hay agua.
     */
    private boolean[][] mapa;
    /** Generardor de números aleatorios */
    private Random random;

    /** Constructor de la clase */
    public Batalla (){
        random= new Random();
        iniciarMapa();
        generarBarcos();
    }

    /** Iniciaos el mapa con todo agua */
    public void iniciarMapa(){
        mapa = new boolean[NUM_FILAS][NUM_COLUMNAS];
        for(int i=0; i<mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j]=false;
            }
        }
    }

    /** Determina si hay un barco en una posición */
    public boolean hayBarco(int x, int y){
        return mapa[x][y];
    }

    /** Genera varios barcos y los sitúa en el mapa */
    public void generarBarcos(){
        for(int i=0; i<NUM_BARCOS;i++) {
            generarBarco(TAM_BARCO);
        }
    }

    /** Método que genera un barco y lo sitúa en el mapa
     * @param tam Tamaño del barco. */
    public void generarBarco(int tam){
        // Mientras no encontrado espacio, intentamos situar un barco
        boolean encontradoEspacio=false;
        while(!encontradoEspacio) {
            // Elegir posición aleatoria
            int x = random.nextInt(NUM_FILAS);
            int y = random.nextInt(NUM_COLUMNAS);
            // Elegir dirección
            int dir = elegirDireccion();
            // Calcular sentido
            int sentido = calcularSentidoBarco(x,y,dir, tam);
            // Ver si hay espacio
            encontradoEspacio = hayEspacioParaBarco(x, y, dir, sentido, tam);
            // Posicionar el barco si hay espacio
            if (encontradoEspacio) {
                escribirBarco(x,y,dir,sentido, tam);
            }
        }
    }

    /** Indica si hay espacio disponible un barco **/
    public boolean hayEspacio(int x, int y){
        return !mapa[x][y];
    }
    /** Elige una dirección de forma aleatoria. Devolverá constante HORIZONTAL o VERTICAL. */
    public int elegirDireccion(){
        int r = random.nextInt(2);
        if(r==0) {
            return HORIZONTAL;
        }else {
            return VERTICAL;
        }
    }
    /** Calcula sentido de un barco de cierto tamaño
     * @param x posición X del barco
     * @param y posición Y del barco
     * @param dir Dirección del barco
     * @param tam Tamaño del barco
      */
    public int calcularSentidoBarco(int x, int y, int dir, int tam){
        // Distingue entre direcciones posibles
        if (dir == HORIZONTAL){
            if(y<=NUM_COLUMNAS/2){
                return POSITIVO;
            }else{
                return NEGATIVO;
            }
            // Si vertical
        }else{
            if(x<=NUM_COLUMNAS/2){
                return POSITIVO;
            }else{
                return NEGATIVO;
            }
        }
    }
    /** Situa el barco en una posción, dirección y sentido */
    public void escribirBarco(int x, int y, int dir, int sentido, int tam){
        int xi, yi;
        xi=x;
        yi=y;
        for (int i = 0; i< tam; i++){
            setMapaPos(xi,yi,true);
            if(dir==VERTICAL) {
                xi += sentido;
            }else {
                yi += sentido;
            }
        }
    }
    /** Situa el barco en una posción, dirección y sentido */
    public boolean hayEspacioParaBarco(int x, int y, int dir, int sentido, int tam){
        int xi, yi;
        xi=x;
        yi=y;
        boolean hayEspacio = true;
        for (int i = 0; i< tam; i++){
            if(getMapaPos(xi,yi))
                hayEspacio=false;
            if(dir==VERTICAL) {
                xi += sentido;
            }else {
                yi += sentido;
            }
        }
        return hayEspacio;
    }

    /** Cambiar posición del mapa comprobando de los límites */
    public void setMapaPos(int x, int y, boolean value){
        if(x<NUM_FILAS && y <NUM_COLUMNAS) {
            mapa[x][y] = true;
        }else{
            Log.e("Batalla", "Intentando escribir Fuera de posición");
        }
    }
    /** Devuelve la posición del mapa comprobando de los límites */
    public boolean getMapaPos(int x, int y){
        if(x<NUM_FILAS && y <NUM_COLUMNAS) {
            return mapa[x][y];
        }else{
            Log.e("Batalla", "Intentando acceder Fuera de posición");
            return false;
        }
    }


}
