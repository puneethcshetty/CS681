package edu.umb.cs681.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		//With 1 thread
		System.out.println("-----------------Using Executors------------------");
		System.out.println("--------------------------------------------------");
		RunnablePrimeGenerator gen = new RunnablePrimeGenerator(1L, 2000000L);
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		long start = System.currentTimeMillis();
		
		executor.execute(gen);
		executor.shutdown();
		try {
			executor.awaitTermination(7000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		long time = end - start;
		
		long primeNum = gen.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.");
        System.out.println("Time taken with 1 thread Executor: " + time + "ms");
		
		//With 2 threads
        System.out.println("--------------------------------------------------");
		RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1000000L, 2000000L);
		
		ExecutorService executor1 = Executors.newFixedThreadPool(2);
		
		start = System.currentTimeMillis();
		
		executor1.execute(gen1);
		executor1.execute(gen2);
		executor1.shutdown();
		try {
			executor1.awaitTermination(7000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		end = System.currentTimeMillis();
		time = end - start;

		primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.");
        System.out.println("Time taken with 2 thread Executor: " + time + "ms");
        
        //With 4 threads
        System.out.println("--------------------------------------------------");
        RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(500000L, 1000000L);
		RunnablePrimeGenerator gen5 = new RunnablePrimeGenerator(1000000L, 1500000L);
		RunnablePrimeGenerator gen6 = new RunnablePrimeGenerator(1500000L, 2000000L);
		
		ExecutorService executor2 = Executors.newFixedThreadPool(4);
		
		start = System.currentTimeMillis();
		
		executor2.execute(gen3);
		executor2.execute(gen4);
		executor2.execute(gen5);
		executor2.execute(gen6);
		executor2.shutdown();
		try {
			executor2.awaitTermination(7000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		end = System.currentTimeMillis();
		time = end - start;

		primeNum = gen3.getPrimes().size() + gen4.getPrimes().size() + gen5.getPrimes().size() + gen6.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.");
        System.out.println("Time taken with 4 thread Executor: " + time + "ms");
        
        //With 8 threads
        System.out.println("--------------------------------------------------");
        RunnablePrimeGenerator gen7 = new RunnablePrimeGenerator(1L, 250000L);
		RunnablePrimeGenerator gen8 = new RunnablePrimeGenerator(250000L, 500000L);
		RunnablePrimeGenerator gen9 = new RunnablePrimeGenerator(500000L, 750000L);
		RunnablePrimeGenerator gen10 = new RunnablePrimeGenerator(750000L, 1000000L);
		RunnablePrimeGenerator gen11 = new RunnablePrimeGenerator(1000000L, 1250000L);
		RunnablePrimeGenerator gen12 = new RunnablePrimeGenerator(1250000L, 1500000L);
		RunnablePrimeGenerator gen13 = new RunnablePrimeGenerator(1500000L, 1750000L);
		RunnablePrimeGenerator gen14 = new RunnablePrimeGenerator(1750000L, 2000000L);
		
		ExecutorService executor3 = Executors.newFixedThreadPool(8);
		
		start = System.currentTimeMillis();

		executor3.execute(gen7);
		executor3.execute(gen8);
		executor3.execute(gen9);
		executor3.execute(gen10);
		executor3.execute(gen11);
		executor3.execute(gen12);
		executor3.execute(gen13);
		executor3.execute(gen14);
		executor3.shutdown();
		try {
			executor3.awaitTermination(7000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		end = System.currentTimeMillis();
		time = end - start;

		primeNum = gen7.getPrimes().size() + gen8.getPrimes().size() + gen9.getPrimes().size() + gen10.getPrimes().size() +
				gen11.getPrimes().size() + gen12.getPrimes().size() + gen13.getPrimes().size() + gen14.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.");
        System.out.println("Time taken with 8 thread Executor: " + time + "ms");
        
        //With 16 threads
        System.out.println("--------------------------------------------------");
        RunnablePrimeGenerator gen15 = new RunnablePrimeGenerator(1L, 125000L);
		RunnablePrimeGenerator gen16 = new RunnablePrimeGenerator(125000L, 250000L);
		RunnablePrimeGenerator gen17 = new RunnablePrimeGenerator(250000L, 375000L);
		RunnablePrimeGenerator gen18 = new RunnablePrimeGenerator(375000L, 500000L);
		RunnablePrimeGenerator gen19 = new RunnablePrimeGenerator(500000L, 625000L);
		RunnablePrimeGenerator gen20 = new RunnablePrimeGenerator(625000L, 750000L);
		RunnablePrimeGenerator gen21 = new RunnablePrimeGenerator(750000L, 875000L);
		RunnablePrimeGenerator gen22 = new RunnablePrimeGenerator(875000L, 1000000L);
		RunnablePrimeGenerator gen23 = new RunnablePrimeGenerator(1000000L, 1125000L);
		RunnablePrimeGenerator gen24 = new RunnablePrimeGenerator(1125000L, 1250000L);
		RunnablePrimeGenerator gen25 = new RunnablePrimeGenerator(1250000L, 1375000L);
		RunnablePrimeGenerator gen26 = new RunnablePrimeGenerator(1375000L, 1500000L);
		RunnablePrimeGenerator gen27 = new RunnablePrimeGenerator(1500000L, 1625000L);
		RunnablePrimeGenerator gen28 = new RunnablePrimeGenerator(1625000L, 1750000L);
		RunnablePrimeGenerator gen29 = new RunnablePrimeGenerator(1750000L, 1875000L);
		RunnablePrimeGenerator gen30 = new RunnablePrimeGenerator(1875000L, 2000000L);
		
		ExecutorService executor4 = Executors.newFixedThreadPool(8);
		
		start = System.currentTimeMillis();

		executor4.execute(gen15);
		executor4.execute(gen16);
		executor4.execute(gen17);
		executor4.execute(gen18);
		executor4.execute(gen19);
		executor4.execute(gen20);
		executor4.execute(gen21);
		executor4.execute(gen22);
		executor4.execute(gen23);
		executor4.execute(gen24);
		executor4.execute(gen25);
		executor4.execute(gen26);
		executor4.execute(gen27);
		executor4.execute(gen28);
		executor4.execute(gen29);
		executor4.execute(gen30);
		executor4.shutdown();
		try {
			executor4.awaitTermination(7000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		end = System.currentTimeMillis();
		time = end - start;

		primeNum = gen15.getPrimes().size() + gen16.getPrimes().size() + gen17.getPrimes().size() + gen18.getPrimes().size() +
				gen19.getPrimes().size() + gen20.getPrimes().size() + gen21.getPrimes().size() + gen22.getPrimes().size() +
				gen23.getPrimes().size() + gen24.getPrimes().size() + gen25.getPrimes().size() + gen26.getPrimes().size() +
				gen27.getPrimes().size() + gen28.getPrimes().size() + gen29.getPrimes().size() + gen30.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.");
        System.out.println("Time taken with 16 thread Executor: " + time + "ms");
        System.out.println("--------------------------------------------------");
        
	}

}
