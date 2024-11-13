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
        this.players = new ArrayList<Player>();
        for(int i=0; i<=nPlayers; i++) {
            this.players.add(new Player( Character.forDigit(i, 10), Dice.randomIntelligence(), Dice.randomStrength()));
        }
        
        // Creamos los monstruos
        this.monsters = new ArrayList<Monster>();
        for(int i=0; i<=3; i++) {
            this.monsters.add(new Monster("Monstruo " + i, Dice.randomIntelligence(), Dice.randomIntelligence()));
        }
          
        // Empezara un jugador aleatorio
        this.currentPlayerIndex = Dice.whoStarts(nPlayers);
        this.currentPlayer = this.players.get(currentPlayerIndex);
        
        // Creamos el laberinto
        this.labyrinth = new Labyrinth(10, 10, 8, 8);
                
        // Aqui se usa el metodo para configurar el laberinto...
        configureLabyrinth();
        
        // Ponemos a los jugadores
        this.labyrinth.spreadPlayers(this.players);
                
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
    
   public boolean nextStep (Directions preferredDirection){
        
        // Reseteamos el log
        log = "";

        if (currentPlayer.dead()){
            // Si el jugador está muerto, se ve si se resucita o no
            manageResurrection();
        }
        else{

            // Se obtiene la dirección real a la que se mueve el jugador.
            // Si no se puede mover, se indica en el log
            Directions direction = this.actualDirection(preferredDirection);
            if (direction != preferredDirection){
                logPlayerNoOrders();
            }

            // Se mueve al jugador y se obtiene el monstruo que haya en la casilla
            Monster monster = this.labyrinth.putPlayer(direction, currentPlayer);


            // Se estudia si hay un monstruo en la casilla a la que se ha movido el jugador
            if (monster == null){
                logNoMonster();
            }
            else{
                // Se lleva a cabo el combate
                GameCharacter winner = combat(monster);
                // Se gestiona la recompensa
                manageReward(winner);
            }// if (monster == null)
            
            
        }// if (currentPlayer.dead())


        boolean endGame = finished();
        if (!endGame)   // Si no ha finalizado el juego, se pasa al siguiente
            nextPlayer();

        return endGame;
    }
    
    


   public void configureLabyrinth() {
        int rows = 10;
        int cols = 10;

         //Configurar bordes como obstáculos, solo en las filas y columnas extremas
        for (int row = 0; row < rows; row++) {
            labyrinth.addBlock(Orientation.HORIZONTAL, row, 0, 1); // Borde izquierdo
            labyrinth.addBlock(Orientation.HORIZONTAL, row, cols - 1, 1); // Borde derecho
        }
        for (int col = 0; col < cols; col++) {
            labyrinth.addBlock(Orientation.VERTICAL, 0, col, 1); // Borde superior
            labyrinth.addBlock(Orientation.VERTICAL, rows - 1, col, 1); // Borde inferior
        }

         //Configurar obstáculos internos en posiciones clave y con variedad de longitudes
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 4); // Muro horizontal en el nivel superior
        labyrinth.addBlock(Orientation.VERTICAL, 4, 2, 3);   // Muro vertical para bloquear el camino
        labyrinth.addBlock(Orientation.HORIZONTAL, 5, 5, 3); // Muro horizontal en el centro
        labyrinth.addBlock(Orientation.VERTICAL, 6, 7, 2);   // Otro muro vertical para variar el laberinto

         //Colocar monstruos en puntos estratégicos
        labyrinth.addMonster(1, 1, this.monsters.get(0)); // Monstruo fácil
        labyrinth.addMonster(3, 5, this.monsters.get(1)); // Monstruo intermedio
        labyrinth.addMonster(6, 8, this.monsters.get(2)); // Monstruo difícil
        labyrinth.addMonster(8, 2, this.monsters.get(0)); // Otro monstruo fácil

         //Configurar áreas adicionales para la exploración
        labyrinth.addBlock(Orientation.HORIZONTAL, 3, 4, 2);  // Pared falsa horizontal
        labyrinth.addBlock(Orientation.VERTICAL, 7, 4, 1);    // Pared falsa vertical

         //Añadir pasadizos secretos o áreas de exploración extra
        labyrinth.addBlock(Orientation.HORIZONTAL, 8, 3, 2); // Un muro horizontal que crea una bifurcación
        labyrinth.addBlock(Orientation.VERTICAL, 1, 6, 3);   // Otro muro vertical en una posición inusual
    }

    
    private Directions actualDirection(Directions preferredDirection) {
        int currentRow = this.currentPlayer.getRow();
        int currentCol = this.currentPlayer.getCol();
        
        ArrayList<Directions> validMoves =  this.labyrinth.validMoves(currentRow, currentCol);
        
        return this.currentPlayer.move(preferredDirection, validMoves);
    }
    
    private GameCharacter combat(Monster monster) {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = this.currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while (!lose && (rounds < MAX_ROUNDS)) {
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = this.currentPlayer.defend(monsterAttack);
            
            if (!lose) {
                playerAttack = this.currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds , MAX_ROUNDS);
        
        return winner;
        
    }
    
    private void manageResurrection() {
        boolean resurrect = Dice.resurrectPlayer();
        
        if (resurrect) {
            currentPlayer.resurrect();
            logResurrected();
        }
        else {
            logPlayerSkipTurn();
        }
    }
    
    private void manageReward(GameCharacter winner) {
        if (winner == GameCharacter.PLAYER) {
            currentPlayer.reciveReward();
            logPlayerWon();
        }
        else {
            logMonsterWon();
        }
    }
    
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

