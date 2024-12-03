/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
abstract public class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect() {
        float result;
        if (uses > 0) {
            uses--; // Disminuir el número de usos en 1
            result = effect;
        } else {
            result = 0f; // Si no hay más usos, el ataque no tiene potencia
        }
        return result;
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
    
    @Override
    public String toString() {
        return "[" + effect + ", " + uses + "]";
    }
}