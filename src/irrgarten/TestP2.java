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
    pruebaLabyrinth();
  }
  
  public static void pruebaLabyrinth() {
      Labyrinth l = new Labyrinth(10,10,3,3);
      
      System.out.println(l.toString());
  }
}

