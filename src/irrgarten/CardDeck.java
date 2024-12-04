/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author joseangel
 */
abstract public class CardDeck <T extends CombatElement>{
    
    private ArrayList <T> cardDeck;
    
   
    protected static final int MAX=50;
    
    
    public CardDeck(){
        cardDeck=new ArrayList<>();
    }
    
  
    protected abstract void addCards();    
    
  
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    public T nextCard(){
        if(this.cardDeck.size()<=0) {
            this.addCards();
            // Barajamos...
            Collections.shuffle(this.cardDeck); 
        }
        T seleccionada=this.cardDeck.get(0);
        this.cardDeck.remove(0);
        
        return seleccionada;
        
    }
}
