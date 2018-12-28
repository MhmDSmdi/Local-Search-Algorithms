package algorithm;

import graph.Chromosome;
import graph.Gen;
import problem.GeneticProblem;

import java.util.ArrayList;

public class GeneticAlgorithm {

    private int maxGenerateNumber, populationNumber;
    private double crossoverRate, mutationRate;

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

    private Chromosome best;
    public void run(GeneticProblem p) {
        bestFitness.clear();
        worstFitness.clear();
        averageFitness.clear();

        Chromosome[] chromosomes = p.getInitialChromosomes(populationNumber);
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

    private Chromosome getBest(Chromosome[] chromosomes, GeneticProblem p) {
        double maxFitness = 0;
        double minFitness = Double.MAX_VALUE;
        double totalFitness = 0;
        Chromosome bestChrom = null;
        for(int i = 0; i < chromosomes.length; i++){
            Chromosome chrom = chromosomes[i];
            double fitness = p.fitness(p.objectiveFunction(chrom));
            if(fitness > maxFitness){
                maxFitness = fitness;
                bestChrom = chrom;
            }
            if(fitness < minFitness){
                minFitness = fitness;
            }
            totalFitness += fitness;
        }
        bestFitness.add(maxFitness);
        worstFitness.add(minFitness);
        averageFitness.add(totalFitness/chromosomes.length);
//        System.out.println("best fitness: " + bestFitness);
//        System.out.println("worst fitness: " + worstFitness);
//        System.out.println("average fitness: " + averageFitness);
        return bestChrom;
    }

    public Chromosome getBest(){
        return best;
    }

    private void generation(Chromosome[] chromosomes, GeneticProblem p) {
        chromosomes = selection(chromosomes, p);
        crossover(chromosomes);
        mutation(chromosomes);
    }

    private Chromosome[] selection(Chromosome[] chromosomes, GeneticProblem p) {
        double fitness[] = new double[chromosomes.length];
        double totalFitness = 0;
        for (int i = 0; i < fitness.length; i++) {
            fitness[i] = p.fitness(p.objectiveFunction(chromosomes[i]));
            totalFitness += fitness[i];
        }
        double probabilities[] = new double[fitness.length];
        for (int i = 0; i < fitness.length; i++) {
            probabilities[i] = fitness[i] / totalFitness;
        }

        double cumulativeProbabilities[] = new double[probabilities.length];
        cumulativeProbabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length - 1; i++) {
            cumulativeProbabilities[i] = cumulativeProbabilities[i - 1] + probabilities[i];
        }
        cumulativeProbabilities[cumulativeProbabilities.length - 1] = 1.0;

        Chromosome newChromosomes[] = new Chromosome[chromosomes.length];

        for (int i = 0; i < chromosomes.length; i++) {
            double rand = Math.random();
            for (int j = 0; j < cumulativeProbabilities.length; j++) {
                if (rand < cumulativeProbabilities[j]) {
                    newChromosomes[i] = chromosomes[j];
                    break;
                }
            }
        }

        return newChromosomes;
    }

    private void crossover(Chromosome[] chromosomes) {
        ArrayList<Chromosome> parents = new ArrayList<>();
        ArrayList<Integer> parentsIndexes = new ArrayList<>();
        for (int i = 0; i < chromosomes.length; i++) {
            if (Math.random() < crossoverRate) {
                parents.add(chromosomes[i]);
                parentsIndexes.add(i);
            }
        }

        for (int i = 0; i < parents.size(); i++) {
            chromosomes[parentsIndexes.get(i)] = crossover(parents.get(i), parents.get((i + 1) % parents.size()));
        }
    }

    private Chromosome crossover(Chromosome c1, Chromosome c2) {
        int length = c1.gens.length;
        Gen[] newGens = new Gen[length];
        int crossoverPos = (int) (Math.random() * (length - 1)) + 1;
        for (int i = 0; i < length; i++) {
            if (i < crossoverPos) newGens[i] = c1.gens[i];
            else newGens[i] = c2.gens[i];
        }
        return new Chromosome(newGens).clone();
    }

    private void mutation(Chromosome[] chromosomes) {
        int totalGen = chromosomes.length * chromosomes[0].gens.length;
        int mutationNum = (int) (totalGen * mutationRate);
        for (int i = 0; i < mutationNum; i++) {
            int randGenPos = (int) (Math.random() * totalGen);
            chromosomes[randGenPos % chromosomes.length].gens[randGenPos / chromosomes.length].mutation();
        }
    }

//    public void drawDiagram(){
//        double xData [] = new double[bestFitness.size()];
//        for(int i = 0; i < xData.length; i++)
//            xData[i] = i+1;
//        double[] bestFitnessArray = new double[xData.length],
//                worstFitnessArray = new double[xData.length],
//                averageFitnessArray = new double[xData.length];
//        for(int i = 0; i < xData.length; i++){
//            bestFitnessArray[i] = bestFitness.get(i);
//            worstFitnessArray[i] = worstFitness.get(i);
//            averageFitnessArray[i] = averageFitness.get(i);
//        }
//        new SwingWrapper(QuickChart.getChart("Best Fitness", "n", "fitness", "best fitness", xData, bestFitnessArray)).displayChart();
//        new SwingWrapper(QuickChart.getChart("Worst Fitness", "n", "fitness", "worst fitness", xData, worstFitnessArray)).displayChart();
//        new SwingWrapper(QuickChart.getChart("Average Fitness", "n", "fitness", "average fitness", xData, averageFitnessArray)).displayChart();
//    }
}
