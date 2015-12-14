package edu.up.cs301.ConnectFour;

/**
 * Created by macnary17 on 11/30/2015.
 */

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

/**
 * class C4LocalGame controls the play of the game
 *
 * @author Group 6
 * @version November 27
 */
public class C4LocalGame extends LocalGame {

    //instance variable
    public ConnectFourState state;
    Context stuff;

    /**
     * This constructor creates a new game state
     */
    public C4LocalGame() {
        state = new ConnectFourState();
    }
    public void getContext(Context context)
    {
        stuff = context;
    }
    /**
     * can the player with the given id take an action right now?
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return a boolean that says if they can move or not
     */
    @Override
    public boolean canMove(int playerIdx) {
        if (state.getTurn() == playerIdx) {
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    public boolean makeMove(GameAction action) {
        if (canMove(state.getTurn()) == false) {
            return false;
        }

        //if the action is one of the drop actions, call the appropriate method
        //on the state
        if (action instanceof C4DropActionCol0) {
            state.dropActionCol0();
            return true;
        }
        if (action instanceof C4DropActionCol1) {
            state.dropActionCol1();
            return true;
        }
        if (action instanceof C4DropActionCol2) {
            state.dropActionCol2();
            return true;
        }
        if (action instanceof C4DropActionCol3) {
            state.dropActionCol3();
            return true;
        }
        if (action instanceof C4DropActionCol4) {
            state.dropActionCol4();
            return true;
        }
        if (action instanceof C4DropActionCol5) {
            state.dropActionCol5();
            return true;
        }
        if (action instanceof C4DropActionCol6) {
            state.dropActionCol6();
            return true;
        }

        //if  the action is a reset action, call the resetAction method on the state
        if (action instanceof ResetAction) {
            state.resetAction();
            return true;
        }

        //if  the action is an undo action, call the undoAction method on the state
        if (action instanceof UndoAction){
            state.undoAction();
            return true;
        }
        return false;

    }//makeMove

    /**
     * send the updated state to a given player
     * @param p the GamePlayer
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ConnectFourState copy = new ConnectFourState(state);
        p.sendInfo(copy);

    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    public String checkIfGameOver() {
        String victory = null;
        //if the game is over, check who won
        if (state.gameOver()) {
            if (state.getDraw() == 1)
            {
                victory = "TIE GAME";
            }
            //if it's player 2's turn, set the string to say yellow won
            else if(state.getTurn() == 1)
            {
                victory = "YELLOW WON";
            }
            //if it's player 1's turn, set the string to say yellow won
            else if(state.getTurn() == 0)
            {
                victory = "RED WON";
            }
        }

        //return the string
        return victory;
    }

}// class C4LocalGame
