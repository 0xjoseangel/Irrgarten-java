/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
        
import irrgarten.Controller.Controller;
import irrgarten.UI.TextUI;

/**
 *
 * @author joseangel
 */
public class TestP3 {
      public static void main ( String [ ] args ) {
        
        final int N_PLAYERS = 3;

        TextUI vista = new TextUI();
        Game juego = new Game(N_PLAYERS);
        Controller controlador = new Controller(juego, vista);

        controlador.play();


    }
}
