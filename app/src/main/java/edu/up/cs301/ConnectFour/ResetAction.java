package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A class that extends a game action that tells us if a player is trying
 * to reset the board
 * Created by macnary17 on 12/3/2015.
 */
public class ResetAction extends GameAction{

    //constructor
    public ResetAction(GamePlayer player) {
        super(player);
    }

}