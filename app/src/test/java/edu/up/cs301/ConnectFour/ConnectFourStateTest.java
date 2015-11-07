package edu.up.cs301.ConnectFour;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macnary17 on 11/6/2015.
 */
public class ConnectFourStateTest {

    @Test
    public void testGetPiece() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        int testThis = testState.getPiece(1,1);
        assertEquals(testThis, 0);
    }

    @Test
    public void testSetPiece() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        testState.setPiece(1, 1, 0);
        int testThis = testState.getPiece(1, 1);
        assertEquals(testThis, 1);
    }

    @Test
    public void testGetBoard() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        testState.setPiece(1, 1, 0);
        int[][] testThisBoard; //= new int[6][7];
        testThisBoard = testState.getBoard();
        assertEquals(testThisBoard[1][1], 1);
    }

    @Test
    public void testGetGameOver() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        boolean testThis = testState.getGameOver();
        assertEquals(testThis, false);

    }

    @Test
    public void testGetTurn() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        int testThis = testState.getTurn();
        assertEquals(testThis, 0);
    }

    @Test
    public void testGetColumn() throws Exception {
        ConnectFourState testState = new ConnectFourState();
        int[] testCol = testState.getColumn();
        assertEquals(testCol[0], 0);
    }
}