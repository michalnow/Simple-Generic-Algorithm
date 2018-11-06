import population.Population;

public class GenericAlgorithm {
	
	
	public static void main(String[] args) {
		Population population = new Population();
		population.randomizePopulation();
		population.showPopulation();
		System.out.println("Now Executing selection, result below \n" + population.selection());
		System.out.println("Now execeuting crossover, result below \n" + population.crossover());
		population.addTheFittestOffspring();
		population.showPopulation();
		
	}
	
}
