package irrgarten;

/**
 * Clase de prueba para verificar el comportamiento de las clases en el paquete
 * irrgarten.
 * 
 * @author joseangel
 */
public class TestP2 {

  /**
   * Método principal para ejecutar las pruebas.
   *
   * @param args Los argumentos de línea de comandos.
   */
  public static void main(String[] args) {
        // 1. Crear instancia del Laberinto
        Labyrinth labyrinth = new Labyrinth(5, 5, 3, 3); // Ejemplo con un laberinto de 5x5

        // 2. Añadir monstruo al laberinto
        Monster monster = new Monster("Orc", 10, 5); // Nombre, salud y fuerza
        labyrinth.addMonster(2, 2, monster); // Colocar el monstruo en la posición (2, 2)
        System.out.println("Labyrinth con monstruo:\n" + labyrinth.toString());
        
        // 3. Crear y colocar un jugador en el laberinto
        Player player = new Player((char) 1, Dice.randomIntelligence(), Dice.randomStrength()); // Crear el jugador con ID 1
        player.setPos(0, 0); // Colocar el jugador en la posición (0, 0)
        System.out.println("Estado del jugador:\n" + player.toString());

        // 4. Ejecutar acciones básicas del jugador y el monstruo
        player.attack();
        monster.gotWounded();
        System.out.println("Jugador ataca al monstruo:\n" + monster.toString());
       
    }
  
  public static void pruebaLabyrinth() {
      Labyrinth l = new Labyrinth(10,10,3,3);
      
      System.out.println(l.toString());
  }
}

