package genetic;

import utpc.utils.Scanner;

public class UTPCGA {

    public static void main(String[] args) {
        // Create GA object
        String expr = "(b & e & ~a & ~d & ~f)  | (a & d & ~b & ~e & ~f & ~h)";
        Scanner sc = new Scanner(expr);
        sc.printNotConstant();
        GeneticAlgorithm ga;
        Population population;
        int generation;
        for(int index=0;index<sc.terms.size();index++) {
            System.out.println("index: " + index);
            if (!sc.terms.get(index).getIsConstant()) {
                generation = 1;
                ga = new GeneticAlgorithm(3, 0.001, 0.95, 2, sc, index);
                population = ga.initPopulation(sc, sc.getTerms().get(index));
                ga.evalPopulation(population);
                while (!ga.isTerminationConditionMet(population)) {
                    // Print fittest individual from population
                    System.out.println("Best solution: " + population.getFittest(0).getFitness());
                    // Apply crossover
                    population = ga.crossoverPopulation(population);
                    // Apply mutation
                    population = ga.mutatePopulation(population);
                    // Evaluate population
                    ga.evalPopulation(population);
                    // Increment the current generation
                    generation++;
                }

                /**
                 * We're out of the loop now, which means we have a perfect solution on
                 * our hands. Let's print it out to confirm that it is actually all
                 * ones, as promised.
                 */

                System.out.println("Found solution in " + generation + " generations");
                System.out.println("Best solution: " + population.getFittest(0).toString(sc));
            }
        }
    }
}
