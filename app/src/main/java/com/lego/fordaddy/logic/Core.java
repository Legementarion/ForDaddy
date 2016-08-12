package com.lego.fordaddy.logic;

import android.util.SparseArray;

import com.lego.fordaddy.R;
import com.lego.fordaddy.activity.PlayActivity;
import com.lego.fordaddy.utils.Node;

import java.util.HashMap;

/**
 * @author Lego on 04.08.2016.
 */

public class Core {
    private static Core instance;
    private PlayActivity activity;
    private SparseArray<Node> dominoTree = new SparseArray<>();
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
        for (int i = 0; i < activity.domino_array.length; i++) {
            dominoTree.put(activity.domino_array[activity.domino_array.length - i - 1].getId(), game.tree[i]);
        }

    }

    public void stopGame() {
    }

    public void cancelPick() {
    }

    public void doPick(int value, int value1) {
        int resultValue = dominoTree.get(value).value +
                dominoTree.get(value).type.getIDByType() +
                dominoTree.get(value1).type.getIDByType() +
                dominoTree.get(value1).value;
        if (resultValue == 12) {
            aDraw();
//            dominoTree.remove(value);
//            dominoTree.remove(value1);
        }
    }

    public void addToDrawCard(Node pickedCard, int picked) {
        StringBuilder buf = new StringBuilder("c" + "_" + pickedCard.type + "_" + pickedCard.value);
        System.out.println(buf.toString() + " i" + picked);
        activity.domino_array[picked].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
    }

    public void aDraw() {
        for (int i = 0; i < activity.domino_array.length; i++) {
            if (dominoTree.keyAt(i) == activity.domino_array[i].getId()) {
                if (dominoTree.get(activity.domino_array[i].getId()).isLive()) {
                    StringBuilder buf = new StringBuilder("c" + "_" + dominoTree.get(activity.domino_array[i].getId())
                            .type + "_" + dominoTree.get(activity.domino_array[i].getId()).value);
                    activity.domino_array[i].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
                }
            }
        }
    }
}
