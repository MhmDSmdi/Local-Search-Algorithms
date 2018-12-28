package problem;


import graph.Chromosome;

public abstract class GeneticProblem {

    public abstract Chromosome[] getInitialChromosomes(int number);

    public abstract double objectiveFunction(Chromosome c);

    public abstract double fitness(double objectiveFunction);
}
