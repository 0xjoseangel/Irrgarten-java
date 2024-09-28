/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Esta clase permitirá, de forma muy sencilla, almacenar una representación del estado completo del
 * juego: el estado del laberinto, el estado de los jugadores, el estado de los monstruos, el índice del
 * jugador que tiene el turno, un indicador sobre si ya hay un ganador y un atributo adicional para
 * guardar en una cadena de caracteres eventos interesantes que hayan ocurrido desde el turno anterior.
 * @author joseangel
 */
public class GameState {
  private String labyrinth;      // Laberinto del juego
    private String players;        // Jugadores en el juego
    private String monsters;       // Monstruos en el juego
    private int currentPlayer;     // Índice del jugador actual
    private boolean winner;        // Indica si hay un ganador
    private String log;            // Registro del juego

    /**
     * Constructor para inicializar el estado del juego.
     *
     * @param labyrinth El laberinto del juego.
     * @param players Los jugadores en el juego.
     * @param monsters Los monstruos en el juego.
     * @param currentPlayer El índice del jugador actual.
     * @param winner Indica si hay un ganador.
     * @param log Registro del juego.
     */
    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;       
        this.players = players;           
        this.monsters = monsters;         
        this.currentPlayer = currentPlayer; 
        this.winner = winner;             
        this.log = log;                   
    }
    
    // Métodos getter

    /**
     * Obtiene el laberinto del juego.
     *
     * @return El laberinto como una cadena.
     */
    public String getLabyrinth() {
        return labyrinth;
    }

    /**
     * Obtiene los jugadores en el juego.
     *
     * @return Los jugadores como una cadena.
     */
    public String getPlayers() {
        return players;
    }

    /**
     * Obtiene los monstruos en el juego.
     *
     * @return Los monstruos como una cadena.
     */
    public String getMonsters() {
        return monsters;
    }

    /**
     * Obtiene el índice del jugador actual.
     *
     * @return El índice del jugador actual como un entero.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Obtiene el estado del ganador.
     *
     * @return true si hay un ganador, false en caso contrario.
     */
    public boolean getWinner() {
        return winner;
    }

    /**
     * Obtiene el registro del juego.
     *
     * @return El registro del juego como una cadena.
     */
    public String getLog() {
        return log;
    }
    
}

