package edu.up.cs301.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by binghami17 on 11/25/2015.
 */
public class GameBoardPiece {
    private int x;
    private int y;

    public GameBoardPiece(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    public void paint(Canvas g){
        Paint boardPiece = new Paint();
        boardPiece.setColor(Color.BLUE);
        g.drawRect(this.x, this.y, 100, 100, boardPiece);

        Paint clear = new Paint(Paint.ANTI_ALIAS_FLAG);
        clear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        g.drawCircle(this.x+50, this.y+50, 40, clear);
        //Paint clear = new Paint(android.R.color.transparent);
    }

}
