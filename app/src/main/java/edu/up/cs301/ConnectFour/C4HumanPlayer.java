package edu.up.cs301.ConnectFour;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by macnary17 on 11/21/2015.
 */
public class C4HumanPlayer extends GameHumanPlayer implements OnClickListener{

    ConnectFourState newState = new ConnectFourState();

    // These variables will reference widgets that will be modified during play
    private ImageButton    column1Button = null;
    private ImageButton    column2Button = null;
    private ImageButton    column3Button = null;
    private ImageButton    column4Button = null;
    private ImageButton    column5Button = null;
    private ImageButton    column6Button = null;
    private ImageButton    column7Button = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    public C4HumanPlayer(String name) {
        super(name);
    }

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

        if (info instanceof ConnectFourState) {
            newState = (ConnectFourState) info;
            //TODO GET the board please... please...
        }
    }//receiveInfo



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
        if (button == column3Button) {
            C4DropActionCol3 dropActionCol3 = new C4DropActionCol3(this);
            game.sendAction(dropActionCol3);
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

        //Initialize the widget reference member variables
        this.column1Button = (ImageButton)activity.findViewById(R.id.Col1);
        this.column2Button = (ImageButton)activity.findViewById(R.id.Col2);
        this.column3Button = (ImageButton)activity.findViewById(R.id.Col3);
        this.column4Button = (ImageButton)activity.findViewById(R.id.Col4);
        this.column5Button = (ImageButton)activity.findViewById(R.id.Col5);
        this.column6Button = (ImageButton)activity.findViewById(R.id.Col6);
        this.column7Button = (ImageButton)activity.findViewById(R.id.Col7);

        //Listen for button presses
        column1Button.setOnClickListener(this);
        column2Button.setOnClickListener(this);
        column3Button.setOnClickListener(this);
        column4Button.setOnClickListener(this);
        column5Button.setOnClickListener(this);
        column6Button.setOnClickListener(this);
        column7Button.setOnClickListener(this);

    }//setAsGui

}
