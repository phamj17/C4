package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A class that extends a game action that tells us if a player is trying
 * to play a piece in column3
 * Created by macnary17 on 11/21/2015.
 */
public class C4DropActionCol3 extends GameAction{

    //constructor
    public C4DropActionCol3(GamePlayer player) {
        super(player);
    }

}