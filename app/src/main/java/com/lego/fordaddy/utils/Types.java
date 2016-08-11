package com.lego.fordaddy.utils;


/**
 * @author Lego on 11.08.2016.
 */

public enum Types {
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