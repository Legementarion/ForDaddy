package com.lego.fordaddy.logic;

import com.lego.fordaddy.utils.Node;
import com.lego.fordaddy.utils.Types;

import java.util.Random;

/**
 * @author Lego on 04.08.2016.
 */

public class Game {
    private static final int DECK_SIZE = 28;
    int[] value;
    Types[] types;

    public Game() {
        initGameDeck(DECK_SIZE);
    }

    public Node[] tree;


    private void initGameDeck(int deckSize) {
        tree = new Node[deckSize];
        value = new int[deckSize];
        types = new Types[deckSize];

        for (int i = 0, j = 6, type = 0; i < deckSize; j--, i++) {
            value[i] = j;
            types[i] = Types.getTypeByID(type);
            if (j == type) {
                type++;
                j = 6;
            }
        }
        value[deckSize-1] = 6;

        shufle();

        for (int i = 0, res = 13, row = 7; i < deckSize; i++) {
            if (i < 7) {
                tree[i] = new Node(null, null);
                tree[i].setId("" + i);
                tree[i].value = value[i];
                tree[i].type = types[i];
            } else {
                tree[i] = new Node(tree[i - row], tree[i - row + 1]);
                tree[i - row].getChildren().add(tree[i]);
                tree[i - row + 1].getChildren().add(tree[i]);
                tree[i].setId("" + i);
                tree[i].value = value[i];
                tree[i].type = types[i];
                if (i == res) {
                    row--;
                    res += row;
                }
            }
            System.out.println(tree[i].getId());
            System.out.println(tree[i].value);
            System.out.println(tree[i].type);
        }

//        printTree(tree[deckSize - 1], " ");

    }

    public Node pickCard() {

        return null;
    }

    private void shufle() {
        Random rnd = new Random();
        for (int i = 0; i < value.length - 1; i++) {
            int index = rnd.nextInt(value.length);
            int buf = value[index];
            Types temp = types[index];
            value[index] = value[i];
            types[index] = types[i];
            value[i] = buf;
            types[i] = temp;
        }
    }

    private static void printTree(Node node, String appender) {
        if (node == null) {
            return;
        }
        System.out.println(appender + node.getId());
        for (Node each : node.getParent()) {
            printTree(each, appender + appender);
        }
    }
}
