package algorithm;

import graph.State;
import problem.GraphColoring;
import problem.Problem;

public abstract class LocalSearch {
    public int visitedStatesNumber, expandedStatesNumber;
    private State bestState;

    public void run(Problem p){
        visitedStatesNumber = 0;
        expandedStatesNumber = 0;
        bestState = search(p);
//        printResult();
    }

    protected abstract State search(Problem p);

    public State getBestState(){
        return bestState;
    }

    private void printResult() {
        System.out.println("Visited State Size : " + visitedStatesNumber);
        System.out.println("Expanded State Size : " + expandedStatesNumber);
        System.out.println("Best State is : [" + bestState + "]");
        System.out.println("Score = " + bestState.score);
    }

}
