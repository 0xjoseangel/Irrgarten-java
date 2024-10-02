/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * La clase Shield representa un escudo con una capacidad de protección y un número limitado
 * de usos. A medida que el escudo se utiliza, el número de usos disponibles disminuye.
 * Una vez que se agotan los usos, el escudo deja de ofrecer protección.
 * 
 * @author joseangel
 */
public class Shield {
    private float protection;  // Capacidad de protección del escudo
    private int uses;          // Número de usos restantes del escudo
    
    /**
     * Constructor que inicializa un escudo con la cantidad de protección y usos proporcionados.
     * 
     * @param p La capacidad de protección del escudo.
     * @param u El número de usos disponibles para el escudo.
     */
    public Shield(float p, int u) {
        protection = p;
        uses = u;
    }
    
    /**
     * Método que devuelve el valor de protección del escudo si tiene usos restantes.
     * Cada vez que se llama a este método, el número de usos disminuye en 1.
     * Si el número de usos es 0, el escudo ya no proporciona protección.
     * 
     * @return El valor de protección del escudo si hay usos restantes, o 0 si los usos se han agotado.
     */
    public float protect() {
        float result;
        if (uses > 0) {
            uses--; // Disminuir el número de usos en 1
            result = protection;
        } else {
            result = 0f; // Si no hay más usos, la protección es 0
        }
        return result;
    }
    
    /**
     * Devuelve una representación en formato de cadena del escudo, mostrando su nivel de 
     * protección y los usos restantes.
     * 
     * @return Una cadena en el formato "W[protection, uses]".
     */
    @Override
    public String toString() {
        String info = "W[" + protection + ", " + uses + "]";
        return info;
    }
    
    /**
     * Implementa la decisión de si un escudo debe ser descartado.
     * 
     * @return {@code true} si el elemento debe ser descartado, {@code false} en caso contrario.
     */
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
