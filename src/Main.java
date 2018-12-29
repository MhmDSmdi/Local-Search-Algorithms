import algorithm.HillClimbing.StochasticHillClimbing;
import algorithm.LocalSearch;
import problem.GraphColoring;

public class Main implements Cloneable {

    public static void main(String[] args) {
        GraphColoring graphColoring = new GraphColoring();
        LocalSearch localSearch = new StochasticHillClimbing();
        localSearch.run(graphColoring);

    }
}
