package optimize;

public class Individual {

  private double[] genes;
  private double fitness = 0;
  private final double minValue = -5.12;
  private final double maxValue = 5.12;

  public Individual(int geneLength) {
    this.genes = new double[geneLength];

    for (int i = 0; i < geneLength; i++) {
      genes[i] = minValue + (Math.random() * (maxValue - minValue));
    }
  }

  public double[] genes() {
    return genes;
  }

  public double fitness() {
    if (fitness == 0) {
      fitness = calculateFitness();
    }

    return fitness;
  }

  public void setGene(int index, double value) {
    genes[index] = value;
    fitness = 0;
  }

  private double calculateFitness() {
    double sumFitness = 0;

    for (double gene : genes) {
      sumFitness += rastriginFunction(gene);
    }

    return 10 * genes.length + sumFitness;
  }

  private double rastriginFunction(double x) {
    return x * x - 10 * Math.cos(2 * Math.PI * x) + 10;
  }
}
