package GeneticAlgorithm;

import java.util.Random;

//Cameron Cronheimer
//6517080

//Chromosome
public class Chromosome implements Comparable<Chromosome> {

    // array of genes which is column #
    private int[] genes;

    // lower score the better
    private double fitness;

    char[][] shreddedDocument;

    public Chromosome(int geneLength, char[][] shreddedDocument) {

        // Initialization
        this.genes = new int[geneLength];

        this.shreddedDocument = shreddedDocument;

    }

    // sets the fitness of this chromosome
    public void setFitness() {

        fitness = FitnessCalculator.fitness(shreddedDocument, genes);

    }

    // gets the chromosome gene
    public int[] getGene() {

        return genes;
    }

    // sets the chromosome genes
    public void setGenes(int i, int num) {

        genes[i] = num;

    }

    // pass the random seed so get the same pop when intitializing
    public void initializeGenes(Random rand) {

        for (int i = 0; i < genes.length; i++) {
            genes[i] = i;
        }

        // this shuffles the array
        for (int i = 0; i < genes.length; i++) {

            // 0-15
            int swap = rand.nextInt(genes.length);
            int tmp = genes[swap];
            genes[swap] = genes[i];
            genes[i] = tmp;

        }

    }

    // returns fintness of this chromosome
    public double getFitness() {

        return fitness;

    }

    // mutator
    // swaps the chromosomes 2 random indices
    public void mutate(Random rand) {

        int gene1 = rand.nextInt(genes.length);
        int gene2 = rand.nextInt(genes.length);

        int tmp = genes[gene1];
        genes[gene1] = genes[gene2];
        genes[gene2] = tmp;

        // update the fitness
        setFitness();
    }

    // allows the Arrays.Sort to work which sorts by fitness
    @SuppressWarnings("deprecation")
    @Override
    public int compareTo(Chromosome o) {

        return new Double(fitness).compareTo(o.getFitness());

    }

}
