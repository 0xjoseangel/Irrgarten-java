package irrgarten;

/**
 * Clase de prueba para verificar el comportamiento de las clases en el paquete
 * irrgarten.
 * 
 * @author joseangel
 */
public class TestP1 {

  /**
   * Método principal para ejecutar las pruebas.
   *
   * @param args Los argumentos de línea de comandos.
   */
//  public static void main(String[] args) {
//    testEnums();
//    testWeaponClass();
//    testShieldClass();
//    testDiceClass();
//    testGameState();
//  }

  /**
   * Prueba de enums utilizados en el juego.
   */
  private static void testEnums() {
    System.out.println("Prueba enums");

    GameCharacter player = GameCharacter.PLAYER;
    System.out.println(player);

    Directions dir = Directions.DOWN;
    System.out.println(dir);

    Orientation or = Orientation.HORIZONTAL;
    System.out.println(or);

    System.out.println();
  }

  /**
   * Prueba de la clase Weapon.
   */
  private static void testWeaponClass() {
    System.out.println("Prueba clase Weapon:");
    Weapon weapon = new Weapon(2f, 5);
    System.out.println(weapon.toString());

    for (int i = 0; i < 6; i++) {
      System.out.println("Ataque: " + weapon.attack());
      System.out.println(weapon.toString());
      System.out.println("¿Descartar?: " + weapon.discard());
      System.out.println();
    }

    System.out.println();
  }

  /**
   * Prueba de la clase Shield.
   */
  private static void testShieldClass() {
    System.out.println("Prueba clase Shield:");
    Shield shield = new Shield(2f, 5);
    System.out.println(shield.toString());

    for (int i = 0; i < 6; i++) {
      System.out.println("Protección: " + shield.protect());
      System.out.println(shield.toString());
      System.out.println("¿Descartar?: " + shield.discard());
      System.out.println();
    }

    System.out.println();
  }

  /**
   * Prueba de la clase Dice.
   * Realiza múltiples invocaciones a métodos de la clase Dice y muestra los
   * resultados.
   */
  private static void testDiceClass() {
    System.out.println("Prueba clase Dice:");

    // Llamamos a cada método 100 veces
    for (int i = 0; i < 100; i++) {
      System.out.print("[ " + (i + 1) + "] RandomPos(10): ");
      System.out.println(Dice.randomPos(10));

      System.out.print("[ " + (i + 1) + "] whoStarts(10): ");
      System.out.println(Dice.whoStarts(10));

      System.out.print("[ " + (i + 1) + "] randomIntelligence: ");
      System.out.println(Dice.randomIntelligence());

      System.out.print("[ " + (i + 1) + "] randomStrength: ");
      System.out.println(Dice.randomStrength());

      System.out.print("[ " + (i + 1) + "] resurrectPlayer: ");
      System.out.println(Dice.resurrectPlayer());

      System.out.print("[ " + (i + 1) + "] weaponsReward: ");
      System.out.println(Dice.weaponsReward());

      System.out.print("[ " + (i + 1) + "] shieldsReward: ");
      System.out.println(Dice.shieldsReward());

      System.out.print("[ " + (i + 1) + "] healthReward: ");
      System.out.println(Dice.healthReward());

      System.out.print("[ " + (i + 1) + "] weaponPower: ");
      System.out.println(Dice.weaponPower());

      System.out.print("[ " + (i + 1) + "] shieldPower: ");
      System.out.println(Dice.shieldPower());

      System.out.print("[ " + (i + 1) + "] usesLeft: ");
      System.out.println(Dice.usesLeft());

      System.out.print("[ " + (i + 1) + "] intensity(0.5f): ");
      System.out.println(Dice.intensity(0.5f));

      System.out.print("[ " + (i + 1) + "] discardElement(3): ");
      System.out.println(Dice.discardElement(3));
    }

    System.out.println();
  }

  /**
   * Prueba de la clase GameState.
   * Verifica la creación y el estado de un GameState.
   */
  private static void testGameState() {
    System.out.println("Prueba clase GameState:");
    GameState gameState = new GameState("Maze1", "Player1, Player2", "Monster1", 0, false, "Log entry");

    System.out.println("Labyrinth: " + gameState.getLabyrinth());
    System.out.println("Players: " + gameState.getPlayers());
    System.out.println("Monsters: " + gameState.getMonsters());
    System.out.println("Current Player: " + gameState.getCurrentPlayer());
    System.out.println("Winner: " + gameState.getWinner());
    System.out.println("Log: " + gameState.getLog());
  }
}
