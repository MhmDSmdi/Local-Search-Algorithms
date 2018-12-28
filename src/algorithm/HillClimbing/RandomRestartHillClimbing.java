package algorithm.HillClimbing;


import graph.State;
import problem.Problem;

public class RandomRestartHillClimbing extends HillClimbing{
    private static final int MAX_RANDOM_TIME = 10;
    private int randomNumber;

    @Override
    protected State search(Problem p) {
        randomNumber = 0;
        return super.search(p);
    }

    @Override
    protected State getNextState(State s, Problem p) {
        double currentObjective = p.objectiveFunction(s);
        State nextState = null;
        double nextMaxObjective = -1.0 / 0.0;
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
