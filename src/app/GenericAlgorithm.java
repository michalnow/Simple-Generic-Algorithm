package app;
import java.util.Scanner;

import population.Population;

public class GenericAlgorithm {

	private static void singleThreadExecution() {
		int generation = 0;
		Population population = new Population();
		population.randomizePopulation();
		long startTime = System.currentTimeMillis();
		System.out.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		while (population.getMaxFitnessLevel() < population.GENES_QUANTITY) {
			++generation;
			population.addTheFittestOffspring();
			System.out
					.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("\n\n\nSolution find in generation number = " + generation + "\nFitness level = "
				+ population.getMaxFitnessLevel());
		population.showPopulation();

		System.out.println("It Took " + (endTime - startTime)  + " ms");
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String choice ="";
		
		System.out.println("Time to decide:\n1 - Single thread execution\n2 - Multi thread execution1");
		choice = scanner.next("[12]");
		
		switch(choice) {
		
			case "1" : {
				singleThreadExecution();
				break;
			}
			
			case "2" : {
				System.out.println("Multi Thread");
				break; 
			}
			
			default: {
				System.out.println("Wrong input");
			}
		}
		
		scanner.close();
		
	}
}
