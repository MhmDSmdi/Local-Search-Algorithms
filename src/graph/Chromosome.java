package graph;

public class Chromosome {
    public Gen[] gens;

    public Chromosome(Gen[] gens){
        this.gens = gens;
    }

    public Chromosome clone(){
        Gen[] newGens = new Gen[gens.length];
        for(int i = 0; i < gens.length; i++)
            newGens[i] = gens[i].clone();

        return new Chromosome(newGens);
    }

    public String toString(){
        String s = "[";
        for(int i = 0; i < gens.length-1; i++)
            s = s + gens[i].toString() + ", ";

        s = s + gens[gens.length-1].toString();
        s = s + "]";
        return s;
    }
}
