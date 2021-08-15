package GeneticAlgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

//Cameron Cronheimer
//6517080

public class GeneticAlgo {

    // GA params
    char[][] shreddedDocument;
    int populationSize;
    int maxGeneration;
    float crossOverRate;
    float mutationRate;
    int eliteSize;
    int tournamentSize;

    Chromosome[] chromosomes;
    int chromosomeLength = 15;

    // random object for seed
    Random random;

    public GeneticAlgo(char[][] shreddedDocument, int populationSize, int maxGens, float crossOverPer,
                       float mutationPer, float elitePer, float tournamentSizePer, long seed) {

        this.shreddedDocument = shreddedDocument;

        // create the empty population
        chromosomes = new Chromosome[populationSize];

        // set the random seed used for all random gens and array shuffles
        random = new Random();
        random.setSeed(seed);

        // print out the GA params
        System.out.println("----------[GA]-----------");
        System.out.println("Seed:             " + seed);

        // conversions and setting params
        this.populationSize = populationSize;
        System.out.println("Population Size:  " + populationSize);

        this.maxGeneration = maxGens;
        System.out.println("Max Generations:  " + maxGeneration);

        this.crossOverRate = crossOverPer / 100f;
        System.out.println("Crossover Rate:   " + crossOverRate);

        this.mutationRate = mutationPer / 100f;
        System.out.println("Mutation Rate:    " + mutationRate);

        this.eliteSize = (int) (populationSize * (elitePer / 100f));
        System.out.println("Number of Elites: " + eliteSize);

        this.tournamentSize = (int) (populationSize * (tournamentSizePer / 100f));
        System.out.println("Tournament Size:  " + tournamentSize);

        System.out.println();
        System.out.println("--------[Results]--------");
    }

    // first create the initial population
    void createPopulation() {

        for (int i = 0; i < chromosomes.length; i++) {

            chromosomes[i] = new Chromosome(chromosomeLength, shreddedDocument);

            // creates the chromosomes genes
            chromosomes[i].initializeGenes(random);

        }

    }

    // set the fitness of each chromosome
    void calculateFitness() {

        for (int i = 0; i < chromosomes.length; i++) {

            chromosomes[i].setFitness();
        }
    }

    public int[] findFittestChromosome() {

        int generation = 0;

        // creates the initial population
        createPopulation();

        // terminates when maxGeneration is reached
        while (generation < (maxGeneration)) {

            // need to calculate each individuals fitness every generation.
            calculateFitness();

            // order chromosomes most fit to least fit
            Arrays.sort(chromosomes);

            // select elite

            // newPop[] is a temp array that will be copied and replace the old population
            // after being filled up
            // with the population
            Chromosome[] newPop = new Chromosome[populationSize];
            Chromosome[] elites = new Chromosome[eliteSize];
            Chromosome[] matingPool = new Chromosome[eliteSize + 1];

            // since the population is sort best fit to worst we can pick
            // the first n chromosomes as the elites
            for (int e = 0; e < eliteSize; e++) {

                elites[e] = chromosomes[e];

                // send elites to the newPopulation
                newPop[e] = elites[e];

                // send elites to the mating Pool
                matingPool[e] = elites[e];
            }

            // offset the tournament pool by number of elites so we dont count elites
            Chromosome winner = tournament(eliteSize);

            // last to be added to the mating pool is the winner of the tournament
            matingPool[matingPool.length - 1] = winner;

            // offset filled index by number of elites already in the new population
            int filled = eliteSize;

            // this is creating and adding the new children to the new population
            while (filled < (populationSize)) {

                // randomize the mating pool so we select two random parents everytime
                Collections.shuffle(Arrays.asList(matingPool), random);
                Chromosome leftParent = matingPool[0];
                Chromosome rightParent = matingPool[1];

                // if we are crossing over pass the children to the new pop
                if (random.nextFloat() <= crossOverRate) {

                    // preserving 7 genes from the parent chromosome
                    Chromosome[] children = orderCrossOver(leftParent, rightParent, 6);

                    // mutation rate
                    if (random.nextFloat() <= mutationRate) {

                        children[0].mutate(random);
                        children[1].mutate(random);
                    }

                    // set those children in there spots
                    newPop[filled] = children[0];
                    filled += 1;

                    // if population is filled then stop filling spots
                    if (filled == populationSize) {

                        break;
                    }

                    newPop[filled] = children[1];
                    filled += 1;

                    // if we arent crossing over then pass the parents to the new pop
                } else {

                    // mutation rate
                    if (random.nextFloat() <= mutationRate) {

                        leftParent.mutate(random);
                        rightParent.mutate(random);
                    }

                    // set those children in there spots
                    newPop[filled] = leftParent;
                    filled += 1;

                    if (filled == populationSize) {

                        break;
                    }

                    newPop[filled] = rightParent;
                    filled += 1;

                }

            }

            // the sum of population fitness
            double overallFit = 0;

            for (int p = 0; p < newPop.length; p++) {

                overallFit += newPop[p].getFitness();

            }

            // average population fitness
            double averageFit = overallFit / populationSize;

            Arrays.sort(chromosomes);

            System.out.printf("Generation: " + (generation + 1) + " avg: " + "%.3f", averageFit);
            System.out.printf(" best: " + "%.3f", chromosomes[0].getFitness());
            System.out.println();

            // discard the old chromosomes array and replace/copy the new pop into it
            chromosomes = Arrays.copyOf(newPop, populationSize);

            // increase generation by 1
            generation++;
        }

        int[] bestPerm = new int[chromosomeLength];

        // sort the population so the best chromosome will be at the very beginining [0]
        Arrays.sort(chromosomes);
        Chromosome bestChromosome = chromosomes[0];

        bestPerm = bestChromosome.getGene();

        return bestPerm;

    }

    // order crossOver
    Chromosome[] orderCrossOver(Chromosome leftParent, Chromosome rightParent, int preserve) {

        // returns two children
        Chromosome[] children = new Chromosome[2];

        Chromosome leftChild = new Chromosome(chromosomeLength, shreddedDocument);
        Chromosome rightChild = new Chromosome(chromosomeLength, shreddedDocument);

        // gets the range of the preserved indices
        int min = 0;
        int max = (chromosomeLength - 1) - preserve;
        int start = min + random.nextInt((max - min) + 1);
        int end = start + preserve;

        // presereves the set of numbers
        for (int i = 0; i < chromosomeLength; i++) {

            // being preserved
            if (i >= start && i <= end) {

                // leftChild gets leftParents characteristics
                // rightChild get rigthParents characteristics
                leftChild.setGenes(i, leftParent.getGene()[i]);
                rightChild.setGenes(i, rightParent.getGene()[i]);

                // not being preserved
            } else {

                // fill with -1 so we can recongize all the preserved numbers
                leftChild.setGenes(i, -1);
                rightChild.setGenes(i, -1);
            }

        }

        int filled1 = 0;

        // the amout of spots needed to be filled
        int spots = chromosomeLength - preserve;

        // cross over the left parent with the right parent

        // while there are available spots left
        while (filled1 < spots - 1) {

            for (int i = 0; i < chromosomeLength; i++) {

                boolean exists = false;

                // search n if exists already
                int n = leftParent.getGene()[i];

                // check if the index exists/preserved already in the child
                for (int j = 0; j < chromosomeLength; j++) {

                    // if n exists already then break out i++
                    if (rightChild.getGene()[j] == n) {

                        exists = true;
                        break;
                    }
                }

                // if n does not exist then check what spot is available to receive it
                if (!exists) {

                    // check to see if index is full, if so then go to next index
                    for (int f = 0; f < chromosomeLength; f++) {

                        if (rightChild.getGene()[f] == -1) {

                            rightChild.setGenes(f, n);

                            break;

                        }

                    }

                    // add the number of filled up spots
                    filled1 += 1;

                }

            }

        }

        int filled = 0;

        // cross over the left parent with the right parent
        // while there are available spots left
        while (filled < spots - 1) {

            for (int i = 0; i < chromosomeLength; i++) {

                boolean exists = false;

                // search n if exists already
                int n = rightParent.getGene()[i];

                // check if the index exists/preserved already in the child
                for (int j = 0; j < chromosomeLength; j++) {

                    // if n exists already then break out i++
                    if (leftChild.getGene()[j] == n) {

                        exists = true;
                        break;
                    }
                }

                // if n does not exist then check what spot is available to receive it
                if (!exists) {

                    // check to see if index is full, if so then go to next index
                    for (int f = 0; f < chromosomeLength; f++) {

                        if (leftChild.getGene()[f] == -1) {

                            leftChild.setGenes(f, n);

                            break;

                        }

                    }

                    // add the number of filled up spots
                    filled += 1;

                }

            }

        }

        // return the children created in the cross over
        rightChild.setFitness();
        leftChild.setFitness();

        children[0] = rightChild;
        children[1] = leftChild;

        return children;

    }

    // picks n amount of random chromosomes from the population
    // whatever has the best fitness is the winner
    Chromosome tournament(int eliteOffset) {

        // copy and remove elite(s) from to
        Chromosome[] tournamentQualifiers = Arrays.copyOfRange(chromosomes, eliteOffset, chromosomes.length);

        // shuffle the sorted list
        Collections.shuffle(Arrays.asList(tournamentQualifiers), random);

        // defualt winner
        Chromosome winner = tournamentQualifiers[0];

        // since the list is shuffled pick go thru the amount of chhromosomes of
        // tournament size
        for (int i = 0; i < tournamentSize; i++) {

            // selects the best fitness out of the group
            if (tournamentQualifiers[i].getFitness() < winner.getFitness()) {

                winner = tournamentQualifiers[i];
            }

        }

        // returns the most fit chromosome out of the pool
        return winner;

    }

}