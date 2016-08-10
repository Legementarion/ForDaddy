package com.lego.fordaddy.logic;

import com.lego.fordaddy.R;
import com.lego.fordaddy.activity.PlayActivity;

/**
 * @author Lego on 04.08.2016.
 */

public class Core {
    private static Core instance;
    private PlayActivity activity;

    public static Core getInstance(PlayActivity activity) {
        return instance == null ? (instance = new Core(activity)) : instance;
    }

    private Core(PlayActivity activity) {
        this.activity = activity;
    }

    public void startGame() {
        Game game = new Game();
        for (int i = 0; i < 7; i++) {
            addToDrawCard(game.cards[i], ((activity.domino_array.length - 1) - i));
            activity.domino_array[((activity.domino_array.length - 1) - i)].setEnabled(true);
        }
    }

    public void stopGame() {
    }

    public void cancelPick() {
    }

    public void doPick() {
//        ImageView imageView = new ImageView(activity.getApplicationContext());
//        ImageView.setId(getId());
//        ImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//
//        linearLayout.addView(imageView);
    }

    private int getId() {
        return Integer.parseInt(activity.getApplicationContext().getString(R.string.firstPick, 12));
    }

    public void addToDrawCard(Game.Card pickedCard, int picked) {
        StringBuffer buf = new StringBuffer("logo");
//                new StringBuffer("c" + pickedCard.type + "_" + pickedCard.value);
        activity.domino_array[picked].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
    }
}
