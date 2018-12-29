package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static graph.Node.Color.B;
import static graph.Node.Color.G;
import static graph.Node.Color.R;

public class Node implements Cloneable {
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
        color = B;
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
                return R;
            case 1:
                return G;
            case 2:
                return B;
        }
        return B;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Node{" + this.getId() + "} , Color = " + this.getColor() + "\n");
        for (Node n : this.getNeighbors())
            stringBuilder.append("\tNode{" + n.getId() + "} , Color = " + n.getColor() + "\n");
        return stringBuilder.toString();
    }

    public enum Color {
        R(0),
        G(1),
        B(2);

        private int tag;

        Color(int tag) {
            this.tag = tag;
        }
    }

}
