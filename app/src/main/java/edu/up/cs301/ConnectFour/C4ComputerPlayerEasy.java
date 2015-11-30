package edu.up.cs301.ConnectFour;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * Created by macnary17 on 11/21/2015.
 */
public class C4ComputerPlayerEasy extends GameComputerPlayer{

    ConnectFourState newState = new ConnectFourState();
    //set up 2D array to hold board values
    public int[][] board = new int[6][7];

    public C4ComputerPlayerEasy(String name) {
        super(name);
    }

    protected void receiveInfo(GameInfo info) {
        //Check if opponent can get 4 in a row
        if(info instanceof ConnectFourState)
        {
            newState = (ConnectFourState) info;
            board = newState.getBoard();
            int playerIdx = newState.getTurn();
        }


        Random rand = new Random();
        int move = rand.nextInt(7) + 1;

        //If opponent cant get 4 in a row, move randomly
        if (move == 1)
        {
            C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
            game.sendAction(dropActionCol0);
        }
        else if (move == 2)
        {
            C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
            game.sendAction(dropActionCol1);
        }
        else if (move == 3)
        {
            C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
            game.sendAction(dropActionCol2);
        }
        else if (move == 4)
        {
            C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
            game.sendAction(dropActionCol3);
        }
        else if (move == 5)
        {
            C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
            game.sendAction(dropActionCol4);
        }
        else if (move == 6)
        {
            C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
            game.sendAction(dropActionCol5);
        }
        else if (move == 7)
        {
            C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
            game.sendAction(dropActionCol6);
        }
        //random movements
    }


}
