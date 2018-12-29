package problem;

import graph.Chromosome;
import graph.Gen;
import graph.Node;

public class GraphColoringGenetic extends GeneticProblem {

    @Override
    public Chromosome[] getInitialChromosomes(int number) {
        return new Chromosome[0];
    }

    @Override
    public double objectiveFunction(Chromosome c) {
        return 0;
    }

    @Override
    public double fitness(double objectiveFunction) {
        return 0;
    }


    public class ColoringGen extends Gen {
        public Node node;

        public ColoringGen(Node node) {
            this.node = node;
        }

        @Override
        public Gen clone() {
            Node newNode = new Node(node.getId());
            ColoringGen clone = new ColoringGen(newNode);
            for (int i = 0; i < node.getNeighbors().size(); i++) {
                clone.node.getNeighbors().add(new Node(node.getNeighbors().get(i).getId(), node.getNeighbors().get(i).getColor()));
            }
            return clone;
        }

        @Override
        public Gen mutation() {
            node.setColor(node.getColorExcept(node.getColor()));
            return this;
        }
    }
}
