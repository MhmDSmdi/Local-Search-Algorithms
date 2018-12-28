package problem;

import graph.State;

public abstract class Problem{

    private State initialState;

    public Problem(State initialState){
        this.initialState = initialState;
    }

    public abstract double objectiveFunction(State s);

    public abstract State getRandomState();
}
