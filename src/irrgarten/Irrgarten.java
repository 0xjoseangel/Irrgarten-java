/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
public class Irrgarten {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world");
        
        // Prueba de enums
        GameCharacter player = GameCharacter.PLAYER;
        
        System.out.println(player);
        
        Directions dir = Directions.DOWN;
        
        Orientation or = Orientation.HORIZONTAL;
        
        // Prueba weapon
        System.out.println("Prueba clase weapon:");
        Weapon weapon = new Weapon(2f, 2);
        
        System.out.println(weapon.toString());
        
        System.out.println(weapon.attack());
        System.out.println(weapon.toString());

        System.out.println(weapon.attack());
        System.out.println(weapon.toString());

        System.out.println(weapon.attack());
        System.out.println(weapon.toString());

        // Prueba clase Shield
        System.out.println("Prueba clase shield:");
        Shield shield = new Shield(2f, 2);
        
        System.out.println(shield.toString());
        
        System.out.println(shield.protect());
        System.out.println(shield.toString());

        System.out.println(shield.protect());
        System.out.println(shield.toString());

        System.out.println(shield.protect());
        System.out.println(shield.toString());
        
    }
    
}
