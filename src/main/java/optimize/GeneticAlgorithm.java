package optimize;

import java.util.Random;

public class GeneticAlgorithm {

  private int populationSize;
  private double mutationRate;
  private double crossoverRate;
  private int elitismCount;

  public GeneticAlgorithm(
    int populationSize,
    double mutationRate,
    double crossoverRate,
    int elitismCount
  ) {
    this.populationSize = populationSize;
    this.mutationRate = mutationRate;
    this.crossoverRate = crossoverRate;
    this.elitismCount = elitismCount;
  }

  // 1. Initialize population
  public Population createPopulation(int geneLength) {
    return new Population(populationSize, geneLength);
  }

  // 2. Select parent (roulette wheel selection)
  public Individual selectParent(Population population) {
    return rouletteSelection(population);
  }

  // 3. Crossover (two point crossover)
  public Population crossover(Population population, double crossoverRate) {
    return populationCrossover(population, crossoverRate);
  }

  // 4. Mutation (normal distribution mutation)
  public void mutate(Population population, int mutationRate) {
    for (Individual individual : population.individuals()) {
      normalDistributionMutation(individual, mutationRate, 1);
    }
  }

  // 5. Evolve population
  public Population evolve(Population population) {
    return evolvePopulation(population);
  }

  private Population evolvePopulation(Population population) {
    Population newPopulation = new Population(populationSize, elitismCount);

    for (int i = 0; i < elitismCount; i++) {
      newPopulation.individuals()[i] = population.fittest();
    }

    Population xPopulation = crossover(population, crossoverRate);
    mutate(xPopulation, (int) (mutationRate * 100));

    for (int i = elitismCount; i < populationSize; i++) {
      newPopulation.individuals()[i] = xPopulation.individuals()[i];
    }

    newPopulation.updatePopulationFitness();

    return newPopulation;
  }

  // attempt 1, not enough diversity
  private void normalDistributionMutation(
    Individual individual,
    double mRate,
    double mStrength
  ) {
    Random random = new Random();
    double[] genes = individual.genes();

    for (int i = 0; i < genes.length; i++) {
      if (Math.random() < mRate) {
        double mutation = random.nextGaussian() * mStrength;
        genes[i] += mutation;
      }
    }

    // normalize genes
    for (int i = 0; i < genes.length; i++) {
      genes[i] = Math.min(Math.max(genes[i], -5.12), 5.12);
    }
  }

  private Population populationCrossover(
    Population population,
    double crossoverRate
  ) {
    Population newPopulation = new Population(
      populationSize,
      population.individuals()[0].genes().length
    );

    for (int i = 0; i < populationSize; i++) {
      Individual parent1 = population.individuals()[i];
      if (crossoverRate > Math.random() && i >= elitismCount) {
        Individual parent2 = selectParent(population);

        if (parent2 != null) {
          Individual child = twoPointCrossover(parent1, parent2);
          newPopulation.individuals()[i] = child;
        } else {
          newPopulation.individuals()[i] = parent1;
        }
      } else {
        newPopulation.individuals()[i] = parent1;
      }
    }

    return newPopulation;
  }

  private Individual twoPointCrossover(Individual parent1, Individual parent2) {
    int geneLength = parent1.genes().length;

    int xPoint1 = (int) (Math.random() * geneLength);
    int xPoint2 = (int) (Math.random() * geneLength);

    if (xPoint1 > xPoint2) {
      int temp = xPoint1;
      xPoint1 = xPoint2;
      xPoint2 = temp;
    }

    Individual child = new Individual(geneLength);

    for (int i = 0; i < geneLength; i++) {
      if (i < xPoint1 || i > xPoint2) {
        child.setGene(i, parent1.genes()[i]);
      } else {
        child.setGene(i, parent2.genes()[i]);
      }
    }

    return child;
  }

  private Individual rouletteSelection(Population population) {
    double populationFitness = population.populationFitness();
    double random = Math.random();
    double acc = 0;

    for (Individual individual : population.individuals()) {
      acc += individual.fitness() / populationFitness;
      if (acc >= random) {
        return individual;
      }
    }
    return null;
  }
}
