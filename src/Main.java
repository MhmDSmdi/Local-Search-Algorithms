import algorithm.GeneticAlgorithm;
import algorithm.LocalSearch;
import algorithm.SimulatedAnnealing.CoolingScheduler;
import algorithm.SimulatedAnnealing.SimulatedAnnealing;
import problem.GraphColoringGenetic;
import problem.GraphColoringLocalSearch;

public class Main implements Cloneable {

    public static void main(String[] args) {
//        GraphColoringLocalSearch graphColoringLocalSearch = new GraphColoringLocalSearch();
//        LocalSearch localSearch = new SimulatedAnnealeing(CoolingScheduler.reversi);
//        localSearch.run(graphColoringLocalSearch);

        GraphColoringGenetic graphColoringGenetic = new GraphColoringGenetic();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(10, 20, 0.2, 0.3);
        geneticAlgorithm.run(graphColoringGenetic);
        geneticAlgorithm.drawDiagram();

    }
}
