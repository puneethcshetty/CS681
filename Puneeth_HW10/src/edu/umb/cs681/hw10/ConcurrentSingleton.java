package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {

	private ConcurrentSingleton(){};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>(); 

	// Factory method to create or return the singleton instance
	public static AtomicReference<ConcurrentSingleton> getInstance(){
		if(instance.get() == null) {
			instance.set(new ConcurrentSingleton());		
			return instance;
		}
		return instance;
	}


	@SuppressWarnings("static-access")
	public static void main(String args[]) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ConcurrentSingleton.getInstance());
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ConcurrentSingleton.getInstance());
			}
		});

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ConcurrentSingleton.getInstance());
			}
		});

		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ConcurrentSingleton.getInstance());
			}
		});

		t1.start();t2.start();
		t3.start();t4.start();

		try {
			t1.join();t2.join();
			t3.join();t4.join();
		} catch (InterruptedException e) {}
	}
}
