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

	private int getLessFittest(List<String> list) {
		int lessFittest = Integer.MAX_VALUE;
		int lessFittestIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			if (lessFittest >= getFitness(list, i)) {
				lessFittest = getFitness(list, i);
				lessFittestIndex = i;
			}
		}

		return lessFittestIndex;
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

	public List<String> selection() {

		List<String> twoFittest = new ArrayList<String>();

		twoFittest.add(getFittest(chromosomes));
		twoFittest.add(getSecondFittest(chromosomes));

		return twoFittest;

	}

	public List<String> crossover() {

		int crossoverPoint = random.nextInt(GENES_QUANTITY);
		String fittest = selection().get(0);
		String secondFittest = selection().get(1);
		List<String> listAfterCrossover = new ArrayList<String>();
		char[] fittestArr = fittest.toCharArray();
		char[] secondFittestArr = secondFittest.toCharArray();

		for (int i = 0; i < crossoverPoint; i++) {
			char tmp = fittestArr[i];
			fittestArr[i] = secondFittestArr[i];

			secondFittestArr[i] = tmp;

		}

		listAfterCrossover.add(String.valueOf(fittestArr));
		listAfterCrossover.add(String.valueOf(secondFittestArr));

		return listAfterCrossover;
	}

	private List<String> mutation() {

		int mutationPoint = random.nextInt(GENES_QUANTITY);
		char[] fittestAfterCrossover = crossover().get(0).toCharArray();
		char[] secondFittestAfterCrossover = crossover().get(1).toCharArray();
		List<String> list = new ArrayList<String>();

		if (fittestAfterCrossover[mutationPoint] == '0') {
			fittestAfterCrossover[mutationPoint] = '1';
		} else {
			fittestAfterCrossover[mutationPoint] = '0';
		}

		mutationPoint = random.nextInt(GENES_QUANTITY);
		if (secondFittestAfterCrossover[mutationPoint] == '0') {
			secondFittestAfterCrossover[mutationPoint] = '1';
		} else {
			secondFittestAfterCrossover[mutationPoint] = '0';
		}

		list.add(String.valueOf(fittestAfterCrossover));
		list.add(String.valueOf(secondFittestAfterCrossover));
		return list;
	}

	public void addTheFittestOffspring() {

		List<String> mutationList = mutation();

		System.out.println("Now executing mutation result, below \n" + mutationList);

		List<String> fittest = new ArrayList<String>();
		fittest.add((mutationList.get(0)));
		List<String> secondFittest = new ArrayList<String>();
		secondFittest.add((mutationList.get(1)));

		if (getFitness(fittest, 0) > getFitness(secondFittest, 0)) {
			chromosomes.remove(getLessFittest(chromosomes));
			chromosomes.add(fittest.get(0));
		} else {
			chromosomes.remove(getLessFittest(chromosomes));
			chromosomes.add(secondFittest.get(0));
		}

	}

}
