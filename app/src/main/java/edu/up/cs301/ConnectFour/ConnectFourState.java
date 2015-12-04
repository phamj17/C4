package edu.up.cs301.ConnectFour;

import android.graphics.Canvas;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by phamj17 on 11/5/2015.
 */
public class ConnectFourState extends GameState {
    private Piece lastPiecePlayed = null;
    int rowHelper = 0;
    int columnHelper = 0;

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

    //number of pieces for each column
    private int[] column = new int[7];

    //7 columns, 6 rows
    private int[][] board = new int[6][7];

    //constructor that initializes board
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

    //copy constructor
    public ConnectFourState(ConnectFourState state) {
        board = state.getBoard();
        gameOver = state.getGameOver();
        turn = state.getTurn();
        column = state.getColumn();
    }

    //get a piece...
    public int getPiece(int row, int col) {
        //check if row or col is a neg number
        if (board == null || row < 0 || col < 0) return -1;
        //check if row or col is outside the board
        if (row >= board.length || col >= board[row].length) return -1;
        return board[row][col];
    }

    //set a piece...
    public boolean setPiece(int col) {
        //temp variables

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

    //check for a horizontal win...
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
//                else {
//                    count = 0;
//                }
            }
        }
        return false;
    }

    //check for a vertical win...
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
//                else {
//                    count = 0;
//                }
            }
        }
        return false;
    }

    //check for diagonal win
    public boolean diagonalWin() {
        boolean keepGoing = false;
        int count = 0;
        int piece = EMPTY;
        if (turn == 0) {
            piece = RED;
        } else if (turn == 1) {
            piece = BLACK;
        }


        //look through entire game board, starting in top left corner
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


    //checks if the game is over...
    public boolean gameOver() {
        if (verticalWin() || horizontalWin() || diagonalWin()) {
            return true;
        }
        else {
            if (turn == 0)
            {
                if(countCinco == 1)
                {
                    turn = 0;
                    countCinco = 0;
                }
                else {
                    turn = 1;
                }
            }
            else if (turn == 1){
                if(countCinco == 1)
                {
                    turn = 1;
                    countCinco = 0;
                }
                else {
                    turn = 0;
                }
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

    public void dropActionCol0() { setPiece(0); }

    public void dropActionCol1()
    {
        setPiece(1);
    }

    public void dropActionCol2()
    {
        setPiece(2);
    }

    public void dropActionCol3()
    {
        setPiece(3);
    }

    public void dropActionCol4()
    {
        setPiece(4);
    }

    public void dropActionCol5()
    {
        setPiece(5);
    }

    public void dropActionCol6()
    {
        setPiece(6);
    }

    public void resetAction()
    {
        int[][] tempBoard = getBoard();
        int[] tempCol = getColumn();

        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                tempCol[j] = 0;
                tempBoard[i][j] = EMPTY;
            }
        }
        column = tempCol;
        board = tempBoard;

    }

    public void undoAction()
    {
        int[][] tempBoard = getBoard();
        int[] tempCol = getColumn();


        tempBoard[rowHelper][columnHelper] = EMPTY;
        tempCol[columnHelper] = tempCol[columnHelper] - 1;

        tempBoard[rowHelper1stTurn][columnHelper1stTurn] = EMPTY;
        tempCol[columnHelper1stTurn] = tempCol[columnHelper1stTurn] - 1;

//        if(turn == 1){
//            System.out.println("TURN WAS 1, NOW IT IS 0");
//            turn = 0;
//        }
//        else if(turn == 0){
//            System.out.println("TURN WAS 0, NOW it IS 1");
//            turn = 1;
//        }

        turn = 0;

        column = tempCol;
        board = tempBoard;
    }

    public int getEMPTY(){return EMPTY;}

    public int getRED(){return RED;}

    public int getBLACK(){return BLACK;}
}