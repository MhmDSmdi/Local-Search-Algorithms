package algorithm.local_search.HillClimbing;

import algorithm.local_search.LocalSearch;
import graph.State;
import problem.Problem;

public class SimulatedAnnealing extends LocalSearch {
    private static final int MAX_TRY_NUMS = 25;

    @Override
    protected State search(Problem p) {
        State s = p.getInitialState();
        int n = 0;
        int tryNum = 0;
        expandedStatesNumber++;
        while(true){
            State randomNeighbor = s.getRandomNeighbor();
            visitedStatesNumber++;
            if(p.objectiveFunction(randomNeighbor) > p.objectiveFunction(s)
                    || Math.random() < coolingFunction(n + tryNum)) {
                s = randomNeighbor;
                expandedStatesNumber++;
                n++;
                tryNum = 0;
            }
            else {
                tryNum++;
            }
            if(tryNum >= MAX_TRY_NUMS){
                break;
            }
        }
        return s;
    }

    private double coolingFunction(int n) {
        return Math.exp(-n);
    }
    
}
