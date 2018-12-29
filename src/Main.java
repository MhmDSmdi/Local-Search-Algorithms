import algorithm.LocalSearch;
import algorithm.SimulatedAnnealing.CoolingScheduler;
import algorithm.SimulatedAnnealing.SimulatedAnnealing;
import problem.GraphColoringLocalSearch;

public class Main implements Cloneable {

    public static void main(String[] args) {
        GraphColoringLocalSearch graphColoringLocalSearch = new GraphColoringLocalSearch();
        LocalSearch localSearch = new SimulatedAnnealing(CoolingScheduler.reversi);
        localSearch.run(graphColoringLocalSearch);

    }
}
