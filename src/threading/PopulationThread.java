package threading;

import java.util.ArrayList;
import java.util.List;

import population.Population;

public class PopulationThread implements Runnable {

	private static Population population;
	private int noThreads;
	private List<String> chromos;

	public PopulationThread(Population population, int noThreads) {

		PopulationThread.population = population;
		this.noThreads = noThreads;
		chromos = new ArrayList<String>();

	}

	private List<String> divideChromosomes() {
		List<String> list = new ArrayList<String>();
		List<String> chromosomes = population.getChromosomes();
		System.out.println(chromosomes + "\n");
		String a = "";
		int endCut = Population.GENES_QUANTITY / noThreads;
		int chromosomeLength = chromosomes.get(0).length();
		
		for (int i = 0; i < chromosomes.size(); i++) {
			a = chromosomes.get(i).substring(0, endCut);
			list.add(a);
		}
		
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < chromosomes.size(); i++) {
			a = chromosomes.get(i).substring(endCut, chromosomeLength-1);
			newList.add(a);
		}
		

		return list;
	}

	@Override
	public void run() {
		population.setChromosomes(divideChromosomes());
		population.showPopulation();

		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY / noThreads) {
			population.addTheFittestOffspring();

		}
		System.out.println(population.getMaxFitnessLevel());

	}

}
