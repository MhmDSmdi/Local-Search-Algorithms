package algorithm;

import graph.Chromosome;
import graph.Gen;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import problem.GeneticProblem;

import java.util.ArrayList;

public class GeneticAlgorithm {

    private int maxGenerateNumber, populationNumber;
    private double crossoverRate, mutationRate;
    private Chromosome best;
    private ArrayList<Double> bestFitness, worstFitness, averageFitness;

    public GeneticAlgorithm(int maxGenerateNumber, int populationNumber, double crossoverRate, double mutationRate) {
        this.maxGenerateNumber = maxGenerateNumber;
        this.populationNumber = populationNumber;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        bestFitness = new ArrayList<>();
        worstFitness = new ArrayList<>();
        averageFitness = new ArrayList<>();
    }

    public void run(GeneticProblem p) {
        bestFitness.clear();
        worstFitness.clear();
        averageFitness.clear();

        ArrayList<Chromosome> chromosomes = p.getInitialChromosomes(populationNumber);
        int i;
        for (i = 0; i < maxGenerateNumber; i++) {
            generation(chromosomes, p);
            best = getBest(chromosomes, p);
            if(p.objectiveFunction(best) == 0){
                break;
            }
        }
        System.out.println("generation number: " + i);
        System.out.println(p.objectiveFunction(best));
    }

    private Chromosome getBest(ArrayList<Chromosome> chromosomes, GeneticProblem p) {
        double maxFitness = 0;
        double minFitness = Double.MAX_VALUE;
        double totalFitness = 0;
        Chromosome bestChrom = null;
        for (Chromosome chrom : chromosomes) {
            double fitness = p.fitness(p.objectiveFunction(chrom));
            if (fitness > maxFitness) {
                maxFitness = fitness;
                bestChrom = chrom;
            }
            if (fitness < minFitness) {
                minFitness = fitness;
            }
            totalFitness += fitness;
        }
        bestFitness.add(maxFitness);
        worstFitness.add(minFitness);
        averageFitness.add(totalFitness/chromosomes.size());
        return bestChrom;
    }

    public Chromosome getBest(){
        return best;
    }

    private void generation(ArrayList<Chromosome> chromosomes, GeneticProblem p) {
        chromosomes = selection(chromosomes, p);
        crossover(chromosomes);
        mutation(chromosomes);
    }

    private  ArrayList<Chromosome> selection(ArrayList<Chromosome> chromosomes, GeneticProblem p) {
        double totalFitness = 0;
        double fitness[] = new double[chromosomes.size()];
        double probabilities[] = new double[fitness.length];
        double cumulativeProbabilities[] = new double[probabilities.length];
        for (int i = 0; i < fitness.length; i++) {
            fitness[i] = p.fitness(p.objectiveFunction(chromosomes.get(i)));
            totalFitness += fitness[i];
        }

        for (int i = 0; i < fitness.length; i++) {
            probabilities[i] = fitness[i] / totalFitness;
        }

        cumulativeProbabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length - 1; i++) {
            cumulativeProbabilities[i] = cumulativeProbabilities[i - 1] + probabilities[i];
        }
        cumulativeProbabilities[cumulativeProbabilities.length - 1] = 1.0;

        ArrayList<Chromosome> newChromosomes = new ArrayList<>(chromosomes.size());

        for (int i = 0; i < chromosomes.size(); i++) {
            double rand = Math.random();
            for (int j = 0; j < cumulativeProbabilities.length; j++) {
                if (rand < cumulativeProbabilities[j]) {
                    newChromosomes.add(i, chromosomes.get(j));
                    break;
                }
            }
        }
        return newChromosomes;
    }

    private void crossover(ArrayList<Chromosome> chromosomes) {
        ArrayList<Chromosome> parents = new ArrayList<>();
        ArrayList<Integer> parentsIndexes = new ArrayList<>();
        for (int i = 0; i < chromosomes.size(); i++) {
            if (Math.random() < crossoverRate) {
                parents.add(chromosomes.get(i));
                parentsIndexes.add(i);
            }
        }

        for (int i = 0; i < parents.size(); i++) {
            chromosomes.add(parentsIndexes.get(i), crossover(parents.get(i), parents.get((i + 1) % parents.size())));
        }
    }

    private Chromosome crossover(Chromosome c1, Chromosome c2) {
        int length = c1.getGens().size();
        ArrayList<Gen> newGens = new ArrayList<>(length);
        int crossoverPos = (int) (Math.random() * (length - 1)) + 1;
        for (int i = 0; i < length; i++) {
            if (i < crossoverPos)
                newGens.add(c1.getGens().get(i));
            else
                newGens.add(c2.getGens().get(i));
        }
        return new Chromosome(newGens).clone();
    }

    private void mutation(ArrayList<Chromosome> chromosomes) {
        int totalGen = chromosomes.size() * chromosomes.get(0).getGens().size();
        int mutationNum = (int) (totalGen * mutationRate);
        for (int i = 0; i < mutationNum; i++) {
            int randGenPos = (int) (Math.random() * totalGen);
            chromosomes.get(randGenPos % chromosomes.size()).getGens().get(randGenPos / chromosomes.size()).mutation();
        }
    }

    public void drawDiagram(){
        double xData [] = new double[bestFitness.size()];
        for(int i = 0; i < xData.length; i++)
            xData[i] = i+1;
        double[] bestFitnessArray = new double[xData.length],
                worstFitnessArray = new double[xData.length],
                averageFitnessArray = new double[xData.length];
        for(int i = 0; i < xData.length; i++){
            bestFitnessArray[i] = bestFitness.get(i);
            worstFitnessArray[i] = worstFitness.get(i);
            averageFitnessArray[i] = averageFitness.get(i);
        }
        new SwingWrapper(QuickChart.getChart("Best Fitness", "n", "fitness", "best fitness", xData, bestFitnessArray)).displayChart();
        new SwingWrapper(QuickChart.getChart("Worst Fitness", "n", "fitness", "worst fitness", xData, worstFitnessArray)).displayChart();
        new SwingWrapper(QuickChart.getChart("Average Fitness", "n", "fitness", "average fitness", xData, averageFitnessArray)).displayChart();
    }
}
