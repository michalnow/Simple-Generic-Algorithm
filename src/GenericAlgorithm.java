import population.Population;

public class GenericAlgorithm {
	
	
	public static void main(String[] args) {
		Population population = new Population();
		population.randomizePopulation();
		population.showPopulation();
		System.out.println("===============");
		System.out.println(population.getTwoMostFittest());
	}
	
}
