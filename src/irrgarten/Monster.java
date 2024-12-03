/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * La clase Monster representa un monstruo dentro del laberinto. 
 * Cada monstruo tiene atributos como nombre, inteligencia, fuerza, salud, 
 * y su posición dentro del laberinto (fila y columna).
 * 
 * Los monstruos pueden atacar, defenderse, ser heridos y moverse por el laberinto.
 * 
 * Su posición inicial está fuera del laberinto (-1, -1).
 * 
 * @author joseangel
 */
public class Monster extends LabyrinthCharacter {
   
    private static final int INITIAL_HEALTH = 5; 
    
    

    /**
     * Constructor de la clase Monster. 
     * Inicializa los atributos del monstruo y lo coloca fuera del laberinto.
     *
     * @param name El nombre del monstruo.
     * @param intelligence El nivel de inteligencia del monstruo.
     * @param strength La fuerza del monstruo, utilizada para los ataques.
     */
    public Monster(String name, float intelligence, float strength) {
        super(name, intelligence, strength, Monster.INITIAL_HEALTH);
    }

   
    /**
     * Realiza un ataque basado en la fuerza del monstruo.
     * 
     * Utiliza un dado para determinar la intensidad del ataque en función de su fuerza.
     * 
     * @return El valor del ataque basado en la fuerza del monstruo.
     */
    @Override
    public float attack() {
        return Dice.intensity(this.getStrength());
    }

    /**
     * Defiende al personaje de un ataque recibido. Evalúa si el ataque es lo suficientemente 
     * fuerte para herir al personaje, basándose en su inteligencia como parámetro de resistencia.
     * Si el personaje es herido, se verifica si esto lo mata.
     * 
     * @param recivedAttack la intensidad del ataque recibido
     * @return true si el personaje muere después de recibir el ataque, false si sigue vivo
     */
    @Override
    public boolean defend(float recivedAttack) {
        boolean isDead = dead();
        
        if (!isDead) {
            if (Dice.intensity(this.getIntelligence()) < recivedAttack) {
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
}
