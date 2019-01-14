package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import population.Population;
import threading.PopulationThread;

public class GenericAlgorithm {

	private static void singleThreadExecution() {

		int generation = 0;
		Population population = new Population();
		population.randomizePopulation();
		long startTime = System.currentTimeMillis();
		System.out.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		
		while (population.getMaxFitnessLevel() < Population.GENES_QUANTITY) {
			//population.showPopulation();
			++generation;
			population.addTheFittestOffspring();
			System.out
					.println("Generation nr " + generation + " ||| fitness level = " + population.getMaxFitnessLevel());
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("\n\n\nSolution find in generation number = " + generation + "\nFitness level = "
				+ population.getMaxFitnessLevel());
		population.showPopulation();

		System.out.println(population.getChromosomes());
		System.out.println("It Took " + duration + " ms");
	}

	private static void multiThreadExecution(int noThreads) {
		System.out.println("Number of threads: ");

		long startTime = System.currentTimeMillis();

		Population population = new Population();
		population.randomizePopulation();
		population.showPopulation();
		System.out.println("Multi Thread");
		ExecutorService executor = Executors.newFixedThreadPool(1);
		for (int i = 0; i < noThreads; i++) {
			Thread thread = new Thread(new PopulationThread(population, noThreads, i));
			executor.execute(thread);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		long endTime = System.currentTimeMillis();

		System.out.println(population.getMultiChromosomes());
		System.out.println("It Took " + (endTime - startTime) + " ms");
	}

	public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		long start = Long.MIN_VALUE;
		long end = Long.MAX_VALUE;
	/*
		String choice = "";

		System.out.println("Time to decide:\n1 - Single thread execution\n2 - Multi thread execution");
		choice = scanner.next("[123]");*/
		int port = 3333;
		String choice = "";
		ServerSocket ss=new ServerSocket(port);
		System.out.println("Server listening on " + port + " port");

		Socket s=ss.accept();
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());



		while(!choice.equals("1") && !choice.equals("2")){


			System.out.println("waiting for client ");
			dout.writeUTF("Time to decide:\n1 - Single thread execution\n2 - Multi thread execution");
			choice = din.readUTF();

			System.out.println("client write: " + choice);

			dout.flush();
		}


		switch (choice) {

			case "1": {

				start = System.currentTimeMillis();
				singleThreadExecution();
				end = System.currentTimeMillis();

				break;
			}
	
			case "2": {

				dout.writeUTF("How many threads to inwoke: ");
				System.out.print("waiting for client");
				//System.out.print("How many threads to inwoke: ");
				int noThreads = Integer.parseInt(din.readUTF());
				dout.flush();

				start = System.currentTimeMillis();
				multiThreadExecution(noThreads);
				end = System.currentTimeMillis();

				break;
			}
	
			default: {
				System.out.println("Wrong input");
			}
		}
		dout.writeUTF("Solution was find in " + (end - start) + " ms");
		scanner.close();
		din.close();
		s.close();
		ss.close();

	}
}
