package com.lego.fordaddy.logic;

import android.util.SparseArray;
import android.view.View;

import com.lego.fordaddy.activity.PlayActivity;
import com.lego.fordaddy.utils.Node;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Lego on 04.08.2016.
 */

public class Core {
    private static Core instance;
    private PlayActivity activity;
    private SparseArray<Node> dominoTree = new SparseArray<>();
    private SparseArray<Node> dominoRetreat = new SparseArray<>();
    private Game game;

    public static Core getInstance(PlayActivity activity) {
        return instance == null ? (instance = new Core(activity)) : instance;
    }

    private Core(PlayActivity activity) {
        this.activity = activity;
    }

    public void startGame() {
        game = new Game();
        for (int i = 0; i < activity.domino_array.length; i++) {
            dominoTree.put(activity.domino_array[activity.domino_array.length - i - 1].getId(), game.tree[i]);
        }
        aDraw();
//        game.printTree();
    }

    public void stopGame() {
        game = null;
        dominoTree = new SparseArray<>();
        dominoRetreat = new SparseArray<>();
        for (int i = 0; i < activity.domino_array.length; i++) {
            StringBuilder buf = new StringBuilder("title");
            activity.domino_array[i].setEnabled(false);
            activity.domino_array[i].setVisibility(View.VISIBLE);
            activity.domino_array[i].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
        }

    }

    public void doPick(int value, int value1) {
        int resultValue = dominoTree.get(value).value +
                dominoTree.get(value).type.getIDByType() +
                dominoTree.get(value1).type.getIDByType() +
                dominoTree.get(value1).value;
        if (resultValue == 12) {
            dominoTree.get(value).setVisible(false);
            dominoTree.get(value1).setVisible(false);
            removeRelation(dominoTree.get(value));
            removeRelation(dominoTree.get(value1));
            aDraw();
        }
    }

    private void aDraw() {
        for (int i = 0; i < activity.domino_array.length; i++) {
            if (dominoTree.keyAt(i) == activity.domino_array[i].getId()) {
                if (dominoTree.get(activity.domino_array[i].getId()).isLive()) {
                    if (dominoTree.get(activity.domino_array[i].getId()).isVisible()) {
                        StringBuilder buf = new StringBuilder("c" + "_" + dominoTree.get(activity.domino_array[i].getId())
                                .type + "_" + dominoTree.get(activity.domino_array[i].getId()).value);
                        activity.domino_array[i].setImageResource(activity.getResources().getIdentifier(buf.toString(), "drawable", activity.getPackageName()));
                        activity.domino_array[i].setEnabled(true);
                        activity.domino_array[i].setVisibility(View.VISIBLE);
                    } else {
                        activity.domino_array[i].setVisibility(View.INVISIBLE);
                        activity.domino_array[i].setEnabled(false);
                    }
                }
            }
        }
    }

    private void removeRelation(Node root) {
        List<Node> tempList;
        //parent
        tempList = root.getParent();
        if (tempList != null) {
            for (int i = 0; i < tempList.size(); i++) {
                for (int j = 0; j < dominoTree.size(); j++) {
                    int key;
                    key = dominoTree.keyAt(j);
                    if (dominoTree.get(key) == (tempList.get(i))) {
//                        for (int k =0; k<dominoTree.get(key).getChildren().size(); k++)
//                            if (dominoTree.get(key).getChildren().get(k) == root){
                        dominoTree.get(key).getChildren().remove(root);
//                            }
                    }
                }
            }
        }

        //children
        tempList = root.getChildren();
        if (tempList != null) {
            for (int i = 0; i < tempList.size(); i++) {
                for (int j = 0; j < dominoTree.size(); j++) {
                    int key;
                    key = dominoTree.keyAt(j);
                    if (dominoTree.get(key) == (tempList.get(i))) {
//                        for (int k =0; k<dominoTree.get(key).getChildren().size(); k++)
//                            if (dominoTree.get(key).getChildren().get(k) == root){
                        dominoTree.get(key).getParent().remove(root);
//                            }
                    }
                }
            }

        }
    }
}
