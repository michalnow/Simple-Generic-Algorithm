package threading;

import java.util.ArrayList;
import java.util.List;

import population.Population;

public class PopulationThread implements Runnable {

	private Population population;
	private int noThreads;
	private static int number = 0;
	private static int m = 1;

	public PopulationThread(Population population, int noThreads) {

		this.population = population;
		this.noThreads = noThreads;

	}

	private List<String> divideChromosomes() {
		List<String> list = new ArrayList<String>();
		List<String> chromosomes = population.getChromosomes();
		System.out.println(chromosomes + "\n");
		String a = "";
		int endCut = Population.GENES_QUANTITY / noThreads;

		for (int i = 0; i < chromosomes.size(); i++) {
			a = chromosomes.get(i).substring(number * endCut, endCut * m);
			list.add(a);
		}
		number++;
		m++;

		return list;
	}

	@Override
	public void run() {
		population.setChromosomes(divideChromosomes());
		population.showPopulation();
		System.out.println();

		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY / noThreads) {
			population.addTheFittestOffspring();

		}
		System.out.println(population.getMaxFitnessLevel());

	}

}
