package algorithm.local_search.HillClimbing;

import graph.State;
import problem.Problem;

import java.util.ArrayList;

public class StochasticHillClimbing extends BasicHillClimbing {
    @Override
    protected State getNextState(State s, Problem p) {
        double currentObjective = p.objectiveFunction(s);
        ArrayList<State> betterStates = new ArrayList<>();
        for(State neiState : s.getNeighbors()){
            if(p.objectiveFunction(neiState) > currentObjective) {
                betterStates.add(neiState);
            }
            visitedStatesNumber++;
        }
        if(betterStates.size() == 0)
            return null;
        return betterStates.get((int) (Math.random() * betterStates.size()));
    }
}
