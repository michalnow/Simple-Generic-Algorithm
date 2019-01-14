package population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	public final static int POPULATION_QUANTITY = 5;
	public final static int GENES_QUANTITY = 10000;
			;

	private List<String> chromosomes = new ArrayList<String>();
	private List<String> multiChromosomes = new ArrayList<String>();
	private Random random;

	public Population() {
		for (int i = 0; i < POPULATION_QUANTITY; i++) {
			multiChromosomes.add("");
		}
	}

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

	public List<String> getMultiChromosomes() {
		return multiChromosomes;
	}

	public void setMultiChromosomes(List<String> multiChromosomes) {
		this.multiChromosomes = multiChromosomes;
	}

	public List<String> getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(List<String> chromosomes) {
		this.chromosomes = chromosomes;
	}

	public void showPopulation() {
		for (String chromosome : chromosomes) {
			System.out.println(chromosome);
		}
	}

	public int getFitness(List<String> list, int index) {

		int fitness = 0;
		String chromosome = list.get(index);
		for (int i = 0; i < chromosomes.get(0).length(); i++) {
			if (chromosome.charAt(i) == '1') {
				fitness++;
			}
		}

		return fitness;
	}

	public int getMaxFitnessLevel() {
		int maxFitness = Integer.MIN_VALUE;
		for (int i = 0; i < chromosomes.size(); i++) {
			int fitnessLevel = getFitness(chromosomes, i);
			if (fitnessLevel > maxFitness) {
				maxFitness = fitnessLevel;
			}
		}
		return maxFitness;
	}

	public String getFittest(List<String> list) {
		int fittest = Integer.MIN_VALUE;
		int fittestIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			int fitnessLevel = getFitness(list, i);
			if (fittest <= fitnessLevel) {
				fittest = fitnessLevel;
				fittestIndex = i;
			}
		}

		return list.get(fittestIndex);
	}

	public int getLessFittest(List<String> list) {
		int lessFittest = Integer.MAX_VALUE;
		int lessFittestIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			int fitnessLevel = getFitness(list, i);
			if (lessFittest >= fitnessLevel) {
				lessFittest = fitnessLevel;
				lessFittestIndex = i;
			}
		}

		return lessFittestIndex;
	}

	public String getSecondFittest(List<String> list) {

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
		//System.out.println("SELECTION " + "\n-------------\n" + twoFittest +
		// "\n-------------");
		return twoFittest;

	}

	public List<String> crossover() {

		int crossoverPoint = random.nextInt(chromosomes.get(0).length() - 2) + 1;
		List<String> selection = new ArrayList<String>(selection());
		List<String> listAfterCrossover = new ArrayList<String>();
		char[] fittestArr = selection.get(0).toCharArray();
		char[] secondFittestArr = selection.get(1).toCharArray();

		for (int i = 0; i < crossoverPoint; i++) {
			char tmp = fittestArr[i];
			fittestArr[i] = secondFittestArr[i];

			secondFittestArr[i] = tmp;

		}

		listAfterCrossover.add(String.valueOf(fittestArr));
		listAfterCrossover.add(String.valueOf(secondFittestArr));

		// listAfterCrossover.add(fittest);
		// listAfterCrossover.add(secondFittest);
		//System.out.println("CROSSOVER, POINT = " + crossoverPoint +
		//"\n-------------\n" + listAfterCrossover+ "\n-------------");
		return listAfterCrossover;
	}

	public List<String> mutation() {

		int mutationPoint = random.nextInt(chromosomes.get(0).length());
		List<String> crossover = new ArrayList<String>(crossover());
		char[] fittestAfterCrossover = crossover.get(0).toCharArray();
		char[] secondFittestAfterCrossover = crossover.get(1).toCharArray();
		List<String> list = new ArrayList<String>();

		if (fittestAfterCrossover[mutationPoint] == '0') {
			fittestAfterCrossover[mutationPoint] = '1';
		} else {
			fittestAfterCrossover[mutationPoint] = '0';
		}

		if (secondFittestAfterCrossover[mutationPoint] == '0') {
			secondFittestAfterCrossover[mutationPoint] = '1';
		} else {
			secondFittestAfterCrossover[mutationPoint] = '0';
		}

		list.add(String.valueOf(fittestAfterCrossover));
		list.add(String.valueOf(secondFittestAfterCrossover));
		//System.out.println("MUTATION, POINT = " + mutationPoint + "\n-------------\n"
		//+ list+ "\n-------------");
		return list;
	}

	public void addTheFittestOffspring() {

		if (random.nextInt(777777) % 7 < 5) {
			List<String> mutationList = mutation();
			
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

		} else {
			List<String> crossover = new ArrayList<String>(crossover());
			chromosomes.add(crossover.get(0));
			chromosomes.remove(getLessFittest(chromosomes));
		}

	}

}
