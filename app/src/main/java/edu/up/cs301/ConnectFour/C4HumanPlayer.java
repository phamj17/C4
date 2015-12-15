package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.Serializable;

/**
 * Implements the necessary code to make a human player play
 * Created by macnary17 on 11/21/2015.
 */
public class C4HumanPlayer extends GameHumanPlayer implements OnClickListener, Serializable{

    //instance variables
    ConnectFourState newState = new ConnectFourState();
    private int[][] tempBoard = new int[6][7];

    private Button reset = null;
    private Button undo = null;


    // These variables will reference widgets that will be modified during play
    private ImageButton    column1Button = null;
    private ImageButton    column2Button = null;
    private ImageButton    column3Button = null;
    private ImageButton    column4Button = null;
    private ImageButton    column5Button = null;
    private ImageButton    column6Button = null;
    private ImageButton    column7Button = null;
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
    private MediaPlayer player;

    //constructor
    public C4HumanPlayer(String name) {
        super(name);
    }

    /**
     * Gets the top view layout
     * @return the view
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        //if we passed in a state, save that state into our variable called newState
        if (info instanceof ConnectFourState) {
            newState = (ConnectFourState) info;
            //TODO GET the board please... please...
        }
        tempBoard = newState.getBoard();;

        //checks what piece has been played in each space on the board and sets the
        //appropriate image to display the current state of the board
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
     * this method gets called when the user clicks on a button and the appropriate
     * action takes place
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {

        //if they clicked a certain column, send the appropriate action
        if (button == column1Button) {
            C4DropActionCol0 dropActionCol0 = new C4DropActionCol0(this);
            game.sendAction(dropActionCol0);
            player.start();

        }
        if (button == column2Button) {
            C4DropActionCol1 dropActionCol1 = new C4DropActionCol1(this);
            game.sendAction(dropActionCol1);
            player.start();
        }
        if (button == column3Button) {
            C4DropActionCol2 dropActionCol2 = new C4DropActionCol2(this);
            game.sendAction(dropActionCol2);
            player.start();
        }
        if (button == column4Button) {
            C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
            game.sendAction(dropActionCol3);
            player.start();
        }
        if (button == column5Button) {
            C4DropActionCol4 dropActionCol4 = new C4DropActionCol4(this);
            game.sendAction(dropActionCol4);
            player.start();
        }
        if (button == column6Button) {
            C4DropActionCol5 dropActionCol5 = new C4DropActionCol5(this);
            game.sendAction(dropActionCol5);
            player.start();
        }
        if (button == column7Button) {
            C4DropActionCol6 dropActionCol6 = new C4DropActionCol6(this);
            game.sendAction(dropActionCol6);
            player.start();
        }

        //if reset was pressed, send a resetAction
        if (button == reset){
            ResetAction resetAction = new ResetAction(this);
            game.sendAction(resetAction);
        }

        //if undo was pressed, send an undoAction
        if (button == undo)
        {
            UndoAction undoAction = new UndoAction(this);
            game.sendAction(undoAction);
        }

    }// onClick


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

        //if(getSchmidty)
        this.undo = (Button) activity.findViewById(R.id.undo);

        //media player
        player = MediaPlayer.create(myActivity,R.raw.piece_set);

        //Initialize the widget reference member variables
        this.column1Button = (ImageButton)activity.findViewById(R.id.Col1);
        this.column2Button = (ImageButton)activity.findViewById(R.id.Col2);
        this.column3Button = (ImageButton)activity.findViewById(R.id.Col3);
        this.column4Button = (ImageButton)activity.findViewById(R.id.Col4);
        this.column5Button = (ImageButton)activity.findViewById(R.id.Col5);
        this.column6Button = (ImageButton)activity.findViewById(R.id.Col6);
        this.column7Button = (ImageButton)activity.findViewById(R.id.Col7);

        this.imageView0 = (ImageView)activity.findViewById(R.id.imageView0);
        this.imageView1 = (ImageView)activity.findViewById(R.id.imageView1);
        this.imageView2 = (ImageView)activity.findViewById(R.id.imageView2);
        this.imageView3 = (ImageView)activity.findViewById(R.id.imageView3);
        this.imageView4 = (ImageView)activity.findViewById(R.id.imageView4);
        this.imageView5 = (ImageView)activity.findViewById(R.id.imageView5);
        this.imageView6 = (ImageView)activity.findViewById(R.id.imageView6);
        this.imageView7 = (ImageView)activity.findViewById(R.id.imageView7);
        this.imageView8 = (ImageView)activity.findViewById(R.id.imageView8);
        this.imageView9 = (ImageView)activity.findViewById(R.id.imageView9);
        this.imageView10 = (ImageView)activity.findViewById(R.id.imageView10);
        this.imageView11 = (ImageView)activity.findViewById(R.id.imageView11);
        this.imageView12 = (ImageView)activity.findViewById(R.id.imageView12);
        this.imageView13 = (ImageView)activity.findViewById(R.id.imageView13);
        this.imageView14 = (ImageView)activity.findViewById(R.id.imageView14);
        this.imageView15 = (ImageView)activity.findViewById(R.id.imageView15);
        this.imageView16 = (ImageView)activity.findViewById(R.id.imageView16);
        this.imageView17 = (ImageView)activity.findViewById(R.id.imageView17);
        this.imageView18 = (ImageView)activity.findViewById(R.id.imageView18);
        this.imageView19 = (ImageView)activity.findViewById(R.id.imageView19);
        this.imageView20 = (ImageView)activity.findViewById(R.id.imageView20);
        this.imageView21 = (ImageView)activity.findViewById(R.id.imageView21);
        this.imageView22 = (ImageView)activity.findViewById(R.id.imageView22);
        this.imageView23 = (ImageView)activity.findViewById(R.id.imageView23);
        this.imageView24 = (ImageView)activity.findViewById(R.id.imageView24);
        this.imageView25 = (ImageView)activity.findViewById(R.id.imageView25);
        this.imageView26 = (ImageView)activity.findViewById(R.id.imageView26);
        this.imageView27 = (ImageView)activity.findViewById(R.id.imageView27);
        this.imageView28 = (ImageView)activity.findViewById(R.id.imageView28);
        this.imageView29 = (ImageView)activity.findViewById(R.id.imageView29);
        this.imageView30 = (ImageView)activity.findViewById(R.id.imageView30);
        this.imageView31 = (ImageView)activity.findViewById(R.id.imageView31);
        this.imageView32 = (ImageView)activity.findViewById(R.id.imageView32);
        this.imageView33 = (ImageView)activity.findViewById(R.id.imageView33);
        this.imageView34 = (ImageView)activity.findViewById(R.id.imageView34);
        this.imageView35 = (ImageView)activity.findViewById(R.id.imageView35);
        this.imageView36 = (ImageView)activity.findViewById(R.id.imageView36);
        this.imageView37 = (ImageView)activity.findViewById(R.id.imageView37);
        this.imageView38 = (ImageView)activity.findViewById(R.id.imageView38);
        this.imageView39 = (ImageView)activity.findViewById(R.id.imageView39);
        this.imageView40 = (ImageView)activity.findViewById(R.id.imageView40);
        this.imageView41 = (ImageView)activity.findViewById(R.id.imageView41);

        //Listen for button presses
        column1Button.setOnClickListener(this);
        column2Button.setOnClickListener(this);
        column3Button.setOnClickListener(this);
        column4Button.setOnClickListener(this);
        column5Button.setOnClickListener(this);
        column6Button.setOnClickListener(this);
        column7Button.setOnClickListener(this);

        reset.setOnClickListener(this);
        undo.setOnClickListener(this);

    }//setAsGui

}
