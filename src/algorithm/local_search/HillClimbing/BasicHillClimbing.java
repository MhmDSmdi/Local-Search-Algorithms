package algorithm.local_search.HillClimbing;

import algorithm.local_search.LocalSearch;
import graph.State;
import problem.Problem;

public abstract class BasicHillClimbing extends LocalSearch {
    @Override
    protected State search(Problem problem) {
        State state = problem.getInitialState();
        while(true){
            expandedStatesNumber++;
            State nextState = getNextState(state, problem);
            if(nextState != null){
                state = nextState;
            }
            else {
                break;
            }
        }
        problem.objectiveFunction(state);
        return state;
    }

    protected abstract State getNextState(State s, Problem p);
}
