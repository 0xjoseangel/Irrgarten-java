package irrgarten;

import java.util.ArrayList;

/**
 * La clase Player representa a un jugador en el juego. Cada jugador tiene atributos
 * como inteligencia, fuerza, salud y una posición en el laberinto. Además, puede llevar
 * armas y escudos que le ayudan en combate.
 * 
 * @author joseangel
 */
public class Player {
  
  /** Número máximo de armas que puede llevar el jugador. */
  private static final int MAX_WEAPONS = 2;
  /** Número máximo de escudos que puede llevar el jugador. */
  private static final int MAX_SHIELDS = 3;
  /** Valor inicial de la salud del jugador. */
  private static final int INITIAL_HEALTH = 10;
  /** Número de golpes consecutivos para perder. */
  private static final int HITS2LOSE = 3;
  /** Posición inicial para las coordenadas del jugador (sin posición definida). */
  private final int INITIAL_POS = -1;

  /** Nombre del jugador. */
  private String name;
  /** Número identificador del jugador. */
  private char number;
  /** Nivel de inteligencia del jugador. */
  private float intelligence;
  /** Nivel de fuerza del jugador. */
  private float strength;
  /** Nivel de salud del jugador. */
  private float health;
  /** Fila en la que se encuentra el jugador en el laberinto. */
  private int row;
  /** Columna en la que se encuentra el jugador en el laberinto. */
  private int col;
  /** Contador de golpes consecutivos recibidos. */
  private int consecutiveHits;

  /** Lista de armas que lleva el jugador. */
  private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
  /** Lista de escudos que lleva el jugador. */
  private ArrayList<Shield> shields = new ArrayList<Shield>();

  /**
   * Constructor de la clase Player.
   * Inicializa un jugador con un número, inteligencia, fuerza, y su nombre asignado automáticamente.
   * 
   * @param number número del jugador
   * @param intelligence nivel de inteligencia del jugador
   * @param strength nivel de fuerza del jugador
   */
  public Player(char number, float intelligence, float strength) {
    this.number = number;
    this.intelligence = intelligence;
    this.strength = strength;
    this.name = "Player #" + this.number;
    this.consecutiveHits = 0;
    this.health = Player.INITIAL_HEALTH;
    this.row = this.INITIAL_POS;
    this.col = this.INITIAL_POS;
  }
  
  public int getCol() {
      return this.col;
  }
  
  public int getRow() {
      return this.row;
  }
  
  public char getNumber() {
      return this.number;
  }

  /**
   * Resucita al jugador si está muerto. Reinicia su salud, golpes consecutivos y vacía las listas de armas y escudos.
   */
  public void resurrect() {
    if (dead()) {
      this.weapons.clear();
      this.shields.clear();
      this.health = Player.INITIAL_HEALTH;
      this.consecutiveHits = 0;
    }
  }

  /**
   * Modifica la posición del jugador en el laberinto.
   * 
   * @param row fila a la que se mueve el jugador
   * @param col columna a la que se mueve el jugador
   */
  public void setPos(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Verifica si el jugador está muerto, es decir, si su salud ha llegado a cero.
   * 
   * @return true si el jugador está muerto, false en caso contrario
   */
  public boolean dead() {
    return health == 0;
  }

  /**
   * Realiza un ataque sumando la fuerza del jugador y el poder de sus armas.
   * 
   * @return el valor total del ataque
   */
  public float attack() {
    return this.sumWeapons() + this.strength;
  }

  /**
   * Defiende al jugador de un ataque recibido. Actualmente delega la funcionalidad en el método `manageHit`.
   * 
   * @param recivedAttack el valor del ataque recibido
   * @return true si el jugador defendió exitosamente, false en caso contrario
   */
  public boolean defend(float recivedAttack) {
    // Delega su funcionalidad al metodo manageHit
    return true;
  }

  /**
   * Devuelve una representación en cadena de caracteres del estado del jugador.
   * 
   * @return el estado del jugador como cadena de texto
   */
  public String toString() {
    return this.name + "\n" +
           "Inteligencia: " + this.intelligence + "\n" +
           "Fuerza: " + this.strength + "\n" + 
           "Salud: " + this.health + "\n" +
           "Coordenadas: " + "[" + this.row + ", " + this.col + "]\n" +
           "Golpes consecutivos: " + this.consecutiveHits + "\n";
  } 
  
  public Directions move (Directions direction, ArrayList<Directions> validMoves) {
      int size = validMoves.size();
      boolean contained = validMoves.contains(direction);
      
      Directions rdo;
      
      if (size > 0 && !contained) {
          rdo = validMoves.get(0);
      }
      else {
          rdo = direction;
      }
      
      return rdo;
  }
  
  public void reciveReward() {
      int wReward = Dice.weaponsReward();
      int sReward = Dice.shieldsReward();
      
      for (int i=0; i<wReward; i++) {
          Weapon wnew = newWeapon();
          reciveWeapon(wnew);
      }
      
        for (int i=0; i<wReward; i++) {
          Shield snew = newShield();
          reciveShield(snew);
      }
      int extraHealth = Dice.healthReward();
      
      this.health += extraHealth;
  }
  
  private void reciveWeapon(Weapon w) {
      // Comprobamos si hay algun elemento para eliminar
      for (Weapon wi : this.weapons) {
          boolean discard = wi.discard();
          
          if (discard) {
              this.weapons.remove(wi);
          }
      }
      int size = this.weapons.size();
      
      // Lo añadimos si cabe
      if (size < Player.MAX_WEAPONS) {
          this.weapons.add(w);
      }
  }
  
  private void reciveShield(Shield s) {
      // Comprobamos si hay algun elemento para eliminar
      for (Shield si : this.shields) {
          boolean discard = si.discard();
          
          if (discard) {
              this.shields.remove(si);
          }
      }
      int size = this.shields.size();
      
      // Lo añadimos si cabe
      if (size < Player.MAX_SHIELDS) {
          this.shields.add(s);
      }
  }
  
  private boolean manageHit(float recivedAttack) {
      boolean lose;
      float defense = defensiveEnergy();
      
      if (defense < recivedAttack) {
          gotWounded();
          incConsecutiveHits();
      }
      else {
          resetHits();
      }
      
      if (this.consecutiveHits == Player.HITS2LOSE || dead()) {
          resetHits();
          lose = true;
      }
      else {
          lose = false;
      }
      
      return lose;
  }
  /**
   * Genera una nueva arma utilizando valores aleatorios.
   * 
   * @return una nueva instancia de Weapon
   */
  private Weapon newWeapon() {
    return new Weapon(Dice.weaponPower(), Dice.usesLeft());  
  }
  
  /**
   * Genera un nuevo escudo utilizando valores aleatorios.
   * 
   * @return una nueva instancia de Shield
   */
  private Shield newShield() {
    return new Shield(Dice.shieldPower(), Dice.usesLeft());  
  }
  
  /**
   * Calcula la energía defensiva del jugador, sumando el poder de sus escudos y su inteligencia.
   * 
   * @return la energía defensiva total
   */
  private float defensiveEnergy() {
    return this.sumShields() + this.intelligence;
  }
  
  /**
   * Reinicia el contador de golpes consecutivos recibidos por el jugador.
   */
  private void resetHits() {
    this.consecutiveHits = 0;
  }
  
  /**
   * Disminuye en uno la salud del jugador cuando es herido.
   */
  private void gotWounded() {
    this.health--;
  }
  
  /**
   * Incrementa el número de golpes consecutivos recibidos por el jugador.
   */
  private void incConsecutiveHits() {
    this.consecutiveHits++;
  }
  
  /**
   * Suma el poder de ataque de todas las armas que lleva el jugador.
   * 
   * @return la suma del poder de ataque de las armas
   */
  private float sumWeapons() {
    float sumWeapons = 0;

    for (Weapon w : this.weapons) {
      sumWeapons += w.attack();
    }
    return sumWeapons;
  }
  
  /**
   * Suma el poder de defensa de todos los escudos que lleva el jugador.
   * 
   * @return la suma del poder de defensa de los escudos
   */
  private float sumShields() {
    float sumShields = 0;

    for (Shield s : this.shields) {
      sumShields += s.protect();
    }
    return sumShields;
  }
  
}
