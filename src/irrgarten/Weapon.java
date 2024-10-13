/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * La clase Weapon representa un arma con una potencia de ataque y un número limitado de usos.
 * Cada vez que el arma se utiliza, su número de usos disponibles disminuye. Si se agotan los
 * usos, el arma ya no inflige daño.
 * 
 * @author joseangel
 */
public class Weapon {
    private float power;  // Potencia de ataque del arma
    private int uses;     // Número de usos restantes del arma
    
    /**
     * Constructor que inicializa un arma con la potencia de ataque y el número de usos especificados.
     * 
     * @param p La potencia de ataque del arma.
     * @param u El número de usos disponibles para el arma.
     */
    public Weapon(float p, int u) {
        power = p;
        uses = u;
    }
    
    /**
     * Método que permite atacar con el arma. Si el arma tiene usos restantes, disminuye el número
     * de usos en 1 y devuelve la potencia de ataque. Si no quedan usos, devuelve 0.
     * 
     * @return La potencia de ataque si hay usos restantes, o 0 si se han agotado los usos.
     */
    public float attack() {
        float result;
        if (uses > 0) {
            uses--; // Disminuir el número de usos en 1
            result = power;
        } else {
            result = 0f; // Si no hay más usos, el ataque no tiene potencia
        }
        return result;
    }
    
    /**
     * Devuelve una representación en formato de cadena del arma, mostrando su potencia de ataque
     * y los usos restantes.
     * 
     * @return Una cadena en el formato "W[power, uses]".
     */
    @Override
    public String toString() {
        String info = "W[" + power + ", " + uses + "]";
        return info;
    }
    
    /**
     * Implementa la decisión de si un arma debe ser descartada.
     * 
     * @return {@code true} si el elemento debe ser descartado, {@code false} en caso contrario.
     */
    public boolean discard() {
        return Dice.discardElement(uses);
    }
    
    // Preguntar si hay que anadirlo
    
    public float getPower() {
        return power;
    }
}
