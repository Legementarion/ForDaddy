package com.lego.fordaddy.logic;

import com.lego.fordaddy.R;
import com.lego.fordaddy.activity.PlayActivity;
import com.lego.fordaddy.utils.Node;

/**
 * @author Lego on 04.08.2016.
 */

public class Core {
    private static Core instance;
    private PlayActivity activity;
    private Node dominoTree;
    private Game game;

    public static Core getInstance(PlayActivity activity) {
        return instance == null ? (instance = new Core(activity)) : instance;
    }

    private Core(PlayActivity activity) {
        this.activity = activity;
    }

    public void startGame() {
        game = new Game();
        addToDrawCard(game.tree[activity.domino_array.length - 1], 0);
        activity.domino_array[0].setEnabled(true);
        for (int i = 0; i < 7; i++) {
            addToDrawCard(game.tree[i], ((activity.domino_array.length - 1) - i));
            activity.domino_array[((activity.domino_array.length - 1) - i)].setEnabled(true);
        }

    }

    public void stopGame() {
    }

    public void cancelPick() {
    }

    public void doPick(int value, int value1) {
        int resultValue = value + value1;
        if (resultValue == 12){
            game.pickCard();
        }
    }

    private int getId() {
        return Integer.parseInt(activity.getApplicationContext().getString(R.string.firstPick, 12));
    }

    public void addToDrawCard(Node pickedCard, int picked) {
        StringBuffer buf = new StringBuffer("c" + "_" + pickedCard.type + "_" + pickedCard.value);
        System.out.println(buf.toString() + " i" + picked);
        activity.domino_array[picked].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
    }
}
