/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author joseangel
 */
public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] labyrinth;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];
        for (int i = 0; i < nRows; i++){
          for (int j = 0; j < nCols; j++) {
            this.monsters[i][j] = null;
            this.players[i][j] = null;
            this.labyrinth[i][j] = this.EMPTY_CHAR;
          }
        }
        this.labyrinth[exitRow][exitCol] = this.EXIT_CHAR;
    }
    
    public boolean haveAWinner() {
        return this.players[this.exitRow][this.exitCol] != null;
    }
    
    public String toString() {
        String rdo = "Laberito: " + "\n";
        for (int i = 0; i < nRows; i++){
          for (int j = 0; j < nCols; j++) {
              rdo += this.labyrinth[i][j] + " ";
          }
          rdo += "\n";
        }
        return rdo;
    }
}
