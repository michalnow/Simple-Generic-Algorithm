package app;

import java.util.Scanner;

import population.Population;
import threading.PopulationThread;

public class GenericAlgorithm {

	private static void singleThreadExecution() {

		int generation = 0;
		Population population = new Population();
		population.randomizePopulation();
		long startTime = System.currentTimeMillis();
		System.out.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		
		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY) {
			population.showPopulation();
			++generation;
			population.addTheFittestOffspring();
			System.out
					.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("\n\n\nSolution find in generation number = " + generation + "\nFitness level = "
				+ population.getMaxFitnessLevel());
		population.showPopulation();

		System.out.println("It Took " + duration + " ms");
		System.out.println(population.getChromosomes());
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String choice = "";

		System.out.println("Time to decide:\n1 - Single thread execution\n2 - Multi thread execution");
		choice = scanner.next("[12]");

		switch (choice) {

		case "1": {
			singleThreadExecution();
			break;
		}

		case "2": {
			System.out.println("Number of threads: ");
			int noThreads = scanner.nextInt();
			long startTime = System.currentTimeMillis();

			Population population = new Population();
			population.randomizePopulation();
			System.out.println("Multi Thread");
			for (int i = 0; i < noThreads; i++) {
				Thread thread = new Thread(new PopulationThread(population, noThreads));
				thread.start();
			}

			long endTime = System.currentTimeMillis();
			System.out.println("It Took " + (endTime - startTime) + " ms");

			break;
		}

		default: {
			System.out.println("Wrong input");
		}
		}

		scanner.close();

	}
}
