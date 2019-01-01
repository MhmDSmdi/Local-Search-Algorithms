package algorithm.local_search.HillClimbing;


import graph.State;
import problem.Problem;

public class RandomRestartHillClimbing extends BasicHillClimbing {
    private static final int MAX_RANDOM_TIME = 10;
    private int randomNumber;

    @Override
    protected State search(Problem problem) {
        randomNumber = 0;
        return super.search(problem);
    }

    @Override
    protected State getNextState(State s, Problem p) {
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
        if(nextState != null && nextMaxObjective <= currentObjective) {
            if(randomNumber < MAX_RANDOM_TIME) {
                randomNumber++;
                return p.getRandomState();
            }
            else {
                return null;
            }
        }
        return nextState;
    }
}
