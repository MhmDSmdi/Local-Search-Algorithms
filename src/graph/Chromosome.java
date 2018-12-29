package graph;

import java.util.ArrayList;

public class Chromosome {

    private ArrayList<Gen> gens;

    public Chromosome(ArrayList<Gen> gens){
        this.gens = gens;
    }

    public Chromosome clone(){
        ArrayList<Gen> newGens = new ArrayList<>();
        for (Gen gen : gens) newGens.add(gen.clone());
        return new Chromosome(newGens);
    }

    public String toString(){
        String s = "";
        for(Gen g : gens)
            s = s + g.toString() + ", ";
        return s;
    }

    public ArrayList<Gen> getGens() {
        return gens;
    }
}
