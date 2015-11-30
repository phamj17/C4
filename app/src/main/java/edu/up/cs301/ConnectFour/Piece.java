package edu.up.cs301.ConnectFour;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by macnary17 on 11/6/2015.
 */
public class Piece {
    public int xPosition;
    public int yPosition;
    public int velY;
    public int twist1;
    public int turn;
    public int maxY; //used to tell where the piece should end up
    //public int color;

    public Piece(int xPos, int yPos, int initTurn, int initMax){
        this.xPosition = xPos;
        this.yPosition = yPos;
        //this.velY = initVelY;
        //this.twist1 = initTwist;
        this.turn = initTurn;
        this.maxY = initMax;

    }

    /**
     * Method: paintCannonBall
     * sets the color for the cannonball and then paints it according to the xPosition and yPosition variables
     */
    public void paintPiece(Canvas g){
        Paint color = new Paint();
        if (turn == 0)
        {
            color.setColor(Color.RED);
        }
        else
        {
            color.setColor(Color.BLACK);
        }
        g.drawCircle(xPosition, yPosition, 40, color);
    }

    //getter method for xPosition
    public int getXPosition(){
        return xPosition;
    }

    //getter for yPosition
    public int getYPosition(){
        return yPosition;
    }

    //getter for velY
    public int getYVel(){
        return this.velY;
    }

    /**
     * move
     *
     * causes the ball's position to change based upon velocity.  The ball is
     * presumed to be moving in a rectangular area defined by the x/y axes and a
     * given width and height.
     *
     */
    /*public void move(int width, int height, int initVelY, int count){

        //calculate the xPosition and yPosition and y velocity
        xPosition = (width + (2*twist1)) + (count * 35);
        yPosition = yPosition - initVelY;
        velY = initVelY - 5;

        //if the cannon ball hits the ground, bounce back up
        if(yPosition >= height - 40){
            yPosition = height - 40;
            velY = 35;
        }

        //if the cannon ball hits the edge of the screen, stop moving completely
        if(xPosition < 40){
            xPosition = 40;
            velY = -25;
        }


    }*/

}