/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
public class Shield {
    private float protection;
    private int uses;
    
    public Shield(float p, int u){
        protection = p;
        uses = u;
    }
    
    public float protect(){
        float result;
        if (uses > 0) {
            uses--;
            result = protection;
        } else {
            result = 0f;
        }
        return result;
    }
    
    public String toString() {
        String info = "W[" + protection + ", " + uses + "]";
        
        return info;
    }
}
