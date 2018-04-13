package test;

import genetic.GeneticAlgorithm;
import genetic.Population;
import utpc.*;
import utpc.utils.*;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String args[]) {
        String expr = "(b & e & ~a & ~d & ~f) | (b & e & ~b & ~d & ~f) | (b & d & e & ~a & ~e & ~f) | (b & d & e & ~b & ~e & ~f) | (b & d & f & ~a & ~e & ~f) | (b & d & f & ~b & ~e & ~f) | (b & e & f & ~a & ~d & ~f) | (b & e & f & ~b & ~d & ~f) | (a & d & ~a & ~e & ~f & ~h) | (a & d & ~b & ~e & ~f & ~h) | (a & e & ~a & ~d & ~f & ~h) | (a & e & ~b & ~d & ~f & ~h) | (b & e & ~a & ~d & ~e & ~f) | (b & e & ~b & ~d & ~e & ~f) | (b & f & ~a & ~d & ~e & ~f) | (b & f & ~b & ~d & ~e & ~f) | (a & c & d & h & ~a & ~e & ~f) | (a & c & d & h & ~b & ~e & ~f) | (a & c & e & h & ~a & ~d & ~f) | (a & c & e & h & ~b & ~d & ~f) | (a & d & e & ~a & ~d & ~f & ~h) | (a & d & e & ~a & ~e & ~f & ~h) | (a & d & e & ~b & ~d & ~f & ~h) | (a & d & e & ~b & ~e & ~f & ~h) | (a & c & d & e & h & ~a & ~d & ~f) | (a & c & d & e & h & ~a & ~e & ~f) | (a & c & d & e & h & ~b & ~d & ~f) | (a & c & d & e & h & ~b & ~e & ~f) | (a & d & ~a & ~d & ~e & ~f & ~h) | (a & d & ~b & ~d & ~e & ~f & ~h) | (a & e & ~a & ~d & ~e & ~f & ~h) | (a & e & ~b & ~d & ~e & ~f & ~h) | (a & c & d & h & ~a & ~d & ~e & ~f) | (a & c & d & h & ~b & ~d & ~e & ~f) | (a & c & e & h & ~a & ~d & ~e & ~f) | (a & c & e & h & ~b & ~d & ~e & ~f)";
        Scanner sc = new Scanner(expr);
        sc.printNotConstant();
        for (int index = 0; index < sc.terms.size(); index++) {
            if (!sc.terms.get(index).getIsConstant()) {
                System.out.println(index);
                GeneticAlgorithm ga = new GeneticAlgorithm(2, 0.001, 0.95, 2, sc, index);
                Population population = ga.initPopulation(sc, sc.getTerms().get(index));
                ga.evalPopulation(population);
                int generation = 1;
                while (ga.isTerminationConditionMet(population) == false) {
                    // Print fittest individual from population
                    System.out.println("Best solution: " + population.getFittest(0).toString());
                    // Apply crossover
                    population = ga.crossoverPopulation(population);
                    // Apply mutation
                    population = ga.mutatePopulation(population);
                    // Evaluate population
                    ga.evalPopulation(population);
                    // Increment the current generation
                    generation++;
                }
                System.out.println("Found solution in " + generation + " generations");
                System.out.println("Best solution: " + population.getFittest(0).toString(sc));
            }
        }
    }
}
