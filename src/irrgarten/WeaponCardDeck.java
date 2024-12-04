/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
public class WeaponCardDeck extends CardDeck<Weapon> {

    @Override
    protected void addCards() {
        for (int i=0; i<ShieldCardDeck.MAX; i++) {
            this.addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
    
}
