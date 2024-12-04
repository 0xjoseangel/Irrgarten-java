/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
abstract public class LabyrinthCharacter {
    private final int INITIAL_POS = -1;
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter (String name, float intelligence, float strength, float health) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        
        this.row = INITIAL_POS;
        this.col = INITIAL_POS;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other) {
        this.name=other.name;
        this.intelligence=other.intelligence;
        this.strength=other.strength;
        this.health=other.health;
        
        this.row=other.row;
        this.col=other.col;
    }
    
    public boolean dead() {
        return health <= 0;
    }
    
    public int getCol() {
      return this.col;
    }
  
    public int getRow() {
      return this.row;
    }
    
    protected float getIntelligence(){
        return this.intelligence;
    }
    
    
    protected float getStrength(){
        return this.strength;
    }
    
    
    protected float getHealth(){
        return this.health;
    }
    
 
    protected void setHealth(float health){
        this.health=health;
    }
    
    
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    
    @Override
    public String toString(){
        return this.name + "\n" +
           "Inteligencia: " + this.intelligence + "\n" +
           "Fuerza: " + this.strength + "\n" + 
           "Salud: " + this.health + "\n" +
           "Coordenadas: " + "[" + this.row + ", " + this.col + "]\n";
    }
    
    
    protected void gotWounded(){
        this.health--;
    }
    
    
    public abstract float attack();
    
 
    public abstract boolean defend (float attack);
    
}
