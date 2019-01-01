package algorithm.local_search.HillClimbing;

import graph.State;
import problem.Problem;

public class SimpleHillClimbing extends BasicHillClimbing {
    @Override
    protected State getNextState(State s, Problem p){
        double currentObjective = p.objectiveFunction(s);
        State nextState = null;
        double nextMaxObjective = -1.0;
        for(State neiState : s.getNeighbors()){
            double neiStateObjective = p.objectiveFunction(neiState);
            if(neiStateObjective > nextMaxObjective){
                nextMaxObjective = neiStateObjective;
                nextState = neiState;
            }
            visitedStatesNumber++;
        }
        if(nextState != null && nextMaxObjective <= currentObjective)
            return null;
        return nextState;
    }
}
