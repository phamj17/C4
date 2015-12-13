package edu.up.cs301.game;

import edu.up.cs301.ConnectFour.C4DropActionCol0;
import edu.up.cs301.ConnectFour.C4DropActionCol1;
import edu.up.cs301.ConnectFour.C4DropActionCol2;
import edu.up.cs301.ConnectFour.C4DropActionCol3;
import edu.up.cs301.ConnectFour.C4DropActionCol4;
import edu.up.cs301.ConnectFour.C4DropActionCol5;
import edu.up.cs301.ConnectFour.C4DropActionCol6;
import edu.up.cs301.ConnectFour.ConnectFourState;
import edu.up.cs301.ConnectFour.ResetAction;
import edu.up.cs301.ConnectFour.UndoAction;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.BindGameInfo;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.NetworkObjectPasser;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A Player object that is used as a proxy for the real player that is on another
 * machine on the network. Whenever a message is sent to the ProxyPlayer object,
 * it serializes the message and sends it across the network; when
 * the ProxyPlayer object receives a message from the network, it
 * unserializes the message and sends it to its game.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class ProxyPlayer implements GamePlayer {

    ConnectFourState newState = new ConnectFourState();
    private int[][] tempBoard = new int[6][7];

    private Button reset = null;
    private Button undo = null;
    private ImageButton column1Button = null;
    private ImageButton column2Button = null;
    private ImageButton column3Button = null;
    private ImageButton column4Button = null;
    private ImageButton column5Button = null;
    private ImageButton column6Button = null;
    private ImageButton column7Button = null;
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView13;
    private ImageView imageView14;
    private ImageView imageView15;
    private ImageView imageView16;
    private ImageView imageView17;
    private ImageView imageView18;
    private ImageView imageView19;
    private ImageView imageView20;
    private ImageView imageView21;
    private ImageView imageView22;
    private ImageView imageView23;
    private ImageView imageView24;
    private ImageView imageView25;
    private ImageView imageView26;
    private ImageView imageView27;
    private ImageView imageView28;
    private ImageView imageView29;
    private ImageView imageView30;
    private ImageView imageView31;
    private ImageView imageView32;
    private ImageView imageView33;
    private ImageView imageView34;
    private ImageView imageView35;
    private ImageView imageView36;
    private ImageView imageView37;
    private ImageView imageView38;
    private ImageView imageView39;
    private ImageView imageView40;
    private ImageView imageView41;
    // the android activity that we are running
    private GameMainActivity myActivity;


    // the game object
    private Game game;

    // the object that connects us to the network
    private NetworkObjectPasser networkPasser;

    // whether this player is ready to play the game.  In this case, being
    // ready essentially means that the a connection with a remote player
    // has been established
    private boolean isReady;

    /**
     * ProxyPlayer constructor.
     *
     * @param portNum the port number through which we connect to our client
     */
    public ProxyPlayer(int portNum) {

        Log.i("ProxyPlayer", "creating Proxy Player");

        // set instance variables to their initial values
        game = null; // the game
        isReady = false; // whether we are ready

        // create our network-connection object, connecting as a server
        networkPasser =
                new NetworkObjectPasser(null, portNum) {

                    // callback method, called whenever we receive an object
                    // that has come across the network
                    public void onReceiveObject(Object obj) {
                        if (obj instanceof GameAction) {
                            // if it's a game action (which it should be), send
                            // the action to the game
                            GameAction action = (GameAction) obj;
                            action.setPlayer(ProxyPlayer.this);
                            game.sendAction(action);
                        }
                    }
                };


}

    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }
    /**
     * Tells whether the player is ready to play the game.
     *
     * @return a boolean value indicating whether the player is ready
     *   to play.
     */
    public boolean isReady() {
        // return value based on whether we are marked as being ready
        return isReady;
    }

    
    /**
     * Starts the game. (In this case the constructor has already done
     * all the work.)
     */
    public void start() {
    }

    /**
     * Used by the game to send a GameInfo object to this player
     * 
     * @param state
     * 		The state to send
     */
    public void sendInfo(GameInfo state) {
    	if (game == null && state instanceof BindGameInfo) {
    		// If we're just starting (so we don't know who
    		// game is), then it had better be a BindGameInfo
    		// message. Get the game from the BindGameInfo
    		// object so that we have the connection for
    		// future messages.
    		game = ((BindGameInfo)state).getGame();
    	}
    	
    	// Null out the game from the GameInfo object (if present),
    	// so that the entire game does not get passed across the
    	// network
    	state.setGame(null);
    	
    	// send the state across the network
    	networkPasser.sendObject(state);
	}
    
    
    /**
     * Set this game as a GUI. (Should never be called because the
     * 'supportsGui' method returns false.)
     */
    public final void gameSetAsGui(GameMainActivity a) {
    }
    
    /**
     * Set this game as a GUI. (Should never be called because the
     * 'supportsGui' method returns false.)
     */
//    public void setAsGui(GameMainActivity a) {
//    }
    
    /**
     * Tells whether the this player requires a GUI.
     * 
     * @return
     * 		false, since this player does not require a GUI
     */
    public boolean requiresGui() {
    	return false;
    }
    
    /**
     * Tells whether the this player support a GUI.
     * 
     * @return
     * 		false, since this player does not support a GUI
     */
    public boolean supportsGui() {
    	return false;
    }

    /**
     * this method gets called when the user clicks the '+' or '-' button. It
     * creates a new CounterMoveAction to return to the parent activity.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {

        if (button == column1Button) {
            C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
            game.sendAction(dropActionCol0);
        }
        if (button == column2Button) {
            C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
            game.sendAction(dropActionCol1);
        }
        if (button == column3Button) {
            C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
            game.sendAction(dropActionCol2);
        }
        if (button == column4Button) {
            C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
            game.sendAction(dropActionCol3);
        }
        if (button == column5Button) {
            C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
            game.sendAction(dropActionCol4);
        }
        if (button == column6Button) {
            C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
            game.sendAction(dropActionCol5);
        }
        if (button == column7Button) {
            C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
            game.sendAction(dropActionCol6);
        }
        if (button == reset){
            ResetAction resetAction = new ResetAction(this);
            game.sendAction(resetAction);
        }
        if (button == undo)
        {
            UndoAction undoAction = new UndoAction(this);
            game.sendAction(undoAction);
        }

    }// onClick


    public void receiveInfo(GameInfo info) {

        if (info instanceof ConnectFourState) {
            newState = (ConnectFourState) info;
            //TODO GET the board please... please...
        }
        tempBoard = newState.getBoard();

        if (tempBoard[5][0]==1)
        {
            imageView5.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][0]==2)
        {
            imageView5.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][0]== 0)
        {
            imageView5.setImageResource(R.mipmap.clear);
        }


        if (tempBoard[4][0]==1)
        {
            imageView4.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][0]==2)
        {
            imageView4.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][0]== 0)
        {
            imageView4.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[3][0]==1)
        {
            imageView3.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][0]==2)
        {
            imageView3.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][0]== 0)
        {
            imageView3.setImageResource(R.mipmap.clear);
        }


        if (tempBoard[2][0]==1)
        {
            imageView2.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][0]==2)
        {
            imageView2.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][0]== 0)
        {
            imageView2.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][0]==1)
        {
            imageView1.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][0]==2)
        {
            imageView1.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][0]== 0)
        {
            imageView1.setImageResource(R.mipmap.clear);
        }


        if (tempBoard[0][0]==1)
        {
            imageView0.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][0]==2)
        {
            imageView0.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][0]== 0)
        {
            imageView0.setImageResource(R.mipmap.clear);
        }





        if (tempBoard[5][1]==1)
        {
            imageView11.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][1]==2)
        {
            imageView11.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][1]== 0)
        {
            imageView11.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][1]==1)
        {
            imageView10.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][1]==2)
        {
            imageView10.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][1]== 0)
        {
            imageView10.setImageResource(R.mipmap.clear);
        }
        if (tempBoard[3][1]==1)
        {
            imageView9.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][1]==2)
        {
            imageView9.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][1]== 0)
        {
            imageView9.setImageResource(R.mipmap.clear);
        }
        if (tempBoard[2][1]==1)
        {
            imageView8.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][1]==2)
        {
            imageView8.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][1]== 0)
        {
            imageView8.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][1]==1)
        {
            imageView7.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][1]==2)
        {
            imageView7.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][1]== 0)
        {
            imageView7.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][1]==1)
        {
            imageView6.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][1]==2)
        {
            imageView6.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][1]== 0)
        {
            imageView6.setImageResource(R.mipmap.clear);
        }




        if (tempBoard[5][2]==1)
        {
            imageView17.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][2]==2)
        {
            imageView17.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][2]== 0)
        {
            imageView17.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][2]==1)
        {
            imageView16.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][2]==2)
        {
            imageView16.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][2]== 0)
        {
            imageView16.setImageResource(R.mipmap.clear);
        }
        if (tempBoard[3][2]==1)
        {
            imageView15.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][2]==2)
        {
            imageView15.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][2]== 0)
        {
            imageView15.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[2][2]==1)
        {
            imageView14.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][2]==2)
        {
            imageView14.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][2]== 0)
        {
            imageView14.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][2]==1)
        {
            imageView13.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][2]==2)
        {
            imageView13.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][2]== 0)
        {
            imageView13.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][2]==1)
        {
            imageView12.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][2]==2)
        {
            imageView12.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][2]== 0)
        {
            imageView12.setImageResource(R.mipmap.clear);
        }




        if (tempBoard[5][3]==1)
        {
            imageView23.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][3]==2)
        {
            imageView23.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][3]== 0)
        {
            imageView23.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][3]==1)
        {
            imageView22.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][3]==2)
        {
            imageView22.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][3]== 0)
        {
            imageView22.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[3][3]==1)
        {
            imageView21.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][3]==2)
        {
            imageView21.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][3]== 0)
        {
            imageView21.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[2][3]==1)
        {
            imageView20.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][3]==2)
        {
            imageView20.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][3]== 0)
        {
            imageView20.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][3]==1)
        {
            imageView19.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][3]==2)
        {
            imageView19.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][3]== 0)
        {
            imageView19.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][3]==1)
        {
            imageView18.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][3]==2)
        {
            imageView18.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][3]== 0)
        {
            imageView18.setImageResource(R.mipmap.clear);
        }



        if (tempBoard[5][4]==1)
        {
            imageView29.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][4]==2)
        {
            imageView29.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][4]== 0)
        {
            imageView29.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][4]==1)
        {
            imageView28.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][4]==2)
        {
            imageView28.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][4]== 0)
        {
            imageView28.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[3][4]==1)
        {
            imageView27.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][4]==2)
        {
            imageView27.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][4]== 0)
        {
            imageView27.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[2][4]==1)
        {
            imageView26.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][4]==2)
        {
            imageView26.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][4]== 0)
        {
            imageView26.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][4]==1)
        {
            imageView25.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][4]==2)
        {
            imageView25.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][4]== 0)
        {
            imageView25.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][4]==1)
        {
            imageView24.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][4]==2)
        {
            imageView24.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][4]== 0)
        {
            imageView24.setImageResource(R.mipmap.clear);
        }


        if (tempBoard[5][5]==1)
        {
            imageView35.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][5]==2)
        {
            imageView35.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][5]== 0)
        {
            imageView35.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][5]==1)
        {
            imageView34.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][5]==2)
        {
            imageView34.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][5]== 0)
        {
            imageView34.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[3][5]==1)
        {
            imageView33.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][5]==2)
        {
            imageView33.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][5]== 0)
        {
            imageView33.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[2][5]==1)
        {
            imageView32.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][5]==2)
        {
            imageView32.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][5]== 0)
        {
            imageView32.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][5]==1)
        {
            imageView31.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][5]==2)
        {
            imageView31.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][5]== 0)
        {
            imageView31.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][5]==1)
        {
            imageView30.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][5]==2)
        {
            imageView30.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][5]== 0)
        {
            imageView30.setImageResource(R.mipmap.clear);
        }



        if (tempBoard[5][6]==1)
        {
            imageView41.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[5][6]==2)
        {
            imageView41.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[5][6]== 0)
        {
            imageView41.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[4][6]==1)
        {
            imageView40.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[4][6]==2)
        {
            imageView40.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[4][6]== 0)
        {
            imageView40.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[3][6]==1)
        {
            imageView39.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[3][6]==2)
        {
            imageView39.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[3][6]== 0)
        {
            imageView39.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[2][6]==1)
        {
            imageView38.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[2][6]==2)
        {
            imageView38.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[2][6]== 0)
        {
            imageView38.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[1][6]==1)
        {
            imageView37.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[1][6]==2)
        {
            imageView37.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[1][6]== 0)
        {
            imageView37.setImageResource(R.mipmap.clear);
        }

        if (tempBoard[0][6]==1)
        {
            imageView36.setImageResource(R.mipmap.red);
        }
        else if(tempBoard[0][6]==2)
        {
            imageView36.setImageResource(R.mipmap.yellow);
        }
        else if(tempBoard[0][6]== 0)
        {
            imageView36.setImageResource(R.mipmap.clear);
        }

    }//receiveInfo

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.connectfourboard);

        this.reset = (Button) activity.findViewById(R.id.reset);
        this.undo = (Button) activity.findViewById(R.id.undo);

        //Initialize the widget reference member variables
        column1Button = (ImageButton)activity.findViewById(R.id.Col1);
        column2Button = (ImageButton)activity.findViewById(R.id.Col2);
        column3Button = (ImageButton)activity.findViewById(R.id.Col3);
        column4Button = (ImageButton)activity.findViewById(R.id.Col4);
        column5Button = (ImageButton)activity.findViewById(R.id.Col5);
        column6Button = (ImageButton)activity.findViewById(R.id.Col6);
        column7Button = (ImageButton)activity.findViewById(R.id.Col7);

        imageView0 = (ImageView)activity.findViewById(R.id.imageView0);
        imageView1 = (ImageView)activity.findViewById(R.id.imageView1);
        imageView2 = (ImageView)activity.findViewById(R.id.imageView2);
        imageView3 = (ImageView)activity.findViewById(R.id.imageView3);
        imageView4 = (ImageView)activity.findViewById(R.id.imageView4);
        imageView5 = (ImageView)activity.findViewById(R.id.imageView5);
        imageView6 = (ImageView)activity.findViewById(R.id.imageView6);
        imageView7 = (ImageView)activity.findViewById(R.id.imageView7);
        imageView8 = (ImageView)activity.findViewById(R.id.imageView8);
        imageView9 = (ImageView)activity.findViewById(R.id.imageView9);
        imageView10 = (ImageView)activity.findViewById(R.id.imageView10);
        imageView11 = (ImageView)activity.findViewById(R.id.imageView11);
        imageView12 = (ImageView)activity.findViewById(R.id.imageView12);
        imageView13 = (ImageView)activity.findViewById(R.id.imageView13);
        imageView14 = (ImageView)activity.findViewById(R.id.imageView14);
        imageView15 = (ImageView)activity.findViewById(R.id.imageView15);
        imageView16 = (ImageView)activity.findViewById(R.id.imageView16);
        imageView17 = (ImageView)activity.findViewById(R.id.imageView17);
        imageView18 = (ImageView)activity.findViewById(R.id.imageView18);
        imageView19 = (ImageView)activity.findViewById(R.id.imageView19);
        imageView20 = (ImageView)activity.findViewById(R.id.imageView20);
        imageView21 = (ImageView)activity.findViewById(R.id.imageView21);
        imageView22 = (ImageView)activity.findViewById(R.id.imageView22);
        imageView23 = (ImageView)activity.findViewById(R.id.imageView23);
        imageView24 = (ImageView)activity.findViewById(R.id.imageView24);
        imageView25 = (ImageView)activity.findViewById(R.id.imageView25);
        imageView26 = (ImageView)activity.findViewById(R.id.imageView26);
        imageView27 = (ImageView)activity.findViewById(R.id.imageView27);
        imageView28 = (ImageView)activity.findViewById(R.id.imageView28);
        imageView29 = (ImageView)activity.findViewById(R.id.imageView29);
        imageView30 = (ImageView)activity.findViewById(R.id.imageView30);
        imageView31 = (ImageView)activity.findViewById(R.id.imageView31);
        imageView32 = (ImageView)activity.findViewById(R.id.imageView32);
        imageView33 = (ImageView)activity.findViewById(R.id.imageView33);
        imageView34 = (ImageView)activity.findViewById(R.id.imageView34);
        imageView35 = (ImageView)activity.findViewById(R.id.imageView35);
        imageView36 = (ImageView)activity.findViewById(R.id.imageView36);
        imageView37 = (ImageView)activity.findViewById(R.id.imageView37);
        imageView38 = (ImageView)activity.findViewById(R.id.imageView38);
        imageView39 = (ImageView)activity.findViewById(R.id.imageView39);
        imageView40 = (ImageView)activity.findViewById(R.id.imageView40);
        imageView41 = (ImageView)activity.findViewById(R.id.imageView41);

//        //Listen for button presses
//        column1Button.setOnClickListener(this);
//        column2Button.setOnClickListener(this);
//        column3Button.setOnClickListener(this);
//        column4Button.setOnClickListener(this);
//        column5Button.setOnClickListener(this);
//        column6Button.setOnClickListener(this);
//        column7Button.setOnClickListener(this);
//
//        reset.setOnClickListener(this);
//        undo.setOnClickListener(this);

    }//setAsGui

}// These variables will reference widgets that will be modified during play




