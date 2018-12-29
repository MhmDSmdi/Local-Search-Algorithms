package problem;

import graph.Chromosome;
import graph.Gen;
import graph.Node;

import java.util.ArrayList;
import java.util.Random;

import static problem.GraphColoringLocalSearch.edges;

public class GraphColoringGenetic extends GeneticProblem {

    @Override
    public ArrayList<Chromosome> getInitialChromosomes(int number) {
        ArrayList<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < number; i++)
            chromosomes.add(new Chromosome(getRandomChromosomes()));
        return chromosomes;
    }

    @Override
    public double objectiveFunction(Chromosome c) {
        float difference = 0;
        int same = 0;

        for (Gen gen : c.getGens()) {
            Node node = ((ColoringGen)gen).node;
            for (Node n : node.getNeighbors()) {
                if (!n.getColor().equals(node.getColor())) {
                    difference++;
                } else {
                    same++;
                }
            }
        }
        float result = difference / (2 * edges) * 100;
        System.out.println(c + "\n#Has result = " + result + "  SameColor = " + same/2 + " DefferenceColor = " + difference / 2);
        return result;
    }

    @Override
    public double fitness(double objectiveFunction) {
        return 1.0 / (1 + objectiveFunction);
    }

    private ArrayList<Gen> getRandomChromosomes() {
        ArrayList<Gen> coloringGens = new ArrayList<>();
        Node s1 = new Node(1);
        Node s2 = new Node(2);
        Node s3 = new Node(3);
        Node s4 = new Node(4);
        Node s5 = new Node(5);
        Node s6 = new Node(6);
        Node s7 = new Node(7);
        Node s8 = new Node(8);
        Node s9 = new Node(9);
        Node s10 = new Node(10);
        Node s11 = new Node(11);
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
        coloringGens.add(new ColoringGen(s1));
        coloringGens.add(new ColoringGen(s2));
        coloringGens.add(new ColoringGen(s3));
        coloringGens.add(new ColoringGen(s4));
        coloringGens.add(new ColoringGen(s5));
        coloringGens.add(new ColoringGen(s6));
        coloringGens.add(new ColoringGen(s7));
        coloringGens.add(new ColoringGen(s8));
        coloringGens.add(new ColoringGen(s9));
        coloringGens.add(new ColoringGen(s10));
        coloringGens.add(new ColoringGen(s11));
        return coloringGens;
    }


    public class ColoringGen extends Gen {

        public Node node;

        public ColoringGen(Node node) {
            this.node = node;
            int rndColor = new Random().nextInt(3);
            switch (rndColor) {
                case 0:
                    node.setColor(Node.Color.R);
                    break;
                case 1:
                    node.setColor(Node.Color.G);
                    break;
                case 2:
                    node.setColor(Node.Color.B);
                    break;
            }
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

        @Override
        public String toString() {
            return node.toString();
        }
    }
}
