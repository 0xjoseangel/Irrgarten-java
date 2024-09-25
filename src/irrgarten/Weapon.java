/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
public class Weapon {
    private float power;
    private int uses;
    
    public Weapon(float p, int u) {
        power = p;
        uses = u;
    }
    
    public float attack() {
        float result;
        if (uses > 0) {
            uses--;
            result = power;
        } else {
            result = 0f;
        }
        return result;
    }
    
    public String toString() {
        String info = "W[" + power + ", " + uses + "]";
        
        return info;
    }
}
