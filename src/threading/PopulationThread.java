package threading;

import java.util.ArrayList;
import java.util.List;

import population.Population;

public class PopulationThread implements Runnable {

	private Population population;
	private int noThreads;
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

		for (int i = 0; i < chromosomes.size(); i++) {
			StringBuilder a = new StringBuilder(chromosomes.get(i));
			list.add(chromosomes.get(i).substring(0, Population.GENES_QUANTITY / noThreads));
			listAfter.add((a.delete(0, Population.GENES_QUANTITY / noThreads)).toString());
		}

		return list;
	}

	@Override
	public void run() {

		List<String> list = divideChromosomes();
		population.setChromosomes(list);
		int generation = 1;
		System.out.println(
				"Thread no. " + name + " Generation nr " + generation + " ||| fitness level " + population.getMaxFitnessLevel());
		population.showPopulation();

		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY / noThreads) {
			population.addTheFittestOffspring();
			generation++;
			System.out.println("Thread no. " + name + " Generation nr " + generation + " ||| fitness level "
					+ population.getMaxFitnessLevel());

		}


		List<String> b = population.getMultiChromosomes();
		List<String> c = population.getChromosomes();
		List<String> d = new ArrayList<String>();
		for (int i = 0; i < Population.POPULATION_QUANTITY; i++) {
			String a = b.get(i) + c.get(i);
			d.add(a);
		}

		population.setChromosomes(listAfter);
		population.setMultiChromosomes(d);
	}

}
