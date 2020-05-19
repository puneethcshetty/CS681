package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{

	protected boolean done=false;
	ReentrantLock lock = new ReentrantLock();

	public RunnableCancellablePrimeFactorizer(long dividen, long from, long to) {
		super(dividen,from,to);	
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;			
		} finally {
			lock.unlock();
		}
	}

	public void generatePrimeFactors() {
		long divisor = 2;
		while( dividend != 1 && divisor <= to ){
			lock.lock();
			try {
				if(done) {
					System.out.println("****Thread Stopped****");
					this.factors.clear();
					break;
				}
				else {
					if(dividend % divisor == 0) {
						factors.add(divisor);
						dividend /= divisor;
					}else {
						if(divisor==2){ divisor++; }
						else{ divisor += 2; }
					}
				}
			}finally {
				lock.unlock();
			}
		}
	}

	public void run() {
		generatePrimeFactors();
	}

	public static void main(String args[]) {
		System.out.println("*******************************************");
		System.out.print("Prime factors of 923232323: \n");
		RunnableCancellablePrimeFactorizer rFactor1 = new RunnableCancellablePrimeFactorizer(923232323,2,(long)Math.sqrt(923232323));
		Thread t1 = new Thread(rFactor1);
		System.out.println("Calling setDone without Thread sleep.");
		t1.start();
		rFactor1.setDone();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + rFactor1.getPrimeFactors() + "\n");

		System.out.println("*******************************************");
		System.out.print("Prime factors of 923232323: \n");
		RunnableCancellablePrimeFactorizer rFactor2 = new RunnableCancellablePrimeFactorizer(923232323,2,(long)Math.sqrt(923232323));
		Thread t2 = new Thread(rFactor2);
		System.out.println("Calling setDone with Thread sleep of 1 milli sec.");
		t2.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rFactor2.setDone();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + rFactor2.getPrimeFactors() + "\n");

		System.out.println("*******************************************");
		System.out.print("Prime factors of 923232323: ");
		RunnableCancellablePrimeFactorizer rFactor3 = new RunnableCancellablePrimeFactorizer(923232323,2,(long)Math.sqrt(923232323));
		Thread t3 = new Thread(rFactor3);
		System.out.println("Calling setDone with Thread sleep of 10 milli sec.");
		t3.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rFactor3.setDone();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + rFactor3.getPrimeFactors() + "\n");
		System.out.println("*******************************************");
	}
}
