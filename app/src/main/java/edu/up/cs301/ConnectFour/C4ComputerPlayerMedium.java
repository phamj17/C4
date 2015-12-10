package edu.up.cs301.ConnectFour;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by macnary17 on 12/1/2015.
 */
public class C4ComputerPlayerMedium extends GameComputerPlayer {

    ConnectFourState newState = new ConnectFourState();
    //set up 2D array to hold board values
    public int[][] board = new int[6][7];

    public C4ComputerPlayerMedium(String name) {
        super(name);
    }

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



        if (winner != -1) {
            move = winner;
        } else if (stopHuman != -1) {
            move = stopHuman;
        }else if (winner2H!= -1) {
                move = winner2H;
        } else if (midCol < 6) {
            move = 3;
        }
        else {
            move = rand.nextInt(7);
        }

        //If opponent cant get 4 in a row, move randomly
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
        //random movements
    }

    public int stopVerticalWin() {
        int count = 0;
        int piece = newState.getRED();
        int tempCol;

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
                if (board[i][j] == piece) {
                    if (i >= 3) {
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
        }
        return -1;
    }

    public int stopHorizontalWin() {

        int count = 0;
        int piece = newState.getRED();

        int missingCol = -1;

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

                if (count == 3 && missingCol != -1) {
                    if (i < board.length - 1) {
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;

    }

    public int stop2Horizontal() {

        int count = 0;
        int piece = newState.getRED();

        int missingCol = -1;

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

                if (count == 2 && missingCol != -1) {
                    if (i < board.length - 1) {
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }

    public int stopDiagonalWin() {

        int count = 0;
        int missingCol = -1;
        int tempRow = -1;
        int piece = newState.getRED();

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
                if (count == 3 && missingCol != -1) {
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
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
                if (count == 3 && missingCol != -1) {
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }

    public int winV() {
        int count = 0;
        int piece = newState.getBLACK();
        int tempCol;

        for (int i = board.length - 1; i >= 3; i--) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
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

    public int winH() {

        int count = 0;
        int piece = newState.getBLACK();

        int missingCol = -1;

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

                if (count == 3 && missingCol != -1) {
                    if (i < board.length - 1) {
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;

    }
    public int winD() {

        int count = 0;
        int missingCol = -1;
        int tempRow = -1;
            int piece = newState.getBLACK();

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
                if (count == 3 && missingCol != -1) {
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
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
                if (count == 3 && missingCol != -1) {
                    if (tempRow < board.length - 1) {
                        if (board[tempRow + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (tempRow == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }
    public int win2H() {

        int count = 0;
        int piece = newState.getBLACK();

        int missingCol = -1;

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

                if (count == 2 && missingCol != -1) {
                    if (i < board.length - 1) {
                        if (board[i + 1][missingCol] != newState.getEMPTY()) {
                            return missingCol;
                        }
                    } else if (i == board.length - 1) {
                        return missingCol;
                    }
                }
            }
        }
        return -1;
    }
}