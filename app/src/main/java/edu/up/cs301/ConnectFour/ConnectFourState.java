package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by phamj17 on 11/5/2015.
 */
public class ConnectFourState extends GameState {
    //what's up
    private final int EMPTY = 0;
    //player 1 piece
    private final int RED = 1;
    //player 2/computer player piece
    private final int BLACK = 2;
    //Who is the winner
    private int winner = -1;
    //boolean that is true if game is over
    private boolean gameOver;
    //0 for player 1, 1 for player 2/computer player
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
        int[][] tempBoard = getBoard();
        int[] tempCol = getColumn();
        //check if board exists, if piece is out of bounds
        if (tempBoard == null || col < 0 || col > 6) {
            return false;
        }

        //the column is already full, so return false
        if (tempCol[col] >= 6) {
            return false;
        }
        //else, do this stuff
        else {
            int tempRow = tempCol[col];
            //if it's player 1's turn, set the piece to red
            //change the turn
            if (turn == 0) {
                tempBoard[tempBoard.length - 1 - tempRow][col] = RED;
                //turn = 1;
            }
            //else if it's player 2's turn, set the piece to black
            //change the turn
            else if (turn == 1) {
                tempBoard[tempBoard.length - 1 - tempRow][col] = BLACK;
                //turn = 0;
            }

            //update our column array and the board
            tempCol[col]++;
            board = tempBoard;
            column = tempCol;

            return true;
        }
    }

    //check for a vertical win...
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
                //i = board.length-1, j = 1
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
                else {
                    count = 0;
                }
            }
        }

        //look through the entire board
//        for (int i = board.length - 1; i >= 0; i--) {
//            for (int j = 0; j < board[i].length; j++) {
//                //if we find a piece, up the count
//                if (board[i][j] == piece) {
//                    count++;
//                    //we found four in a row, you won!
//                    if (count >= 4) return true;
//                }
//                //else reset the count to zero
//                else {
//                    count = 0;
//                }
//            }
//        }

        return false;
    }

    //check for a horizontal win...
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
                else {
                    count = 0;
                }
            }
        }

//        for (int j = 0; j < board[0].length; j++) {
//            for (int i = board.length - 1; i >= 0; i--) {
//                if (board[i][j] == piece) {
//                    count++;
//                    if (count >= 4) return true;
//                }
//                else {
//                    count = 0;
//                }
//            }
//        }

//        //look through the entire board
//        for (int i = board.length - 1; i >= 0; i--) {
//            for (int j = 0; j < board[i].length; j++) {
//
//                if (board[i][j] == piece) {
//                    count++;
//                    //tempRow =
//                }
//
//                //if we are in the first column
//                if (i == 0) {
//                    //if we find a piece, get the row
//                    //up the count
//                    //set j to the max so we start to look at the next column
//                    if (board[i][j] == piece) {
//                        tempRow = j;
//                        count++;
//                        j = board[i].length;
//                    }
//                }
//                //if we aren't in the first column
//                else if (i > 0) {
//                    //if there's a piece in the tempRow
//                    //up the count
//                    if (board[i][tempRow] == piece) {
//                        count++;
//                        j = board[i].length;
//                        //we found four in a row, you won!
//                        if (count >= 4) return true;
//                    }
//                    //else reset the count to 0
//                    else {
//                        count = 0;
//                    }
//                }
//
//            }
//
//        }
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
        int tempRow = 0;
        int tempCol = 0;


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
                turn = 1;
            }
            else if (turn == 1){
                turn = 0;
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
}