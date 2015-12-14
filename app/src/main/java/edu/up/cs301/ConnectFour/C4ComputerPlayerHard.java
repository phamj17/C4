package edu.up.cs301.ConnectFour;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * This class implements the hard AI for C4
 * Created by macnary17 on 12/1/2015.
 */
public class C4ComputerPlayerHard extends GameComputerPlayer {

    public int schmidty = 1;

    //instance variables
    ConnectFourState newState = new ConnectFourState();
    //set up 2D array to hold board values
    public int[][] board = new int[6][7];

    /**
     * constructor for the C4ComputerPlayerHard
     *
     * @param name
     */
    public C4ComputerPlayerHard(String name) {
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
        } catch (InterruptedException e) {
        }

        //method variables
        int move=0;
        int stopHuman = -1;
        int winner = -1;
        int winner2H = -1;
        Random rand = new Random();
        int[] col = newState.getColumn();
        int midCol = col[3];

        if (winV() != -1) {
            winner = winV();
        } else if (winD() != -1) {
            winner = winD();
        } else if (winH() != -1) {
            winner = winH();
        } else if (win2H() != -1) {
            winner2H = win2H();
        }

        if (stopVerticalWin() != -1) {
            stopHuman = stopVerticalWin();
        } else if (stopDiagonalWin() != -1) {
            stopHuman = stopDiagonalWin();
        } else if (stopHorizontalWin() != -1) {
            stopHuman = stopHorizontalWin();
        } else if (stop2Horizontal() != -1) {
            stopHuman = stop2Horizontal();
        }

        //if AI can win, move there
        if (winner != -1) {
            move = winner;
        }
        //else if human can win, block
        else if (stopHuman != -1) {
            move = stopHuman;
        }
        //else if human has two pieces in a horizontal three slot, block
        else if (winner2H!= -1) {
            move = winner2H;
        }
        //else if AI can move in the middle column, move there
        else if (midCol < 6) {
            move = 3;
        }
        //else, move randomly
        else {
            move = rand.nextInt(7);
        }
        int counterz = 0;
        while(dontGo(move) || counterz == 10)
        {
            counterz++;
            move = rand.nextInt(7);
        }
        //move actions for the computer player
        if (winner != -1) {
            move = winner;
        }

        if (move == 0) {
//            if (!dontGo(0)) {
                C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
                game.sendAction(dropActionCol0);
//            } else {
//                C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
//                game.sendAction(dropActionCol1);
//            }
        } else if (move == 1) {
//            if (!dontGo(1)) {
                C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
                game.sendAction(dropActionCol1);
//            }
//            else {
//                C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
//                game.sendAction(dropActionCol2);
//            }
        } else if (move == 2) {
//            if (!dontGo(2)) {
                C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
                game.sendAction(dropActionCol2);
//            }
//            else
//            {
//                C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
//                game.sendAction(dropActionCol3);
//            }
        } else if (move == 3) {
//            if (!dontGo(3)) {
                C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
                game.sendAction(dropActionCol3);
//            }
//            else
//            {
//                C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
//                game.sendAction(dropActionCol4);
//            }
        } else if (move == 4) {
//            if (!dontGo(4)) {
                C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
                game.sendAction(dropActionCol4);
//            }
//            else {
//                C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
//                game.sendAction(dropActionCol5);
//            }
        } else if (move == 5) {
//            if (!dontGo(5)) {
                C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
                game.sendAction(dropActionCol5);
//            }
//            else
//            {
//                C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
//                game.sendAction(dropActionCol6);
//            }
        } else if (move == 6) {
//            if (!dontGo(6)) {
                C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
                game.sendAction(dropActionCol6);
//            }
//            else
//            {
//                C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
//                game.sendAction(dropActionCol0);
//            }
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
                            //if there's a same colored piece above the first piece, increment count
                            if (board[i - a][tempCol] == piece) {
                                count++;
                                //if the other player can win, block
                                if (count >= 3) {
                                    if (board[i - a - 1][tempCol] == newState.getEMPTY()) {
                                        return tempCol;
                                    }
                                }
                            }
                            //else, reset the count
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
                    if (board[i][j + a] == piece) {
                        count++;
                    }
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
                    else if (board[i + a][j + a] == newState.getEMPTY()) {
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
                    if (board[i - a][j + a] == piece) {
                        count++;
                    }
                    //if the slot checked is empty, set the move to that column
                    else if (board[i - a][j + a] == newState.getEMPTY()) {
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

    /**
     * Algorithm that checks if the AI can win vertically
     * @return an integer that tells the AI where to win vertically
     */
    public int winV() {
        //method variables
        int count = 0;
        int piece = newState.getBLACK();
        int tempCol;

        //checks the board; limits the rows checked to avoid array exceptions
        for (int i = board.length - 1; i >= 3; i--) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
                //if the slot checked is a piece, increment through three times
                //if there are three of the AI's pieces in a row, return that column
                if (board[i][j] == piece) {
                    tempCol = j;
                    for (int a = 0; a < 3; a++) {
                        if (board[i - a][tempCol] == piece) {
                            count++;
                            if (count >= 3) {
                                if (board[i - a - 1][tempCol] == newState.getEMPTY()) {
                                    return tempCol;
                                }
                            }
                        } else {
                            count = 0;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Algorithm that checks if the AI can win horizontally
     * @return an int that tells the AI where to win horizontally
     */
    public int winH() {

        //method variables
        int count = 0;
        int piece = newState.getBLACK();
        int missingCol = -1;

        //checks through the board; limits the columns checked to avoid array exceptions
        //count represents the number of AI pieces there are
        //missingCol represents the column location of an empty piece
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                for (int a = 0; a < 4; a++) {
                    if (board[i][j + a] == piece) {
                        count++;
                    } else if (board[i][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                    } else {
                        missingCol = -1;
                    }
                }

                //if there are three AI pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the row checked is above the bottom and can be moved to, return the move
                    if (i < board.length - 1) {
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
     * Algorithm that checks if the AI can win diagonally
     * @return an int which tells the AI where it can move to win diagonally
     */
    public int winD() {

        //method variables
        //count represents the number of AI pieces
        //missingCol represents the column location of the empty space
        int count = 0;
        int missingCol = -1;
        int tempRow = -1;
        int piece = newState.getBLACK();

        //checks the board for top left to bottom right diagonal wins
        //limits the rows and columns checked to avoid array exceptions
        for (int i = 0; i <= board.length - 4; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                for (int a = 0; a < 4; a++) {
                    if (board[i + a][j + a] == piece) {
                        count++;
                    } else if (board[i + a][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                        tempRow = i + a;
                    } else {
                        missingCol = -1;
                        tempRow = -1;
                    }
                }

                //if there are three AI pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the row checked is above the bottom and can be moved to, return the move
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the row checked is the bottom row, return the move
                    else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }

        //checks the board for bottom right to top left diagonal wins
        for (int i = board.length - 1; i >= 3; i--) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                for (int a = 0; a < 4; a++) {
                    if (board[i - a][j + a] == piece) {
                        count++;
                    } else if (board[i - a][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                        tempRow = i - a;
                    } else {
                        missingCol = -1;
                        tempRow = -1;
                    }
                }
                //if there are three AI pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the row checked is above the bottom and can be moved to, return the move
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the row checked is the bottom row, return the move
                    else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Algorithm that checks if the AI can double trap horizontally
     * @return an integer that tells the AI where to move to double trap horizontally
     */
    public int win2H() {

        //method variables
        //count represents the number of AI pieces
        //missingCol represents the column location of the empty space
        int count = 0;
        int piece = newState.getBLACK();
        int missingCol = -1;

        //checks the board; limits the columns checked to avoid array exceptions
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j <= board[i].length - 3; j++) {
                count = 0;
                missingCol = -1;
                for (int a = 0; a < 3; a++) {
                    if (board[i][j + a] == piece) {
                        count++;
                    } else if (board[i][j + a] == newState.getEMPTY()) {
                        missingCol = j + a;
                    } else {
                        missingCol = -1;
                    }
                }

                //if there are two AI pieces and an empty space...
                if (count == 2 && missingCol != -1) {
                    //if the move's row is above the bottom row and can be moved to, return the move
                    if (i < board.length - 1) {
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    }
                    //if the move's row is the bottom row, return the move
                    else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }

    public boolean dontGo(int inThisCol)
    {
        //method variables
        int count = 0;
        int piece = newState.getRED();
        int missingCol = -1;
        int dontGoHere = -1;

        //check the board
        for (int i = board.length - 2; i >= 0; i--) {
            //limit the columns checked to prevent array exceptions
            for (int j = 0; j <= board[i].length - 4; j++) {
                count = 0;
                missingCol = -1;
                //increment four times for each row and column checked
                for (int a = 0; a < 4; a++) {
                    //if the slot is the piece, increment the count
                    if (board[i][j + a] == piece) {
                        count++;
                    }
                    //else if the slot is empty, set the move to that column
                    else if (board[i + 1][j + a] == newState.getEMPTY()) {
                        if (j + a == inThisCol) {
                            missingCol = j + a;
                        }
                    }
                    //else if the slot is the other piece, set the move to -1
                    else {
                        missingCol = -1;
                    }
                }

                //if there are three pieces and an empty space...
                if (count == 3 && missingCol != -1) {
                    //if the row checked is above the bottom row...
                    if (i < board.length - 2) {
                        //if the row can be moved to, return the move
                        if (board[i + 2][missingCol] != newState.getEMPTY()) {
                            return true;
                        }
                    } else if (i == 4) {
                        return true;
                    }

                }
            }
        }


        //method variables
         count = 0;
         missingCol = -1;
         int tempRow = -1;
         piece = newState.getRED();

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
                    else if (board[i + a ][j + a] == newState.getEMPTY()) {
                        if (j + a == inThisCol) {
                            missingCol = j + a;
                            tempRow = i + a;
                        }
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
                    if (tempRow < board.length - 2) {
                        //if the move's row can be moved to, return the move
                        if (board[tempRow + 2][missingCol] != newState.getEMPTY()) {
                            if (board[tempRow + 1][missingCol] == newState.getEMPTY()) {
                                return true;
                            }
                        }
                    }
                    //if the move's row is the bottom row, return the move
                    else if (tempRow == 4) {
                        return true;
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
                    if (board[i - a][j + a] == piece) {
                        count++;
                    }
                    //if the slot checked is empty, set the move to that column
                    else if (board[i - a][j + a] == newState.getEMPTY()) {
                        if (j + a == inThisCol) {
                            missingCol = j + a;
                            tempRow = i - a;
                        }
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
                    if (tempRow < board.length - 2) {
                        //if the move's row can be moved to, return the move
                        if (board[tempRow + 2][missingCol] != newState.getEMPTY()) {
                            if (board[tempRow + 1][missingCol] == newState.getEMPTY()) {
                                return true;
                            }
                        }
                    }
                    //if the move's row is the bottom row, return the move
                    else if (tempRow == 4) {
                        return true;
                    }
                }
            }
        }

        return false;

    }


    public int getSchmidty() {
        return schmidty;
    }
}