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
    
    
    // Anadido por mi cuenta, no esta en el guion
    /** Índice que representa la fila de una posición 2D. */
    private static final int ROW = 0;
    /** Índice que representa la columna de una posición 2D. */
    private static final int COL = 1;
    
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
       
        for(Player player : this.players) {
            players += player.toString() + "\n";
        }
       
        for (Monster monster : this.monsters) {
            monsters += monster.toString() + "\n";
        }
        
        return new GameState(labyrinth.toString(), players, monsters, this.currentPlayerIndex, this.finished(), this.log);
    }
    
    
    // El diseno corresponde al siguiente laberinto
    // Hasta que no se implementen algunas funciones, carece de funcionalidad
//    X X X X X
//    X - - M X
//    X - X - X
//    X E - - X
//    X X X X X

//    public void configureLabyrinth() {
//        // Configurar bordes como obstáculos
//        for (int row = 0; row < 5; row++) {
//            labyrinth.addBlock(Orientation.HORIZONTAL, row, 0, 5); // Lado izquierdo y derecho
//            labyrinth.addBlock(Orientation.HORIZONTAL, row, 4, 5); // Lado derecho
//        }
//        for (int col = 0; col < 5; col++) {
//            labyrinth.addBlock(Orientation.VERTICAL, 0, col, 5); // Lado superior
//            labyrinth.addBlock(Orientation.VERTICAL, 4, col, 5); // Lado inferior
//        }
//
//        // Configurar obstáculos internos
//        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 1); // Muro en el centro
//
//        // Colocar salida en (3, 1)
//        labyrinth.setExitPosition(3, 1);
//
//        // Añadir un monstruo en (1, 3)
//        labyrinth.addMonster(1, 3, this.monsters.get(0));
//
//        // Imprimir la configuración final del laberinto
//        System.out.println(labyrinth.toString());
//    }
    

    private void nextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
    }
    
    private void logPlayerWon() {
        this.log += "Player#" + this.currentPlayerIndex + " ha ganado el combate" + "\n";
    }
    
    private void logMonsterWon() {
        this.log += "El monstruo ha ganado el combate" + "\n";
    }
    
    private void logResurrected() {
        this.log += "Player#" + this.currentPlayerIndex + " ha resucitado" + "\n";
    }
    
    private void logPlayerSkipTurn() {
        this.log += "Player#" + this.currentPlayerIndex + " ha perdido el turno por estar muerto" + "\n";
    }
    
    private void logPlayerNoOrders() {
        this.log += "Player#" + this.currentPlayerIndex + " no pudo efectuar esa accion" + "\n";
    }
    
    private void logNoMonster() {
        this.log += "Player#" + this.currentPlayerIndex + " en celda vacia o no le ha sido posible moverse" + "\n";
    }
    
    private void logRounds(int rounds, int max) {
        this.log += "Rondas: " + rounds + "/" + max + "\n";
    }
}

