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
public class Player {
  private static final int MAX_WEAPONS = 2;
  private static final int MAX_SHIELDS = 3;
  private static final int INITIAL_HEALTH = 10;
  private static final int HITS2LOSE = 3;
  private final int INITIAL_POS = -1;

  private String name;
  private char number;
  private float intelligence;
  private float strength;
  private float health;
  private int row;
  private int col;
  private int consecutiveHits; // Al principio 0

  private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
  private ArrayList<Shield> shields = new ArrayList<Shield>();

  public Player(char number, float intelligence, float strength) {
    this.number = number;
    this.intelligence = intelligence;
    this.strength = strength;
    this.name = "Player #" + this.number;
    this.consecutiveHits = 0;
    this.health = Player.INITIAL_HEALTH; // Es mas correcto cuando se acceden a métodos estáticos
    // Preguntar si se inicializan
    this.row = this.INITIAL_POS;
    this.col = this.INITIAL_POS;
  }

  public void resurrect() {
    // Comprobamos que este muerto
    if (!dead()) {
      this.weapons.clear();
      this.weapons.clear();
      this.health = Player.INITIAL_HEALTH;
      this.consecutiveHits = 0;
    }
  }

  public void setPos(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public boolean dead() {
    return health == 0;
  }

  public float attack() {
    float sumWeapons = 0;

    for (Weapon w : weapons) {
      // Preguntar sobre el metodo getPower !!!!
      sumWeapons = w.getPower();
    }
    return sumWeapons + strength;
  }

  public boolean defend(float recivedAttack) {
    return true;
    // Delega su funcionalidad al metodo manageHit
  }

  public String toString() {
    System.out.println(this.name);
    System.out.println("Intelegence: " + this.intelligence);
    System.out.println("Strength: " + this.strength);
    System.out.println("health:" + this.health);
    System.out.println("Coordenadas: " + "[" + this.row + "]");
    System.out.println("Golpes consecutivos: " + this.consecutiveHits);
  }

}
