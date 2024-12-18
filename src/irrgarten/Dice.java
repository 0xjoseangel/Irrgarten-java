/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;
import java.util.ArrayList;

/**
 * La clase Dice proporciona varios métodos relacionados con la probabilidad y 
 * números aleatorios para determinar diferentes aspectos de un juego, como 
 * posiciones en el tablero, recompensas en combates, atributos de jugadores 
 * y monstruos, así como si un elemento debe ser descartado.
 * 
 * @author joseangel
 */
public class Dice {
    private static final int MAX_USES = 5; // Número máximo de usos de armas y escudos
    private static final float MAX_INTELIGENCE = 10f; // Valor máximo para la inteligencia de jugadores y monstruos
    private static final float MAX_STRENGTH = 10f; // Valor máximo para la fuerza de jugadores y monstruos
    private static final float RESURRECT_PROB = 0.3f; // Probabilidad de que un jugador sea resucitado en cada turno
    private static final int WEAPONS_REWARD = 2; // Número máximo de armas recibidas al ganar un combate
    private static final int SHIELDS_REWARD = 3; // Número máximo de escudos recibidos al ganar un combate
    private static final int HEALTH_REWARD = 5; // Número máximo de unidades de salud recibidas al ganar un combate
    private static final int MAX_ATTACK = 3; // Máxima potencia de las armas
    private static final int MAX_SHIELD = 2; // Máxima potencia de los escudos
    
    public static final Random generator = new Random();
    
    /** 
     * Devuelve una posición aleatoria (fila o columna) en el tablero, basada 
     * en el número total de filas o columnas.
     * 
     * @param max El número total de filas o columnas del tablero. Debe ser mayor que 0.
     * @return Un número entero aleatorio entre 0 (inclusive) y max (exclusivo).
     */
    public static int randomPos(int max) {
        return generator.nextInt(max);
    }
    
    /**
     * Devuelve el índice del jugador que comenzará la partida.
     * 
     * @param nplayers El número de jugadores en la partida. Debe ser mayor que 0.
     * @return Un número entero aleatorio entre 0 (inclusive) y nplayers (exclusivo).
     */
    public static int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }
    
    /**
     * Genera un valor aleatorio de inteligencia para un jugador o monstruo.
     * 
     * @return Un valor de inteligencia entre 0 (inclusive) y el valor máximo 
     * definido por MAX_INTELIGENCE (exclusivo).
     */
    public static float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELIGENCE;
    }
    
    /**
     * Genera un valor aleatorio de fuerza para un jugador o monstruo.
     * 
     * @return Un valor de fuerza entre 0 (inclusive) y el valor máximo definido 
     * por MAX_STRENGTH (exclusivo).
     */
    public static float randomStrength() {
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    /**
     * Determina si un jugador debe ser resucitado, basado en una probabilidad fija.
     * 
     * @return Un valor booleano que indica si el jugador es resucitado (true) o no (false).
     */
    public static boolean resurrectPlayer() {
        return generator.nextFloat() < RESURRECT_PROB; // nextFloat() devuelve un numero entre 0.0 y 1.0
    }
    
    /**
     * Genera un número aleatorio de armas que se entregan como recompensa.
     * 
     * @return Un número entero entre 0 (inclusive) y WEAPONS_REWARD (exclusivo).
     */
    public static int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    /**
     * Genera un número aleatorio de escudos que se entregan como recompensa.
     * 
     * @return Un número entero entre 0 (inclusive) y SHIELDS_REWARD (exclusivo).
     */
    public static int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    /**
     * Genera un número aleatorio de unidades de salud que se entregan como recompensa.
     * 
     * @return Un número entero entre 0 (inclusive) y HEALTH_REWARD (exclusivo).
     */
    public static int healthReward() {
        return generator.nextInt(HEALTH_REWARD);
    }
    
    /**
     * Genera un valor de potencia de arma aleatorio, entre 0 y el valor máximo permitido.
     * 
     * @return Un valor de potencia de arma entre 0 (inclusive) y MAX_ATTACK (exclusivo).
     */
    public static float weaponPower() {
        return generator.nextFloat() * MAX_ATTACK;
    }
    
    /**
     * Genera un valor de potencia de escudo aleatorio, entre 0 y el valor máximo permitido.
     * 
     * @return Un valor de potencia de escudo entre 0 (inclusive) y MAX_SHIELD (exclusivo).
     */
    public static float shieldPower() {
        return generator.nextFloat() * MAX_SHIELD;
    }
    
    /**
     * Genera un número aleatorio de usos restantes para un arma o escudo.
     * 
     * @return Un número entero entre 0 (inclusive) y MAX_USES (exclusivo).
     */
    public static int usesLeft() {
        return generator.nextInt(MAX_USES);
    }
    
    /**
     * Genera un valor aleatorio basado en la competencia de un jugador o monstruo.
     * 
     * @param competence El valor de competencia que limita el valor aleatorio generado.
     * @return Un valor flotante entre 0 (inclusive) y competence (exclusivo).
     */
    public static float intensity(float competence) {
        return generator.nextFloat() * competence;
    }
    
    /**
     * Determina si un elemento (arma o escudo) debe ser descartado, basado en la cantidad de usos restantes.
     * 
     * @param usesLeft El número de usos restantes del elemento. Debe ser mayor o igual a 0.
     * @return {@code true} si el elemento debe ser descartado, {@code false} en caso contrario.
     */
    public static boolean discardElement(int usesLeft) {
        // Si usesLeft es 0, siempre descartamos el elemento
        if (usesLeft == 0) {
            return true;
        }
        // Si usesLeft es el máximo, nunca descartamos el elemento
        if (usesLeft == MAX_USES) {
            return false;
        }
        
        // Probabilidad de descartar inversamente proporcional a los usos restantes
        double probability = 1.0 - ((double) usesLeft / MAX_USES);

        // Generamos un número aleatorio entre 0.0 y 1.0 usando el objeto Random
        double randomValue = generator.nextDouble();

        // Devolvemos true si el valor aleatorio es menor o igual a la probabilidad
        return randomValue <= probability;
    }
    
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence) {
        Directions rdo = preference;
        
          if(Dice.randomIntelligence() > intelligence){
            int i = generator.nextInt(validMoves.size());
            rdo=validMoves.get(i);          
        }
        
        return rdo;
    }
}
