/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author joseangel
 */
public class FuzzyPlayer extends Player{
    
 
    public FuzzyPlayer (Player other){
        super(other);
    }
    
  
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions preference=super.move(direction, validMoves);
        return Dice.nextStep(preference, validMoves, this.getIntelligence());
    }
    
    /**
     * Calcula ataque total del fuzzyplayer, teniendo en cuenta su fuerza y el poder
     * de sus armas
     * @return Devuelve la suma de su fuerza (a partir del método de Dice) y
     * del poder de las armas
     */
    @Override
    public float attack(){
        return (Dice.intensity(this.getStrength())+this.sumWeapons());
    }
    
  
    @Override
    protected float defensiveEnergy(){
        return (Dice.intensity(this.getIntelligence())+this.sumShields());
    }
    
   
    @Override
    public String toString(){    
        return "(Fuzzy) " + super.toString();
    }
}
