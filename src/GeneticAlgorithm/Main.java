package GeneticAlgorithm;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Cameron Cronheimer
    // 6517080

    Scanner in;

    public Main() {

        int choice = -1;
        int populationSize = 0;
        int maxGenerations = 0;
        float crossOverRate = 0f;
        float mutationRate = 0f;
        float elitePer = 0f;
        float tournamentSizePer = 0f;
        long seed = 0;
        int[] solutionPerm;
        char[][] unshredded;

        in = new Scanner(System.in);

        GeneticAlgo ga;

        do {

            try {

                choice = menu();

                switch (choice) {

                    // doc 1
                    case 1:

                        char[][] shreddedDocument1 = FitnessCalculator.getShreddedDocument("Assets/document1-shredded.txt");

                        System.out.println();
                        System.out.println("-------[Document 1]-------");
                        System.out.println("(1% is 1) (10% is 10) (100% is 100) etc...");
                        System.out.println();
                        System.out.println("Please build your GA below.");
                        System.out.println();
                        System.out.println("Population Size: ");
                        populationSize = in.nextInt();
                        System.out.println("Max Generations: ");
                        maxGenerations = in.nextInt();
                        System.out.println("% CrossOver Rate: ");
                        crossOverRate = in.nextFloat();
                        System.out.println("% Mutation Rate: ");
                        mutationRate = in.nextFloat();
                        System.out.println("% of Elites");
                        elitePer = in.nextFloat();
                        System.out.println("% of Tournament Size: ");
                        tournamentSizePer = in.nextFloat();
                        System.out.println("Seed: ");
                        seed = in.nextLong();
                        System.out.println();

                        ga = new GeneticAlgo(shreddedDocument1, populationSize, maxGenerations, crossOverRate, mutationRate,
                                elitePer, tournamentSizePer, seed);
                        solutionPerm = ga.findFittestChromosome();

                        System.out.println();
                        System.out.println("---------[Output]---------");
                        unshredded = FitnessCalculator.unshred(shreddedDocument1, solutionPerm);
                        FitnessCalculator.prettyPrint(unshredded);

                        System.out.println();
                        System.out.println("--------[Solution]--------");

                        System.out.println("Solution Perm: " + Arrays.toString(solutionPerm));
                        System.out.printf("Fitness: " + "%.3f", FitnessCalculator.fitness(shreddedDocument1, solutionPerm));

                        System.out.println();
                        System.out.println();

                        break;

                    // doc 2
                    case 2:

                        char[][] shreddedDocument2 = FitnessCalculator.getShreddedDocument("Assets/document2-shredded.txt");

                        System.out.println();
                        System.out.println("-------[Document 2]-------");
                        System.out.println("(1% is 1) (10% is 10) (100% is 100) etc...");
                        System.out.println();
                        System.out.println("Please build your GA below.");
                        System.out.println();
                        System.out.println("Population Size: ");
                        populationSize = in.nextInt();
                        System.out.println("Max Generations: ");
                        maxGenerations = in.nextInt();
                        System.out.println("% CrossOver Rate: ");
                        crossOverRate = in.nextFloat();
                        System.out.println("% Mutation Rate: ");
                        mutationRate = in.nextFloat();
                        System.out.println("% of Elites");
                        elitePer = in.nextFloat();
                        System.out.println("% of Tournament Size: ");
                        tournamentSizePer = in.nextFloat();
                        System.out.println("Seed: ");
                        seed = in.nextLong();
                        System.out.println();

                        ga = new GeneticAlgo(shreddedDocument2, populationSize, maxGenerations, crossOverRate, mutationRate,
                                elitePer, tournamentSizePer, seed);
                        solutionPerm = ga.findFittestChromosome();

                        System.out.println();
                        System.out.println("---------[Output]---------");
                        unshredded = FitnessCalculator.unshred(shreddedDocument2, solutionPerm);
                        FitnessCalculator.prettyPrint(unshredded);

                        System.out.println();
                        System.out.println("--------[Solution]--------");

                        System.out.println("Solution Perm: " + Arrays.toString(solutionPerm));
                        System.out.printf("Fitness: " + "%.3f", FitnessCalculator.fitness(shreddedDocument2, solutionPerm));

                        System.out.println();
                        System.out.println();

                        break;

                    // doc 3
                    case 3:

                        char[][] shreddedDocument3 = FitnessCalculator.getShreddedDocument("Assets/document3-shredded.txt");

                        System.out.println();
                        System.out.println("-------[Document 3]-------");
                        System.out.println("(1% is 1) (10% is 10) (100% is 100) etc...");
                        System.out.println();
                        System.out.println("Please build your GA below.");
                        System.out.println();
                        System.out.println("Population Size: ");
                        populationSize = in.nextInt();
                        System.out.println("Max Generations: ");
                        maxGenerations = in.nextInt();
                        System.out.println("% CrossOver Rate: ");
                        crossOverRate = in.nextFloat();
                        System.out.println("% Mutation Rate: ");
                        mutationRate = in.nextFloat();
                        System.out.println("% of Elites");
                        elitePer = in.nextFloat();
                        System.out.println("% of Tournament Size: ");
                        tournamentSizePer = in.nextFloat();
                        System.out.println("Seed: ");
                        seed = in.nextLong();
                        System.out.println();

                        ga = new GeneticAlgo(shreddedDocument3, populationSize, maxGenerations, crossOverRate, mutationRate,
                                elitePer, tournamentSizePer, seed);
                        solutionPerm = ga.findFittestChromosome();

                        System.out.println();
                        System.out.println("---------[Output]---------");
                        unshredded = FitnessCalculator.unshred(shreddedDocument3, solutionPerm);
                        FitnessCalculator.prettyPrint(unshredded);

                        System.out.println();
                        System.out.println("--------[Solution]--------");

                        System.out.println("Solution Perm: " + Arrays.toString(solutionPerm));
                        System.out.printf("Fitness: " + "%.3f", FitnessCalculator.fitness(shreddedDocument3, solutionPerm));

                        System.out.println();
                        System.out.println();

                        break;

                    case 0:

                        break;

                    default:

                        System.out.println("Unrecognized Option");

                }

            } catch (InputMismatchException ime) {

                in.next();
                System.out.println("Input Mismatch Exception.\n");

            }

        } while (choice != 0);

    }

    // menu
    int menu() {

        System.out.println("----------[MENU]----------");
        System.out.println("0: End program");
        System.out.println("1: DOC 1");
        System.out.println("2: DOC 2");
        System.out.println("3: DOC 3");
        System.out.println();
        System.out.println("Enter option 0 to 3:");
        return in.nextInt();

    }

    public static void main(String[] args) {
        new Main();

    }

}