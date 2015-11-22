package edu.up.cs301.ConnectFour;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macnary17 on 11/6/2015.
 */
public class ConnectFourStateTest {

    @Test
    //test the getPiece method
    public void testGetPiece() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //empty spot at 1,1
        //get what is at 1,1
        int testThis = testState.getPiece(1,1);
        //assert that there is no piece in 1,1
        assertEquals(testThis, 0);
    }

    @Test
    //test setPiece method
    public void testSetPiece() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //set pieces at column 0
        //gameOver method switches player for now
        testState.setPiece(0);
        testState.gameOver();
        testState.setPiece(0);
        testState.gameOver();
        testState.setPiece(0);
        testState.gameOver();
        testState.setPiece(0);
        testState.gameOver();
        testState.setPiece(0);
        testState.gameOver();
        testState.setPiece(0);
        testState.gameOver();

        //check if the pieces go out of bounds
        boolean test = testState.setPiece(0);
        int testThis = testState.getPiece(0, 2);
        assertEquals(test, false);
        assertEquals(testThis, 1);
    }

    @Test
    //test getBoard method
    public void testGetBoard() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //set piece in column 1
        testState.setPiece(1);
        testState.gameOver();
        int[][] testThisBoard; //= new int[6][7];
        testThisBoard = testState.getBoard();

        //assert that there is one piece in column 1
        assertEquals(testThisBoard[1][0], 1);
    }

    @Test
    //test getGameOver method
    public void testGetGameOver() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //if no pieces are played, game over should be false
        boolean testThis = testState.getGameOver();
        assertEquals(testThis, false);

    }

    @Test
    //test gameOver method
    public void testGameOver() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //set pieces so that there is a horizontal win
        testState.setPiece(1); //r
        testState.gameOver();
        testState.setPiece(1); //b
        testState.gameOver();
        testState.setPiece(2); //r
        testState.gameOver();
        testState.setPiece(2); //b
        testState.gameOver();
        testState.setPiece(3); //r
        testState.gameOver();
        testState.setPiece(3); //b
        testState.gameOver();
        testState.setPiece(4); //r
        testState.gameOver();
        testState.setPiece(0); //b
        testState.gameOver();
        //game should be over, boolean will be true if the game is over
        boolean test = testState.gameOver();
        assertEquals(test, true);


    }

    @Test
    //test diagonalWin method
    public void testDiagonalWin() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //test diagonal pieces placed bottom to top, left to right
        testState.setPiece(1); //r
        testState.gameOver();
        testState.setPiece(2); //b
        testState.gameOver();
        testState.setPiece(2); //r
        testState.gameOver();
        testState.setPiece(3); //b
        testState.gameOver();
        testState.setPiece(3); //r
        testState.gameOver();
        testState.setPiece(4); //b
        testState.gameOver();
        testState.setPiece(3); //r
        testState.gameOver();
        testState.setPiece(4); //b
        testState.gameOver();
        testState.setPiece(4); //r
        testState.gameOver();
        testState.setPiece(0); //b
        testState.gameOver();
        testState.setPiece(4); //r
        testState.gameOver();
        boolean test = testState.diagonalWin();
        assertEquals(test, true);


        ConnectFourState testState3 = new ConnectFourState();
        //test diagonal pieces placed top to bottom, right to left
        testState3.setPiece(4); //r
        testState.gameOver();
        testState3.setPiece(3); //b
        testState.gameOver();
        testState3.setPiece(3); //r
        testState.gameOver();
        testState3.setPiece(2); //b
        testState.gameOver();
        testState3.setPiece(2); //r
        testState.gameOver();
        testState3.setPiece(1); //b
        testState.gameOver();
        testState3.setPiece(2); //r
        testState.gameOver();
        testState3.setPiece(1); //b
        testState.gameOver();
        testState3.setPiece(1); //r
        testState.gameOver();
        testState3.setPiece(5); //b
        testState.gameOver();
        testState3.setPiece(1); //r
        testState.gameOver();
        boolean test21 = testState3.diagonalWin();
        assertEquals(test21, true);


    }

    @Test
    //test horizontalWin method
    public void testHorizontalWin() throws Exception {
        ConnectFourState testState = new ConnectFourState();

        //set pieces in a horizontal pattern
        //again, gameOver makes sure the next player gets to set a piece
        testState.setPiece(1); //r
        testState.gameOver();
        testState.setPiece(1); //b
        testState.gameOver();
        testState.setPiece(2); //r
        testState.gameOver();
        testState.setPiece(2); //b
        testState.gameOver();
        testState.setPiece(3); //r
        testState.gameOver();
        testState.setPiece(3); //b
        testState.gameOver();
        testState.setPiece(4); //r
        testState.gameOver();
        boolean test = testState.horizontalWin();
        assertEquals(test, true);

    }

    @Test
    //test verticalWin method
    public void testVerticalWin() throws Exception {
        ConnectFourState testState = new ConnectFourState();

        testState.setPiece(0); //r
        testState.gameOver();
        testState.setPiece(0); //b
        testState.gameOver();
        testState.setPiece(0); //r, 1
        testState.gameOver();
        testState.setPiece(1); //b
        testState.gameOver();
        testState.setPiece(0); //r, 2
        testState.gameOver();
        testState.setPiece(2); //b
        testState.gameOver();
        testState.setPiece(0); //r, 3
        testState.gameOver();
        testState.setPiece(2); //b
        testState.gameOver();
        testState.setPiece(0); //r, 4
        testState.gameOver();

        boolean test = testState.verticalWin();
        assertEquals(test, true);
    }

    @Test
    //test getTurn method
    public void testGetTurn() throws Exception {
        ConnectFourState testState = new ConnectFourState();

        //player 0 goes first, test to make sure it is player 0s turn
        int testThis = testState.getTurn();
        assertEquals(testThis, 0);
    }

    @Test
    //test getColumn method
    public void testGetColumn() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        //no pieces have been set, that means there should be a zero in the first column array value
        int[] testCol = testState.getColumn();
        assertEquals(testCol[0], 0);
    }
}