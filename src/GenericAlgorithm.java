import population.Population;

public class GenericAlgorithm {

	public static void main(String[] args) {
		int generation = 0;
		Population population = new Population();
		population.randomizePopulation();
		System.out.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		while (population.getMaxFitnessLevel() < population.GENES_QUANTITY) {
			++generation;
			System.out.println("Now Executing selection, result below \n" + population.selection());
			System.out.println("Now execeuting crossover, result below \n" + population.crossover());
			population.addTheFittestOffspring();
			System.out
					.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		}

		System.out.println("\n\n\nSolution find in generation number = " + generation + "\nFitness level = "
				+ population.getMaxFitnessLevel());
		population.showPopulation();

	}

}
