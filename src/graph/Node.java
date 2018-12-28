package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static graph.Node.Color.BLUE;
import static graph.Node.Color.GREEN;
import static graph.Node.Color.RED;

public class Node {
    private int id;
    private ArrayList<Node> neighbors;
    private Color color;

    public Node(int id, Color color) {
        neighbors = new ArrayList<>();
        this.id = id;
        this.neighbors = neighbors;
        this.color = color;
    }

    public Node(int id) {
        neighbors = new ArrayList<>();
        this.id = id;
        color = BLUE;
    }

    public void addNeighbor(Node ... nodes) {
        neighbors.addAll(Arrays.asList(nodes));
    }

    public int getId() {
        return id;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public Color getColor() {
        return color;
    }

    public void setNeighbors(ArrayList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorExcept(Color color) {
        Random rnd = new Random();
        int nTag = rnd.nextInt(3);
        while (color.tag == nTag)
            nTag = rnd.nextInt(3);
        switch (nTag) {
            case 0:
                return RED;
            case 1:
                return GREEN;
            case 2:
                return BLUE;
        }
        return BLUE;
    }

    public enum Color {
        RED(0),
        GREEN(1),
        BLUE(2);

        private int tag;

        Color(int tag) {
            this.tag = tag;
        }
    }

}
