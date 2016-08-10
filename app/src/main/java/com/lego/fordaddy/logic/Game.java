package com.lego.fordaddy.logic;

import java.util.Random;

/**
 * @author Lego on 04.08.2016.
 */

public class Game {
    private static final int DECK_SIZE = 28;

    public Game() {
        initGameDeck(DECK_SIZE);
    }

    public static class Card {
        int value;
        Types type;

        Card(int value, Types type) {
            this.value = value;
            this.type = type;
        }

        @Override
        public String toString() {
            return "type = " + type + " | value = " + value;
        }
    }

    public Card[] cards;


    private void initGameDeck(int deckSize) {
        cards = new Card[deckSize];
        int types = 6;
        for (int i = 0, val = 0, type = 0; i < deckSize; i++, val++) {
            cards[i] = new Card(val, Types.getTypeByID(type));
            System.out.println(cards[i].toString() + " i -" + i);
            if (val == types) {
                val = 0;
                type++;
                types--;
            }
        }
        shufle();
    }

    public Card pickCard() {
        int index = new Random().nextInt(cards.length);
        Card picked = cards[index];
        System.arraycopy(cards, index + 1, cards, index, cards.length - 1 - index);
        return picked;
    }

    private void shufle() {
        Random rnd = new Random();
        for (int i = 0; i < cards.length - 1; i++) {
            int index = rnd.nextInt(cards.length);
            Card buf = cards[index];
            cards[index] = cards[i];
            cards[i] = buf;
        }
    }

    private enum Types {
        Zero("0"),
        One("1"),
        Two("2"),
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6");

        private String id;

        Types(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }

        public static Types getTypeByID(int id) {
            for (Types type : values()) {
                if (type.toString().equals(String.valueOf(id))) {
                    return type;
                }
            }
            return null;
        }
    }
}
