package problem;

import graph.State;

public abstract class Problem{

    protected State initialState;

    public State getInitialState() {
        return initialState;
    }

    public abstract double objectiveFunction(State s);

    public abstract State getRandomState();
}
