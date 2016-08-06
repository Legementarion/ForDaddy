package com.lego.fordaddy.logic;

/**
 * @author Lego on 04.08.2016.
 */

public class Core {
    private static Core instance;


    public static Core getInstance() {
        return instance == null ? (instance = new Core()) : instance;
    }

    public void startGame(){
        Game game = new Game();
    }
    public void stopGame(){}
}
