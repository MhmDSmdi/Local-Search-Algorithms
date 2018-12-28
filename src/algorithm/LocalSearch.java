package algorithm;


import graph.State;
import problem.Problem;

public abstract class LocalSearch {
    public int visitedStatesNumber, expandedStatesNumber;

    private State bestState;
    public void run(Problem p){
        visitedStatesNumber = 0;
        expandedStatesNumber = 0;
        bestState = search(p);
    }
    protected abstract State search(Problem p);
    public State getBestState(){
        return bestState;
    }
}
