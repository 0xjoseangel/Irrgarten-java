package irrgarten;

import java.util.ArrayList;

/**
 * La clase Labyrinth representa el laberinto del juego, donde interactúan jugadores y monstruos.
 * Utiliza una matriz 2D para representar el tablero y almacena información sobre los monstruos, jugadores y obstáculos.
 * El laberinto contiene posiciones de salida, espacios vacíos, obstáculos y áreas donde puede ocurrir un combate.
 * 
 * @author joseangel
 */
public class Labyrinth {
    
    /** Caracter que representa un bloque u obstáculo en el laberinto. */
    private static final char BLOCK_CHAR = 'X';
    /** Caracter que representa un espacio vacío en el laberinto. */
    private static final char EMPTY_CHAR = '-';
    /** Caracter que representa la posición de un monstruo en el laberinto. */
    private static final char MONSTER_CHAR = 'M';
    /** Caracter que representa una posición de combate (jugador y monstruo) en el laberinto. */
    private static final char COMBAT_CHAR = 'C';
    /** Caracter que representa la salida del laberinto. */
    private static final char EXIT_CHAR = 'E';
    
    /** Índice que representa la fila de una posición 2D. */
    private static final int ROW = 0;
    /** Índice que representa la columna de una posición 2D. */
    private static final int COL = 1;
    
    /** Número de filas del laberinto. */
    private int nRows;
    /** Número de columnas del laberinto. */
    private int nCols;
    /** Fila donde se encuentra la salida del laberinto. */
    private int exitRow;
    /** Columna donde se encuentra la salida del laberinto. */
    private int exitCol;
    
    /** Matriz 2D que almacena los monstruos en el laberinto. */
    private Monster[][] monsters;
    /** Matriz 2D que almacena los jugadores en el laberinto. */
    private Player[][] players;
    /** Matriz 2D que representa la estructura del laberinto. */
    private char[][] labyrinth;

    /**
     * Constructor que inicializa el laberinto con un número de filas y columnas, así como la posición de la salida.
     * Inicializa las matrices de jugadores, monstruos y el tablero del laberinto.
     * 
     * @param nRows número de filas del laberinto
     * @param nCols número de columnas del laberinto
     * @param exitRow fila donde se encuentra la salida del laberinto
     * @param exitCol columna donde se encuentra la salida del laberinto
     */
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];
        
        // Inicializa el laberinto con espacios vacíos
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                this.monsters[i][j] = null;
                this.players[i][j] = null;
                this.labyrinth[i][j] = Labyrinth.EMPTY_CHAR;
            }
        }
        // Establece la posición de la salida
        this.labyrinth[exitRow][exitCol] = Labyrinth.EXIT_CHAR;
    }

    /**
     * Verifica si hay un ganador en el juego, es decir, si algún jugador ha llegado a la casilla de salida.
     * 
     * @return true si un jugador ha alcanzado la salida, false en caso contrario
     */
    public boolean haveAWinner() {
        return this.players[this.exitRow][this.exitCol] != null;
    }

    /**
     * Devuelve una representación en cadena de caracteres del estado actual del laberinto.
     * 
     * @return el estado del laberinto como una cadena de texto
     */
    public String toString() {
        String rdo = "Laberinto: \n";
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                rdo += this.labyrinth[i][j] + " ";
            }
            rdo += "\n";
        }
        return rdo;
    }

    /**
     * Añade un monstruo al laberinto en la posición indicada si está vacía y es válida.
     * 
     * @param row fila donde se añadirá el monstruo
     * @param col columna donde se añadirá el monstruo
     * @param monster instancia de Monstruo que se añadirá
     */
    public void addMonster(int row, int col, Monster monster) {
        if (posOK(row, col) && emptyPos(row, col)) {
            monster.setPos(row, col);
            this.monsters[row][col] = monster;
            this.labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
        }
    }
    
    /**
     * Añade un bloque de obstáculos en el laberinto en una orientación dada.
     * 
     * @param orientation orientación del bloque (VERTICAL u HORIZONTAL)
     * @param startRow fila inicial del bloque
     * @param startCol columna inicial del bloque
     * @param length longitud del bloque
     */
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow = 0, incCol = 0;
        int row = startRow;
        int col = startCol;
        
        if (orientation == Orientation.VERTICAL) {
            incRow = 1;
            incCol = 0;
        }
        else {
            incRow = 0;
            incRow = 1;
        }
        
        while (posOK(row, col) && emptyPos(row, col) && length > 0) {
            this.labyrinth[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            col += incCol;
        }
        
        
    }
    
     /**
     * Mueve el jugador en una dirección dada y devuelve el monstruo encontrado en la nueva posición si es un área de combate.
     * 
     * @param direction dirección en la que se mueve el jugador
     * @param player jugador que se va a mover
     * @return el monstruo en la posición nueva si hay combate, null si no lo hay
     */
    public Monster putPlayer(Directions direction, Player player) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    /**
     * Distribuye un arreglo de jugadores en posiciones aleatorias vacías dentro del laberinto.
     * 
     * @param players arreglo de jugadores que se van a distribuir en el laberinto
     */
    public void spreadPlayers(ArrayList<Player> players) {
        int[] pos;
        
        for (Player p : players) {
            pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
     /**
     * Devuelve una lista de movimientos válidos desde una posición dada.
     * 
     * @param row fila de la posición actual
     * @param col columna de la posición actual
     * @return lista de direcciones en las que el jugador puede moverse
     */
    public ArrayList<Directions> validMoves(int row, int col){
        
        ArrayList<Directions> output = new ArrayList<>();

        if (canStepOn(row+1, col))
            output.add(Directions.DOWN);
        if (canStepOn(row-1, col))
            output.add(Directions.UP);
        if (canStepOn(row, col+1))
            output.add(Directions.RIGHT);
        if (canStepOn(row, col-1))
            output.add(Directions.LEFT);

        return output;
    }
    
    public void convertToFuzzy(FuzzyPlayer other){
        int row=other.getRow();
        int col=other.getCol();
        if(this.players[row][col].getNumber() == other.getNumber())
            this.players[row][col]=other;
    }
    
    /**
     * Verifica si una posición está dentro de los límites del laberinto.
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición está dentro de los límites, false en caso contrario
     */
    private boolean posOK(int row, int col) {
        return (row >= 0 && row < this.nRows) && (col >= 0 && col < this.nCols);
    }

    /**
     * Verifica si una posición está vacía.
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición está vacía, false en caso contrario
     */
    private boolean emptyPos(int row, int col) {
        return this.labyrinth[row][col] == Labyrinth.EMPTY_CHAR;
    }

    /**
     * Verifica si una posición contiene un monstruo.
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición contiene un monstruo, false en caso contrario
     */
    private boolean monsterPos(int row, int col) {
        return this.labyrinth[row][col] == Labyrinth.MONSTER_CHAR;
    }

    /**
     * Verifica si una posición es la de salida.
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición es la salida, false en caso contrario
     */
    private boolean exitPos(int row, int col) {
        return this.labyrinth[row][col] == Labyrinth.EXIT_CHAR;
    }

    /**
     * Verifica si una posición contiene tanto un monstruo como un jugador (zona de combate).
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición es de combate, false en caso contrario
     */
    private boolean combatPos(int row, int col) {
        return this.labyrinth[row][col] == Labyrinth.COMBAT_CHAR;
    }

    /**
     * Verifica si se puede pisar una determinada posición en el laberinto.
     * 
     * @param row fila a verificar
     * @param col columna a verificar
     * @return true si la posición es válida para moverse, false en caso contrario
     */
    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col));
    }

    /**
     * Actualiza la posición antigua de un jugador o monstruo en el laberinto, 
     * eliminando su presencia o indicando si había combate.
     * 
     * @param row fila de la posición antigua
     * @param col columna de la posición antigua
     */
    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (combatPos(row, col)) {
                this.labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
            } else {
                this.labyrinth[row][col] = Labyrinth.EMPTY_CHAR;
            }
        }
    }

    /**
     * Calcula la nueva posición en el laberinto dada una dirección y una posición actual.
     * 
     * @param row fila actual
     * @param col columna actual
     * @param direction dirección en la que se desea mover
     * @return un arreglo de enteros con la nueva posición [fila, columna]
     */
    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] newPos = {row, col};
        
        switch (direction) {
            case LEFT -> newPos[COL]--;
            case RIGHT -> newPos[COL]++;
            case UP -> newPos[ROW]--;
            case DOWN -> newPos[ROW]++;
        }
        
        return newPos;
    }

    /**
     * Genera una posición aleatoria vacía en el laberinto.
     * Utiliza un dado para generar las posiciones hasta encontrar una vacía.
     * 
     * @return un arreglo de enteros con la posición vacía aleatoria [fila, columna]
     */
    private int[] randomEmptyPos() {
        int[] randomPos = new int[2];
        
        do {
            randomPos[ROW] = Dice.randomPos(this.nRows);
            randomPos[COL] = Dice.randomPos(this.nCols);
        } while (!emptyPos(randomPos[ROW], randomPos[COL]));
        
        return randomPos;
    }
    
    
    /**
     * Coloca un jugador en una posición nueva y devuelve el monstruo encontrado si es un área de combate.
     * 
     * @param oldRow fila de la posición anterior del jugador
     * @param oldCol columna de la posición anterior del jugador
     * @param row fila de la nueva posición del jugador
     * @param col columna de la nueva posición del jugador
     * @param player jugador que se va a colocar
     * @return el monstruo encontrado en la nueva posición si hay combate, null si no lo hay
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output = null;
        Player p;
        
        if (canStepOn(row, col)) {
            if (posOK(oldRow, oldCol)) {
                p = players[oldRow][oldCol];
                if (p == player) {
                    updateOldPos(oldRow, oldCol);
                    this.players[oldRow][oldCol] = null;
                }
                
            }
            if (monsterPos(row, col)) {
                this.labyrinth[row][col] = COMBAT_CHAR;
                output = this.monsters[row][col];
            } 
            else {
                this.labyrinth[row][col] = player.getNumber();
            }
            
            this.players[row][col] = player;
            player.setPos(row, col);
            
        }
        
        return output;
    }
    
}

  


 
