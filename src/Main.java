import algorithm.HillClimbing.FirstChoiceHillClimbing;
import algorithm.LocalSearch;
import graph.Node;
import problem.GraphColoring;
import problem.Problem;

public class Main {
    public static void main(String[] args) {
        GraphColoring graphColoring = new GraphColoring();
        LocalSearch localSearch = new FirstChoiceHillClimbing();
        localSearch.run(graphColoring);
    }
}
