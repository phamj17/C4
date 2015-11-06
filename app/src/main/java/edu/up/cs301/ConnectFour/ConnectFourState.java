package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by phamj17 on 11/5/2015.
 */
public class ConnectFourState extends GameState {

    private final int EMPTY = 0;
    //player 1 piece
    private final int RED = 1;
    //player 2/computer player piece
    private final int BLACK = 2;


    private boolean gameOver;
    //0 for player 1, 1 for player 2/computer player
    private int turn;

    //number of pieces for each column
    private int[] column = new int[7];

    //7 columns, 6 rows
    private int[][] board = new int[7][6];


    public ConnectFourState() {
        for (int i = 0; i < board.length; i++) {
            column[i] = 0;
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }

        turn = 0;
        gameOver = false;

    }

    public ConnectFourState(ConnectFourState state) {
        board = state.getBoard();
        gameOver= state.getGameOver();
        turn = state.getTurn();
        column = state.getColumn();


    }

    public int getPiece(int row, int col){
        //check if row or col is a neg number
        if(board == null || row < 0 || col < 0) return -1;
        //check if row or col is outside the board
        if(row >= board.length || col >= board[row].length) return -1;
        return board[row][col];
    }

    public void setPiece(int row, int col, int turn){
        //check if row or col is a neg number
        if(board == null || row < 0 || col < 0) return;
        //check if row or col is outside the board
        if(row >= board.length || col >= board[row].length) return;
        //if it's player 1's turn, set the piece to a red piece
        //change turn to other player
        if(turn == 0){
            board[row][col] = RED;
            this.turn = 1;
        }
        //if it's player 2's turn, set the piece to a black piece
        //change turn to other player
        if(turn == 1){
            board[row][col] = BLACK;
            this.turn = 0;
        }
    }

    public int[][] getBoard() {return board;}
    public boolean getGameOver() {return gameOver;}
    public int getTurn() {return turn;}
    public int[] getColumn() {return column;}
}
