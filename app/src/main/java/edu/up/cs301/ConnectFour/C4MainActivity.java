package edu.up.cs301.ConnectFour;

/**
 * Created by macnary17 on 11/30/2015.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.ArrayList;

import edu.up.cs301.animation.Animation;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.ProxyPlayer;
import edu.up.cs301.game.R;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;


public class C4MainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    private Animation animation;
    Context thisContext;


    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     *
     * @return
     * 		the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // C4 has two player types:  human and computer. The computer
        //player can either be dumb or smart
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new C4HumanPlayer(name);
            }});
//        playerTypes.add(new GamePlayerType("Network Player's Player") {
//            public GamePlayer createPlayer(String name) {
//                //int portNum = getPortNum();
//                //return new C4NetworkPlayer(String name);
//                return new C4NetworkPlayer(name);
//            }});

        //GamePlayerType easy = new GamePlayerType("Computer Player (easy)");

        playerTypes.add(new GamePlayerType("Computer Player (easy)") {
            public GamePlayer createPlayer(String name) {
                C4ComputerPlayerEasy easy = new C4ComputerPlayerEasy(name);
                //easy.setActivity(C4MainActivity.this);
                MediaPlayer player = MediaPlayer.create(C4MainActivity.this, R.raw.piece_set);
                player.setLooping(false);
                easy.setPlayer(player);
                return easy;
            }});
        playerTypes.add(new GamePlayerType("Computer Player (medium)") {
            public GamePlayer createPlayer(String name) {
                //C4ComputerPlayerMedium medium = new C4ComputerPlayerEasy("Computer Player (medium)");
                //medium.setActivity(C4MainActivity.this);
                return new C4ComputerPlayerMedium(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player (hard)") {
            public GamePlayer createPlayer(String name) {
                return new C4ComputerPlayerHard(name);
            }});

        // Create a game configuration class for Counter:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Connect Four", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Player", "", 0);

        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game
     */
    @Override
    public LocalGame createLocalGame() {
        C4LocalGame meh = new C4LocalGame();
        meh.getContext(thisContext);
        return meh;
    }

}
