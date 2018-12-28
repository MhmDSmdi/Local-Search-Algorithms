package problem;

import graph.Node;
import graph.State;

import java.util.ArrayList;
import java.util.Random;

public class GraphColoring extends Problem {

    public GraphColoring() {

    }

    @Override
    public double objectiveFunction(State s) {
        return 0;
    }

    @Override
    public State getRandomState() {
        return null;
    }


    class GraphState extends State {

        private ArrayList<Node> nodes;

        public GraphState(ArrayList<Node> nodes) {
            this.nodes = nodes;
        }

        @Override
        public ArrayList<State> getNeighbors() {
            ArrayList<State> neighbors = new ArrayList<>();
            ArrayList<Node> newNodes = (ArrayList<Node>) nodes.clone();
            for (Node node : newNodes) {
                for (Node.Color color : Node.Color.values()) {
                    if (!node.getColor().equals(color)) {
                        neighbors.add(new GraphState(newNodes));
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
            for (Node node : nodes)
                stringBuilder.append("Node{ " + node.getId() + "} , Color = " + node.getColor() + "\n");
            return stringBuilder.toString();
        }
    }
}
