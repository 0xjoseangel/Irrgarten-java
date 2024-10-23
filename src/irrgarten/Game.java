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
public class Game {
    private final int MAX_ROUNDS = 10;
    
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private Labyrinth  labyrinth;
    
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    
    public Game(int nPlayers) {
        // Creamos los jugadores
        for(int i=0; i<=nPlayers; i++) {
            this.players.add(new Player( (char) i, Dice.randomIntelligence(), Dice.randomStrength()));
        }
        
        // Creamos los monstruos
        for(int i=0; i<=3; i++) {
            this.monsters.add(new Monster("Monstruo " + i, Dice.randomIntelligence(), Dice.randomIntelligence()));
        }
          
        // Empezara un jugador aleatorio
        this.currentPlayerIndex = Dice.whoStarts(nPlayers);
        this.currentPlayer = this.players.get(currentPlayerIndex);
        
        // Creamos el laberinto
        this.labyrinth = new Labyrinth(5, 5, 3, 3);
        
        // Aqui se usa el metodo para configurar el laberinto...
        
        this.log = "";
    }
    
    public boolean finished() {
        return this.labyrinth.haveAWinner();
    }
    
    public GameState getGameState() {
        String players= "";
        String monsters = "";
       s
        for(Player player : this.players) {
            players += player.toString() + "\n";
        }
       
        for (Monster monster : this.monsters) {
            monsters += monster.toString() + "\n";
        }
        
        return new GameState(labyrinth.toString(), players, monsters, this.currentPlayerIndex, this.finished(), this.log);
    }
    
    
}
