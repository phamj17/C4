package edu.up.cs301.ConnectFour;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import java.util.concurrent.TimeUnit;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * This class implements the game state for Connect Four
 * Created by phamj17 on 11/5/2015.
 */
public class ConnectFourState extends GameState {

    //instance variables
    private Piece lastPiecePlayed = null;
    int rowHelper = 0;
    int computerDifficulty;
    int columnHelper = 0;
    int draw = 0;

    int rowHelper1stTurn= 0;
    int columnHelper1stTurn = 0;
    //helper
    int countCinco = 0;

    private final int EMPTY = 0;
    //player 1 piece
    private final int RED = 1;
    //player 2/computer player piece
    private final int BLACK = 2;
    //Who is the winner
    private int winner = -1;
    //boolean that is true if game is over
    private boolean gameOver;
    //0 for player one, 1 for player two/computer player
    private int turn;

    private boolean hasReset = false;
    private boolean hasUndo = false;

    //number of pieces in each column
    private int[] column = new int[7];

    //the game board, contains 7 columns, 6 rows
    private int[][] board = new int[6][7];

    MediaPlayer player;

    /**
     * constructor for the game state which initializes the game board
     */
    public ConnectFourState() {
        //loop through the entire board
        for (int i = 0; i < board.length; i++) {
            column[i] = 0;
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
        //set the turn
        turn = 0;
        //the game is not over...
        gameOver = false;
    }

    /**
     * copy constructor for the game state
     * @param state the state to be copied
     */
    public ConnectFourState(ConnectFourState state) {
        board = state.getBoard();
        gameOver = state.getGameOver();
        turn = state.getTurn();
        column = state.getColumn();
    }

    /**
     * gets the piece value at the specified slot
     * @param row the row to be checked
     * @param col the column to be checked
     * @return the piece value at the specified slot
     */
    public int getPiece(int row, int col) {
        //check if row or col is a neg number
        if (board == null || row < 0 || col < 0) return -1;
        //check if row or col is outside the board
        if (row >= board.length || col >= board[row].length) return -1;
        return board[row][col];
    }

    /**
     * sets a piece at the specified column, if possible
     * @param col the column in which the piece will be set, if possible
     * @return returns true if the setting was successful, and false otherwise
     */
    public boolean setPiece(int col) {
        //temp variables
        if (hasUndo)
        {
            hasUndo = false;
        }
        rowHelper1stTurn = rowHelper;
        columnHelper1stTurn = columnHelper;
        int[][] tempBoard = getBoard();
        int[] tempCol = getColumn();
        //check if board exists, if piece is out of bounds
        if (tempBoard == null || col < 0 || col > 6) {
            countCinco = 1;
            return false;
        }

        //the column is already full, so return false
        if (tempCol[col] >= 6) {
            countCinco = 1;
            return false;
        }
        //else, do this stuff
        else {
            int tempRow = tempCol[col];
            //if it's player 1's turn, set the piece to red
            //change the turn
            if (turn == 0) {
                tempBoard[tempBoard.length - 1 - tempRow][col] = RED;
                rowHelper = tempBoard.length - 1 - tempRow;
                columnHelper = col;
                //turn = 1;
            }
            //else if it's player 2's turn, set the piece to black
            //change the turn
            else if (turn == 1) {
                tempBoard[tempBoard.length - 1 - tempRow][col] = BLACK;
                rowHelper = tempBoard.length - 1 - tempRow;
                columnHelper = col;
                //turn = 0;
            }

            //update our column array and the board
            tempCol[col]++;
            board = tempBoard;
            column = tempCol;

            return true;
        }
    }

    /**
     * checks the board for a horizontal four-in-a-row
     * @return true if there is a horizontal win, and false otherwise
     */
    public boolean horizontalWin() {

        int count = 0;
        int piece = EMPTY;
        if (turn == 0) {
            piece = RED;
        } else if (turn == 1) {
            piece = BLACK;
        }
        int tempRow;

        for (int i = board.length - 1; i >= 0; i--){
            for(int j = 0; j < board[i].length; j++){
                count = 0;
                if(board[i][j] == piece) {
                    if (j <= 3) {
                        tempRow = i;
                        for (int a = 0; a < 4; a++) {
                            if (board[tempRow][j + a] == piece) {
                                count++;
                                if (count >= 4) return true;
                            }
                            else {
                                count = 0;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks the board for a vertical four-in-a-row
     * @return true if there is a vertical win, and false otherwise
     */
    public boolean verticalWin() {

        int count = 0;
        int piece = EMPTY;
        if (turn == 0) {
            piece = RED;
        }
        else if (turn == 1) {
            piece = BLACK;
        }
        int tempCol = 0;

        for (int i = board.length - 1; i >= 0; i--){
            for(int j = 0; j < board[i].length; j++){
                count = 0;
                if(board[i][j] == piece) {
                    if (i >= 3) {
                        tempCol = j;
                        for (int a = 0; a < 4; a++) {
                            if (board[i - a][tempCol] == piece) {
                                count++;
                                if (count >= 4) return true;
                            }
                            else {
                                count = 0;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks the board for a diagonal four-in-a-row
     * @return true if there is a diagonal win, and false otherwise
     */
    public boolean diagonalWin() {
        boolean keepGoing = false;
        int count = 0;
        int piece = EMPTY;
        if (turn == 0) {
            piece = RED;
        } else if (turn == 1) {
            piece = BLACK;
        }

        //checks the board for a top left to bottom right diagonal wins
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == piece) {
                    count = 1;
                    int k = i;
                    int l = j;
                    keepGoing = true;
                    if (i < 3 && j < 4) {
                        while (keepGoing) {
                            k++;
                            l++;
                            if (board[k][l] == piece) {
                                count++;
                            } else {
                                keepGoing = false;
                                count = 0;
                            }
                            if (count == 4) {
                                return true;
                            }
                        }
                    }
                }
                count = 0;
            }
        }

        //checks the board for bottom left to top right diagonal wins
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == piece) {
                    count = 1;
                    int k = i;
                    int l = j;
                    keepGoing = true;
                    if (j > 2 && i < 3) {
                        while (keepGoing) {
                            k++;
                            l--;
                            if (board[k][l] == piece) {
                                count++;
                                if (count == 4) {
                                    return true;
                                }
                            } else {
                                keepGoing = false;
                                count = 0;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * checks is the game is over and changes the turn if it's not
     * @return true if the game is over, and false otherwise
     */
    public boolean gameOver() {
        int fullCols = 0;
        for (int a = 0; a < column.length; a++)
        {
            if (column[a] == 6)
            {
                fullCols++;
            }
        }
        if (verticalWin() || horizontalWin() || diagonalWin() || fullCols == 7) {
            if (fullCols == 7)
            {
                draw = 1;
            }
            return true;
        }
        else {
            if (!hasReset) {
                if (turn == 0) {
                    if (countCinco == 1) {
                        turn = 0;
                        countCinco = 0;
                    } else {
                        turn = 1;
                    }
                } else if (turn == 1) {
                    if (countCinco == 1) {
                        turn = 1;
                        countCinco = 0;
                    } else {
                        turn = 0;
                    }
                }
            }
            else
            {
                hasReset = false;
            }
            return false;
        }

    }

    //gets the board...
    public int[][] getBoard() {
        return board;
    }

    //gets gameOver boolean...
    public boolean getGameOver() {
        return gameOver;
    }

    //gets the turn...
    public int getTurn() {
        return turn;
    }

    //gets the column array
    public int[] getColumn() {
        return column;
    }

    //move actions for players
    public void dropActionCol0() { setPiece(0); }

    public void dropActionCol1() {setPiece(1);}

    public void dropActionCol2() {setPiece(2);}

    public void dropActionCol3() {setPiece(3);}

    public void dropActionCol4() {setPiece(4);}

    public void dropActionCol5() {setPiece(5);}

    public void dropActionCol6() {setPiece(6);}

    /**
     * reset action for players
     */
    public void resetAction()
    {
        int[][] tempBoard = getBoard();
        int[] tempCol = getColumn();

        //sets all slots to empty
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                tempCol[j] = 0;
                tempBoard[i][j] = EMPTY;
            }
        }
        hasReset = true;

        column = tempCol;
        board = tempBoard;
        if (gameOver = true)
        {
            gameOver = false;
        }

    }

    /**
     * undo action for players
     */
    public void undoAction()
    {

        if (!hasUndo) {
            int[][] tempBoard = getBoard();
            int[] tempCol = getColumn();


            tempBoard[rowHelper][columnHelper] = EMPTY;
            tempCol[columnHelper] = tempCol[columnHelper] - 1;

            tempBoard[rowHelper1stTurn][columnHelper1stTurn] = EMPTY;
            tempCol[columnHelper1stTurn] = tempCol[columnHelper1stTurn] - 1;

            turn = 0;
            hasReset = true;

            column = tempCol;
            board = tempBoard;
            hasUndo = true;
        }
        else
        {
            hasReset = true;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(15);
        } catch (InterruptedException e) {
        }

    }

    /**
     * sets the computer difficulty
     * @param difficulty an int that designates the desired difficulty
     */
    public void setComputerDifficulty(int difficulty)
    {
        computerDifficulty = difficulty;
    }

    //get methods that return piece types
    public int getEMPTY(){return EMPTY;}

    public int getRED(){return RED;}

    public int getBLACK(){return BLACK;}

    public int getDraw() {return draw;}
}