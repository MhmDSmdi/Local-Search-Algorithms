package problem;

import graph.Chromosome;
import graph.Gen;

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


    class ColoringGen extends Gen {

        @Override
        public String toString() {
            return null;
        }

        @Override
        public Gen clone() {
            return null;
        }

        @Override
        public Gen mutation() {
            return null;
        }
    }
}
