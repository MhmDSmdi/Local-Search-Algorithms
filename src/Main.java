import algorithm.local_search.LocalSearch;
import algorithm.local_search.HillClimbing.SimulatedAnnealing;
import problem.GraphColoringLocalSearch;

public class Main implements Cloneable {

    public static void main(String[] args) {
        GraphColoringLocalSearch graphColoringLocalSearch = new GraphColoringLocalSearch();
        LocalSearch localSearch = new SimulatedAnnealing();
        localSearch.startLocalSearch(graphColoringLocalSearch);

//        GraphColoringGenetic graphColoringGenetic = new GraphColoringGenetic();
//        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(50, 1000, 0.4, 0.2);
//        geneticAlgorithm.run(graphColoringGenetic);
//        geneticAlgorithm.drawDiagram();
//        System.out.println(geneticAlgorithm.getBest());

    }
}
