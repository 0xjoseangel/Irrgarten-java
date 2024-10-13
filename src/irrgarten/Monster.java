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
public class Monster {
    private final int INITIAL_POS = -1;
    private static final int INITIAL_HEALTH = 5; 
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    /**
     * Constructor de la clase Monster. 
     * Inicializa los atributos del monstruo y lo coloca fuera del laberinto.
     *
     * @param name El nombre del monstruo.
     * @param intelligence El nivel de inteligencia del monstruo.
     * @param strength La fuerza del monstruo, utilizada para los ataques.
     */
    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        // Al no estar en el mapa, suponemos que su posicion es -1,-1
        this.row = this.INITIAL_POS;
        this.col = this.INITIAL_POS;
    }

    /**
     * Verifica si el monstruo está muerto.
     *
     * @return true si la salud es 0, false de lo contrario.
     */
    public boolean dead() {
        return this.health == 0;
    }

    /**
     * Realiza un ataque basado en la fuerza del monstruo.
     * 
     * Utiliza un dado para determinar la intensidad del ataque en función de su fuerza.
     * 
     * @return El valor del ataque basado en la fuerza del monstruo.
     */
    public float attack() {
        return Dice.intensity(this.strength);
    }

    /**
     * Establece una nueva posición para el monstruo en el laberinto.
     * 
     * @param row La nueva fila en la que se encuentra el monstruo.
     * @param col La nueva columna en la que se encuentra el monstruo.
     */
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Genera una representación en forma de cadena del estado actual del monstruo.
     * Incluye el nombre, inteligencia, fuerza, salud y posición del monstruo.
     *
     * @return Una cadena que representa el estado del monstruo.
     */
    public String toString() {
        String out = "Name: " + this.name + "\n" + 
                     "Intelligence: " + this.intelligence + "\n" +
                     "Strength: " + this.strength + "\n" +
                     "Health: " + this.health + "\n" + 
                     "Pos: [" + this.row + ", " + this.col + "]\n";
        return out;
    }

    /**
     * Disminuye la salud del monstruo en una unidad cuando es herido.
     */
    public void gotWounded() {
        this.health--;
    }

    /**
     * Método de defensa del monstruo.
     * 
     * En la versión actual no implementa ninguna lógica, 
     * pero siempre devuelve true. Se espera que este método sea completado en prácticas futuras.
     * 
     * @param recivedAttack El valor del ataque recibido.
     * @return true siempre.
     */
    public boolean defend(float recivedAttack) {
        return true;
    }
}
