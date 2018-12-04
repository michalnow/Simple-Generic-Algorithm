package threading;

import java.util.ArrayList;
import java.util.List;

import population.Population;

public class PopulationThread implements Runnable {
	
	private Population population;
	private int noThreads;
	private static int number = -1;
	private static int m = 0;
	private List<String> list = new ArrayList<String>();
	private List<String> listAfter = new ArrayList<String>();
	private int name;

	public PopulationThread(Population population, int noThreads, int name) {

		this.population = population;
		this.noThreads = noThreads;
		this.name = name;

	}

	private List<String> divideChromosomes() {

		List<String> chromosomes = population.getChromosomes();
		
		
		for(int i = 0; i< chromosomes.size(); i++) {
			StringBuilder a = new StringBuilder(chromosomes.get(i));
			list.add(chromosomes.get(i).substring(0,Population.GENES_QUANTITY / noThreads));
			listAfter.add((a.delete(0, Population.GENES_QUANTITY / noThreads)).toString());
		}
		
		return list;
	}

	@Override
	public void run() {
		population.setChromosomes(divideChromosomes());
		int generation = 0;
		System.out.println("Generation nr " + generation+1 + " ||| " +population.getMaxFitnessLevel());

		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY / noThreads) {
			population.addTheFittestOffspring();
			generation++;
			System.out.println("Thread no. " + name +" Generation nr " + generation + " ||| fitness level " +population.getMaxFitnessLevel());

		}
		System.out.println(population.getMaxFitnessLevel() );
		population.setChromosomes(listAfter);
	}

}
