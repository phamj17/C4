package edu.up.cs301.ConnectFour;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * This class implements the easy AI for C4
 * Created by macnary17 on 11/21/2015.
 */
public class C4ComputerPlayerEasy extends GameComputerPlayer {

    //instance variables
    ConnectFourState newState = new ConnectFourState();
    //set up 2D array to hold board values
    public int[][] board = new int[6][7];

    /**
     * constructor for the C4ComputerPlayerEasy
     *
     * @param name
     */
    public C4ComputerPlayerEasy(String name) {
        super(name);
    }

    /**
     * Receives info
     * @param info - variable that holds the game info
     */
    protected void receiveInfo(GameInfo info) {
        //Check if opponent can get 4 in a row
        if (info instanceof ConnectFourState) {
            newState = (ConnectFourState) info;
            board = newState.getBoard();
            int playerIdx = newState.getTurn();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
        }

        //method variables
        int move;
        int stopHuman = -1;
        Random rand = new Random();


        if (stopVerticalWin() != -1) {
            stopHuman = stopVerticalWin();
        }
        else if (stopDiagonalWin()!=-1) {
            stopHuman = stopDiagonalWin();
        }
        else if (stopHorizontalWin() != -1) {
            stopHuman = stopHorizontalWin();
        }
        else if (stop2Horizontal() != -1) {
            stopHuman = stop2Horizontal();
        }

        //If opponent can get 4 in a row, block
        if (stopHuman != -1) {
            move = stopHuman;
        }
        //If opponent cant get 4 in a row, move randomly
        else {
            move = rand.nextInt(7);
        }

        if (move == 0) {
            C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
            game.sendAction(dropActionCol0);
        } else if (move == 1) {
            C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
            game.sendAction(dropActionCol1);
        } else if (move == 2) {
            C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
            game.sendAction(dropActionCol2);
        } else if (move == 3) {
            C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
            game.sendAction(dropActionCol3);
        } else if (move == 4) {
            C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
            game.sendAction(dropActionCol4);
        } else if (move == 5) {
            C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
            game.sendAction(dropActionCol5);
        } else if (move == 6) {
            C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
            game.sendAction(dropActionCol6);
        }
    }

    /**
     * Algorithm that stops a vertical win
     *
     * @return an int which tells the AI where to play its piece to block a vertical win
     */
    public int stopVerticalWin() {
        //method variables
        int count = 0;
        int piece = newState.getRED();
        int tempCol;

        //check the board
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
                //if we find a piece
                if (board[i][j] == piece) {
                    //can only get a vertical win if the row is >=3, so check that
                    if (i >= 3) {
                        //save the column
                        tempCol = j;
                        //for loop that checks if there is already 3 in a row
                        for (int a = 0; a < 3; a++) {
                            //if there's a same colored piece above the first piece
                            if (board[i - a][tempCol] == piece) {
                                //up the count
                                count++;
                                //if the count is >= 3
                                if (count >= 3) {
                                    //block the four in a row
                                    if (board[i - a - 1][tempCol] == newState.getEMPTY()) {
                                        return tempCol;
                                    }
                                }
                            }
                            //else reset the count
                            else {
                                count = 0;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Algorithm that stops a horizontal win
     *
     * @return an int which tells the AI where to play it's piece to block a horizontal win
     */
    public int stopHorizontalWin() {

        //method variables
        int count = 0;
        int piece = newState.getRED();

        int missingCol = -1;

        //check the board
        for (int i = board.length - 1; i >= 0; i--) {
            //limit the columns checked to prevent array exceptions
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                //increment four times for each row and column checked
                for (int a = 0; a < 4; a++) {
                    //if the slot is the piece, increment the count
                    if (board[i][j + a] == piece) {count++;}
                    //else if the slot is empty, set the move to that column
                    else if (board[i][j + a] == newState.getEMPTY()) {missingCol = j + a;}
                    //else if the slot is the other piece, set the move to -1
                    else {missingCol = -1;}
                }

                //if there are three pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the row checked is above the bottom row...
                    if (i < board.length - 1) {
                        //if the row can be moved to, return the move
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the row checked is the bottom row, return the move
                    else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;

    }

    /**
     * Algorithm that blocks two pieces horizontally to prevent a double trap
     *
     * @return an int which tells the AI where to play to block a two pieces horizontally
     */
    public int stop2Horizontal() {

        //method variables
        int count = 0;
        int piece = newState.getRED();
        int missingCol = -1;

        //checks the board
        for (int i = board.length - 1; i >= 0; i--) {
            //limits the columns checked to avoid array exceptions
            for (int j = 0; j <= board[i].length - 3; j++) {
                count = 0;
                missingCol = -1;
                //increments three times for each slot checked
                for (int a = 0; a < 3; a++) {
                    //if the slot is the piece, increment the count
                    if (board[i][j + a] == piece) {count++;}
                    //else if the slot is empty, set the move to that column
                    else if (board[i][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                    }
                    //else if the slot is the other piece, set the move to -1
                    else {
                        missingCol = -1;
                    }
                }
                //if there are two pieces and an empty space...
                if (count == 2 && missingCol != -1) {
                    //if the row checked is above the bottom row...
                    if (i < board.length - 1) {
                        //if the row checked can be moved to, return the move
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the row checked is the bottom row, return the move
                    else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Algorithm which blocks diagonal wins
     *
     * @return an int which tells the AI where to play to block a diagonal win
     */
    public int stopDiagonalWin() {

        //method variables
        int count = 0;
        int missingCol = -1;
        int tempRow = -1;
        int piece = newState.getRED();

        //checks through the board. limits rows and columns checked to avoid array exceptions
        //checks for top left to bottom right diagonals
        for (int i = 0; i <= board.length - 4; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                //for each slot checked, increment four times
                for (int a = 0; a < 4; a++) {
                    //if the slot checked is the piece, increment the count
                    if (board[i + a][j + a] == piece) {count++;}
                    //if the slot checked is empty, set the move to that column
                    else if(board[i + a][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                        tempRow = i + a;
                    }
                    //if the slot checked is the other piece, set the move to -1
                    else {
                        missingCol = -1;
                        tempRow = -1;
                    }
                }
                //if there are three pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the move's row is above the bottom row...
                    if (tempRow < board.length - 1) {
                        //if the move's row can be moved to, return the move
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the move's row is the bottom row, return the move
                    else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }

        //checks through the board. limits rows and columns checked to avoid array exceptions
        //checks for bottom left to top right diagonals
        for (int i = board.length - 1; i >= 3; i--) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                //for each slot checked, increment four times
                for (int a = 0; a < 4; a++) {
                    //if the slot checked is the piece, increment the count
                    if (board[i - a][j + a] == piece) {count++;}
                    //if the slot checked is empty, set the move to that column
                    else if(board[i - a][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                        tempRow = i - a;
                    }
                    //if the slot checked is the other piece, set the move to -1
                    else {
                        missingCol = -1;
                        tempRow = -1;
                    }
                }
                //if there are three pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the move's row is above the bottom row...
                    if (tempRow < board.length - 1) {
                        //if the move's row can be moved to, return the move
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the move's row is the bottom row, return the move
                    else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }
}