package genetic;

import utpc.utils.Scanner;
import utpc.utils.Term;

import java.util.Hashtable;

public class Individual {
    private Hashtable<String,Boolean> chromosome;
    private double fitness = -1;
    private Term boolExpr;

    public Individual(Hashtable<String,Boolean> chromosome, Term boolExpr) {
        //some problems
        this.chromosome = chromosome;
        this.boolExpr = boolExpr;
        for (String name:chromosome.keySet()) {
            if(boolExpr.literals.contains(name)) {
                chromosome.replace(name, true);
            } else{
                double a = Math.random();
                if (0.5 < a) {
                    this.setGene(name, true);
                } else {
                    this.setGene(name, false);
                }
            }

        }
    }

    public Term getBoolExpr() {
        return boolExpr;
    }

    public Hashtable<String,Boolean> getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.size();
    }

    public void setGene(String name, boolean value) {
        chromosome.replace(name,value);
    }

    public boolean getGene(String name) {
        return this.chromosome.get(name);
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString(Scanner sc) {
        sc.printTestCase();
        return  " -> " + fitness;
    }
}
