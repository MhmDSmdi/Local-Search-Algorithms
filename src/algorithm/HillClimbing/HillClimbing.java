package algorithm.HillClimbing;

import algorithm.LocalSearch;
import graph.State;
import problem.Problem;

public abstract class HillClimbing extends LocalSearch {
    @Override
    protected State search(Problem p) {
        State s = p.getInitialState();
        while(true){
            expandedStatesNumber++;
            State nextState = getNextState(s, p);
            if(nextState != null){
                s = nextState;
            }
            else {
                break;
            }
        }
        p.objectiveFunction(s);
        return s;
    }

    protected abstract State getNextState(State s, Problem p);
}
