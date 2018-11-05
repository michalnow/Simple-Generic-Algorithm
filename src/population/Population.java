package population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Population {
	private final static int POPULATION_QUANTITY = 5;
	private final static int GENES_QUANTITY = 6;

	private List<String> chromosomes = new ArrayList<String>();

	private Random random;

	public void randomizePopulation() {

		random = new Random();
		String chromosome = "";

		for (int i = 0; i < POPULATION_QUANTITY; i++) {
			for (int j = 0; j < GENES_QUANTITY; j++) {
				chromosome += random.nextInt(2);
			}
			chromosomes.add(chromosome);
			chromosome = "";
		}
	}

	public void showPopulation() {
		for (String chromosome : chromosomes) {
			System.out.println(chromosome);
		}
	}

	private int getFitness(int index) {

		int fitness = 0;

		for (int i = 0; i < GENES_QUANTITY; i++) {
			if (chromosomes.get(index).toCharArray()[i] == '1') {
				fitness++;
			}
		}

		return fitness;
	}
	
	private String getFittest() {
		int fittest = Integer.MIN_VALUE;
		int fittestIndex = 0;
		
		for(int i = 0; i < POPULATION_QUANTITY; i++) {
			if(fittest <= getFitness(i)) {
				fittest = getFitness(i);
				fittestIndex = i;
			}
		}
		
		return chromosomes.get(fittestIndex);
	}
	
	private String getSecondFittest() {
		
		return null;
	}
	
	public List<String> getTwoMostFittest(){
		
		int fittest = Integer.MIN_VALUE;
		int fittestIndex = 0;
		
		List<String> twoFittest = new ArrayList<String>();
		List<String> tmp = new ArrayList<String>(chromosomes);
		
				
		twoFittest.add(getFittest());	
			
		return twoFittest;
		
	}
	
	
	
}
