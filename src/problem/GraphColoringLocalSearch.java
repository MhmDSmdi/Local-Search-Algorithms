package problem;

import graph.Node;
import graph.State;

import java.util.ArrayList;
import java.util.Random;

public class GraphColoringLocalSearch extends Problem {

    private static final int edges = 20;

    public GraphColoringLocalSearch() {
        initialState = new GraphState(createState());
    }

    @Override
    public double objectiveFunction(State s) {
        float difference = 0;
        int same = 0;
        for (Node node : ((GraphState)s).getNodes()) {
            for (Node n : node.getNeighbors()) {
                if (!n.getColor().equals(node.getColor())) {
                    difference++;
//                    System.out.println(difference);
                } else {
                    same++;
                }
            }
        }

        float result = difference / (2 * edges) * 100;
        System.out.println(s + "#Has result = " + result + "  SameColor = " + same/2 + " DefferenceColor = " + difference / 2);
        s.score = result;
        return result;
    }

    @Override
    public State getRandomState() {
        return null;
    }

    public static ArrayList<Node> createState() {
        ArrayList<Node> nodeArrayList = new ArrayList<>();
        Node s1 = new Node(1);
        nodeArrayList.add(s1);
        Node s2 = new Node(2);
        nodeArrayList.add(s2);
        Node s3 = new Node(3);
        nodeArrayList.add(s3);
        Node s4 = new Node(4);
        nodeArrayList.add(s4);
        Node s5 = new Node(5);
        nodeArrayList.add(s5);
        Node s6 = new Node(6);
        nodeArrayList.add(s6);
        Node s7 = new Node(7);
        nodeArrayList.add(s7);
        Node s8 = new Node(8);
        nodeArrayList.add(s8);
        Node s9 = new Node(9);
        nodeArrayList.add(s9);
        Node s10 = new Node(10);
        nodeArrayList.add(s10);
        Node s11 = new Node(11);
        nodeArrayList.add(s11);

        s1.addNeighbor(s2, s3, s10, s11);
        s2.addNeighbor(s4, s5, s1);
        s3.addNeighbor(s5, s6, s1);
        s4.addNeighbor(s6, s11, s2, s7);
        s5.addNeighbor(s2, s3, s7, s8, s9);
        s6.addNeighbor(s4, s3, s8, s10);
        s7.addNeighbor(s4, s5, s10);
        s8.addNeighbor(s6, s5, s11);
        s9.addNeighbor(s5, s10, s11);
        s10.addNeighbor(s7, s9, s1, s6);
        s11.addNeighbor(s8, s9, s1, s4);
        return nodeArrayList;
    }

    public static ArrayList<Node> createState(ArrayList<Node> list) {
        ArrayList<Node> nodes = createState();
        for (int i =0 ; i < list.size() ; i++) {
            nodes.get(i).setColor(list.get(i).getColor());
            for (int j = 0; j < list.get(i).getNeighbors().size(); j++) {
                nodes.get(i).getNeighbors().get(j).setColor(list.get(i).getNeighbors().get(j).getColor());
            }
        }
        return nodes;
    }


    class GraphState extends State implements Cloneable {

        private ArrayList<Node> nodes;
        public GraphState(ArrayList<Node> nodes) {
            this.nodes = nodes;
        }

        @Override
        public ArrayList<State> getNeighbors() {
            ArrayList<State> neighbors = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                for (Node.Color color : Node.Color.values()) {
                    ArrayList<Node> newNodes = new ArrayList<>();
                    newNodes = GraphColoringLocalSearch.createState(nodes);
                    Node node = newNodes.get(i);
                    if (!node.getColor().equals(color)) {
                        node.setColor(color);
                        GraphState g = new GraphState(newNodes);
                        neighbors.add(g);
                    }
                }
            }
            return neighbors;
        }

        @Override
        public State getRandomNeighbor() {
            int rndIndex = new Random().nextInt(nodes.size());
            Node node = nodes.get(rndIndex);
            node.setColor(node.getColorExcept(node.getColor()));
            return new GraphState(nodes);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
//            stringBuilder.append("[");
            for (Node node : nodes) {
               stringBuilder.append(node);
//               stringBuilder.append(node.getColor());
            }
//            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        public ArrayList<Node> getNodes() {
            return nodes;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }
}
