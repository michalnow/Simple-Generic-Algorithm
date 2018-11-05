import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenericAlgorithm {
	private final static int POPULATION_QUANTITY = 5;
	private final static int GENES_QUANTITY = 6;
	
	private static List<String> chromosomes = new ArrayList<String>();
	
	private static Random random;
	
	public static void randomizePopulation() {
		
		random = new Random();
		String chromosome = "";
				
		for(int i = 0; i < POPULATION_QUANTITY; i++) {
			for(int j =0; j < GENES_QUANTITY; j++) {
				chromosome += random.nextInt(2);
			}
			chromosomes.add(chromosome);
			chromosome = "";
		}
	}
	
	public static void showPopulation() {
		for(String chromosome: chromosomes) {
			System.out.println(chromosome);
		}
	}
	
	public static void main(String[] args) {
		randomizePopulation();
		showPopulation();
	}
	
}
