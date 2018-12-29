package problem;


import graph.Chromosome;

import java.util.ArrayList;

public abstract class GeneticProblem {

    public abstract ArrayList<Chromosome> getInitialChromosomes(int number);

    public abstract double objectiveFunction(Chromosome c);

    public abstract double fitness(double objectiveFunction);
}
