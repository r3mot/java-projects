package optimize;

public class Population {

  private Individual[] individuals;

  public Population(int populationSize, int geneLength) {
    individuals = new Individual[populationSize];

    for (int i = 0; i < populationSize; i++) {
      individuals[i] = new Individual(geneLength);
    }
  }

  public Individual fittest() {
    Individual fittest = individuals[0];
    for (int i = 1; i < individuals.length; i++) {
      if (fittest.fitness() > individuals[i].fitness()) {
        fittest = individuals[i];
      }
    }

    return fittest;
  }

  public Individual[] individuals() {
    return individuals;
  }
}
