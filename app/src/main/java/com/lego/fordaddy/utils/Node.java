package com.lego.fordaddy.utils;

/**
 * @author Lego on 11.08.2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private boolean live;
    public int value;
    public Types type;
    private final List<Node> children = new ArrayList<>();
    private final Node mother;
    private final Node father;

    public Node(Node mother, Node father){
        this.mother = mother;
        this.father = father;
        setLive(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getChildren() {
        return children;
    }
    public List<Node> getParent() {
        final List<Node> parent = new ArrayList<>();
        parent.add(mother);
        parent.add(father);
        return parent;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "type = " + type + " | value = " + value;
    }
}
