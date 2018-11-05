package population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	private final static int POPULATION_QUANTITY = 5;
	private final static int GENES_QUANTITY = 8;

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

	private int getFitness(List<String> list, int index) {

		int fitness = 0;

		for (int i = 0; i < GENES_QUANTITY; i++) {
			if (list.get(index).toCharArray()[i] == '1') {
				fitness++;
			}
		}

		return fitness;
	}

	private String getFittest(List<String> list) {
		int fittest = Integer.MIN_VALUE;
		int fittestIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			if (fittest <= getFitness(list, i)) {
				fittest = getFitness(list, i);
				fittestIndex = i;
			}
		}

		return list.get(fittestIndex);
	}

	private String getSecondFittest(List<String> list) {

		List<String> tmp = new ArrayList<String>(list);
		String fittest = getFittest(tmp);
		int removeIndex = 0;

		for (int i = POPULATION_QUANTITY - 1; i >= 0; i--) {
			if (tmp.get(i).equals(fittest)) {
				removeIndex = i;
				break;
			}
		}
		tmp.remove(removeIndex);
		return getFittest(tmp);
	}

	public List<String> getTwoMostFittest() {

		List<String> twoFittest = new ArrayList<String>();

		twoFittest.add(getFittest(chromosomes));
		twoFittest.add(getSecondFittest(chromosomes));

		return twoFittest;

	}

}
