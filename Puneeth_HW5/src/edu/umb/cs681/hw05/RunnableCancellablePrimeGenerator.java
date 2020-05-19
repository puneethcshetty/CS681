package edu.umb.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator{

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public void setDone() {
		lock.lock();
		try {
			done = true;
		}finally {
			lock.unlock();
		}
	}
	
	public void generatePrime() {
		for(long n = from; n <= to; n++) {
			lock.lock();
			try {
				if(done) {
					System.out.println("Stopped generating prime nums.");
					this.primes.clear();
					break;
				}
				if(isPrime(n))
					this.primes.add(n);
			}finally {
				lock.unlock();
			}
		}
	}
	
	public void run() {
		generatePrime();	
	}
	
	public static void main(String[] args) {
		System.out.println("*******************************************");
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 1000);
		Thread t1 = new Thread(gen1);
		System.out.println("Calling setDone without Thread sleep.");
		t1.start();
		gen1.setDone();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		System.out.println("\nPrimes from 1 to 1000: " + gen1.getPrimes());
		
		long primeNum = gen1.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		
		System.out.println("*******************************************");
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(1,1000);
		Thread t2 = new Thread(gen2);
		System.out.println("Calling setDone with Thread sleep of 1 sec.");
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen2.setDone();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		System.out.println("\nPrimes from 1 to 1000: " + gen2.getPrimes());
		primeNum = gen2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		System.out.println("*******************************************");
        

	}
}
