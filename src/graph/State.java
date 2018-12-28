package graph;

import java.util.ArrayList;

public abstract class State {

    public float score;

    public abstract ArrayList<State> getNeighbors();

    public abstract State getRandomNeighbor();

    public abstract String toString();
}
