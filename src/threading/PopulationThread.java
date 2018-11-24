package threading;

import java.util.ArrayList;
import java.util.List;

import population.Population;

public class PopulationThread implements Runnable {
	
	private static int n = 1;
	private static int m = 1;
	private Population population;
	private List<String> dividedChromosomes;
	private static int generation = 0;
	private int genesLength = Population.GENES_QUANTITY;
	
	public PopulationThread(Population population, int noThreads) {
		dividedChromosomes = new ArrayList<String>();
		this.population = population;
		List<String> tmp = population.getChromosomes();
		List<String> list = new ArrayList<String>();
		int dividingLength = genesLength/noThreads;
		for(int i = 0;i<Population.POPULATION_QUANTITY;i++) {
			dividedChromosomes.add(tmp.get(i).substring(n-m, n * dividingLength));
		}
		
		for(int i = 0 ; i < Population.POPULATION_QUANTITY ;i++) {
			list.add(tmp.get(i).substring((n * dividingLength) - dividingLength, tmp.get(i).length()-1));
			
		}
		n++;
		m++;
		
		genesLength -= dividingLength;
		population.setChromosomes(list);
	}
	
	
	public static int getGeneration() {
		return generation;
	}



	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		System.out.println("ELO");
		while (population.getMaxFitnessLevel() < genesLength) {
			population.addTheFittestOffspring();
			System.out.println("fdELO");

			generation++;
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Executed in " + (endTime-startTime));
		
	}

}
