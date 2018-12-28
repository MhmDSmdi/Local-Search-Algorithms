package graph;
import java.util.ArrayList;

public abstract class State {

    private ArrayList<State> neighbors;
    private String stateName;

    public State(String stateName){
        this.stateName= stateName;
    }

    public abstract State getRandomNeighbor();

    public String toString() {
        return "State" + stateName;
    }
}
