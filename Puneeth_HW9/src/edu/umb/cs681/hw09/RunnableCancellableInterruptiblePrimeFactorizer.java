package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{

	protected boolean done=false;
	ReentrantLock lock = new ReentrantLock();

	public RunnableCancellableInterruptiblePrimeFactorizer(long dividen, long from, long to) {
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
		long divisor = from;
		while( dividend != 1 && divisor <= to ){
			lock.lock();
			try {
				if(done) {
					System.out.println("******Thread Stopped******");
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
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public void run() {
		generatePrimeFactors();
	}

	public static void main(String args[]) {
		System.out.println("*******************************************");
		System.out.print("Prime factors of 10: \n");
		RunnableCancellableInterruptiblePrimeFactorizer rFactor1 = new RunnableCancellableInterruptiblePrimeFactorizer(10,2,(long)Math.sqrt(10));
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
		System.out.print("Prime factors of 10: \n");
		RunnableCancellableInterruptiblePrimeFactorizer rFactor2 = new RunnableCancellableInterruptiblePrimeFactorizer(10,2,(long)Math.sqrt(10));
		Thread t2 = new Thread(rFactor2);
		System.out.println("Calling setDone with Thread sleep of 3 sec without interruption.");
		t2.start();
		try {
			Thread.sleep(3000);
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
		System.out.print("Prime factors of 10: ");
		RunnableCancellableInterruptiblePrimeFactorizer rFactor3 = new RunnableCancellableInterruptiblePrimeFactorizer(10,2,(long)Math.sqrt(10));
		Thread t3 = new Thread(rFactor3);
		System.out.println("Calling setDone with Thread sleep of 1 sec with interruption.");
		t3.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rFactor3.setDone();
		t3.interrupt();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + rFactor3.getPrimeFactors() + "\n");
		System.out.println("*******************************************");
	}
}
