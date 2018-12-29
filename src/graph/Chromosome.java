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
        StringBuilder stringBuilder = new StringBuilder();
        for(Gen g : gens) {
            stringBuilder.append(g);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<Gen> getGens() {
        return gens;
    }
}
