package edu.up.cs301.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import edu.up.cs301.ConnectFour.ConnectFourState;
import edu.up.cs301.ConnectFour.Piece;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;

/**
 * Created by binghami17 on 11/23/2015.
 */

public class Animation extends GameMainActivity implements Animator {

    public int interval() {
        return 25;
    }

    public int backgroundColor() {
        return Color.rgb(255, 255, 255);
    }

    public boolean doPause() {
        return false;
    }

    public boolean doQuit() {
        return false;
    }

    public void tick(Canvas canvas) {
        Canvas g = new Canvas();
        Piece myPiece = new Piece(0,0,1);
        myPiece.paintPiece(g);

    }

    public void onTouch(MotionEvent event) {

    }

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame() {
        return null;
    }
}