import optimize.GeneticAlgorithm;
import optimize.Individual;
import optimize.Population;

public class Main {

  public static void main(String[] args) {
    int geneLength = 10; // number of dimensions
    int populationSize = 100;
    double mutationRate = 0.01;
    double crossoverRate = 0.9;
    int elitismCount = 1;
    int maxGenerations = 1000;

    GeneticAlgorithm ga = new GeneticAlgorithm(
      populationSize,
      mutationRate,
      crossoverRate,
      elitismCount
    );

    Population population = new Population(populationSize, geneLength);
    int generations = 0;

    while (generations < maxGenerations) {
      population = ga.evolve(population);

      Individual fittest = population.fittest();
      System.out.println(
        "Generation " +
        generations +
        " -> Fitness (" +
        fittest.fitness() +
        "): "
      );
      generations++;
    }

    System.out.println("Finished");
    System.out.println("Best solution:");
    System.out.println(population.fittest().fitness());
  }
}
