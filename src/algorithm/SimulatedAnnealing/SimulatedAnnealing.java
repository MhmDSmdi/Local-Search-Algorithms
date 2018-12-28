package algorithm.SimulatedAnnealing;

import algorithm.LocalSearch;
import graph.State;
import problem.Problem;

public class SimulatedAnnealing extends LocalSearch {
    private CoolingScheduler coolingScheduler;
    public SimulatedAnnealing(CoolingScheduler coolingScheduler){
        this.coolingScheduler = coolingScheduler;
    }

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
                    || Math.random() < coolingScheduler.possibility(n+tryNum)) {
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
}
